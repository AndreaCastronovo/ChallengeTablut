package it.unibo.ai.didattica.competition.tablut.Bannerlord.clients;

/* EXTENSION OF BASIC TABLUT CLIENT TO MY BANNERLORD CLIENT */
import it.unibo.ai.didattica.competition.tablut.Bannerlord.searchTree.BannerlordSearch;
import it.unibo.ai.didattica.competition.tablut.client.TablutClient;
import it.unibo.ai.didattica.competition.tablut.domain.Action;
import it.unibo.ai.didattica.competition.tablut.domain.GameAshtonTablut;
import it.unibo.ai.didattica.competition.tablut.domain.State;
import it.unibo.ai.didattica.competition.tablut.domain.StateTablut;

/* MANAGEMENT OF EXCEPTION I/O AND IP HOST */
import java.io.IOException;
import java.net.UnknownHostException;


/**
 * <b>Bannerlord</b> is my client class for the competition of A.I. Tablut Challenge 2023
 * @author Andrea Castronovo
 * @see <a href="https://github.com/AndreaCastronovo/ChallengeTablut">this</a> for GitHub page of project.
 */
public class Bannerlord extends TablutClient {

    /* VARIABLE CLASS */
    private static final String TEAM_NAME = "Bannerlord - Andrea Castronovo";   // Team Name
    private static String player = "none";  // Role of player in game, white|black or none as default.
    private static int timeout = 60;        // Timeout in seconds to choose next move.
    private static String server_ip = "localhost";  // Ip address of server
    private static boolean show = false;    // Flag to show depth and info node expanded


    /* CONSTRUCTOR */

    /**
     * MAIN CONSTRUCTOR
     * <p></p>
     * Specified only role of the player, other parameters have default value.
     * <p></p>
     * @param player The role of player in the game [WHITE | BLACK].
     * @throws IOException
     * <p></p>
     * @author Andrea Castronovo
     * @see TablutClient
     */
    public Bannerlord(String player) throws IOException {
        super(player, TEAM_NAME, timeout, server_ip);
        Bannerlord.player = player;
    }

    /**
     * MAIN CONSTRUCTOR
     * <p></p>
     * Specified role of the player and timeout.
     * <p></p>
     * @param player The role of player in the game [WHITE | BLACK].
     * @param timeout The timeout that will be taken into account (in seconds).
     * @throws IOException
     * <p></p>
     * @author Andrea Castronovo
     * @see TablutClient
     */
    public Bannerlord(String player, int timeout) throws IOException{
        super(player, TEAM_NAME, timeout, server_ip);
        Bannerlord.player = player;
        Bannerlord.timeout = timeout;
    }

    /**
     * MAIN CONSTRUCTOR
     * <p></p>
     * Specified role of the player, timeout and ip address.
     * <p></p>
     * @param player The role of player in the game [WHITE | BLACK].
     * @param timeout The timeout that will be taken into account (in seconds).
     * @param server_ip The ip address of server
     * @throws IOException
     * <p></p>
     * @author Andrea Castronovo
     * @see TablutClient
     */
    public Bannerlord(String player, int timeout, String server_ip) throws IOException, UnknownHostException{
        super(player, TEAM_NAME, timeout, server_ip);
        Bannerlord.player = player;
        Bannerlord.timeout = timeout;
        Bannerlord.server_ip = server_ip;
    }

    /**
     * MAIN CONSTRUCTOR
     * <p></p>
     * Specified role of the player, timeout, ip address and flag to show info.
     * <p></p>
     * @param player The role of player in the game [WHITE | BLACK].
     * @param timeout The timeout that will be taken into account (in seconds).
     * @param server_ip The ip address of server
     * @throws IOException
     * <p></p>
     * @author Andrea Castronovo
     * @see TablutClient
     */
    public Bannerlord(String player, int timeout, String server_ip, boolean show)
            throws IOException, UnknownHostException{
        super(player, TEAM_NAME, timeout, server_ip);
        Bannerlord.player = player;
        Bannerlord.timeout = timeout;
        Bannerlord.server_ip = server_ip;
        Bannerlord.show = show;
    }

    /* METHODS */
    /**
     * Method to get Team Name of Bannerlord client
     * <p></p>
     * @return String
     * @author Andrea Castronovo
     */
    public String getTeamName(){
        return TEAM_NAME;
    }

    /**
     * Method to get role of Player of Bannerlord client
     * <p></p>
     * @return String
     * @author Andrea Castronovo
     */
    public String getPlayerName(){
        return player;
    }

    /**
     * Method to get timeout of Bannerlord client
     * <p></p>
     * @return Integer
     * @author Andrea Castronovo
     */
    public int getTimeout(){
        return timeout;
    }

    /**
     * Method to get ip address of server of Bannerlord client
     * <p></p>
     * @return String
     * @author Andrea Castronovo
     */
    public String getServer_ip(){
        return server_ip;
    }

    /**
     * Method to get flag of info show of Bannerlord client
     * <p></p>
     * @return boolean
     * @author Andrea Castronovo
     */
    public boolean getShow(){
        return show;
    }

    /**
     * Method of Runnable interface which is implemented in TablutClient
     * <p></p>
     * @author Andrea Castronovo
     * @see it.unibo.ai.didattica.competition.tablut.server.Server
     * @see Runnable
     */
    @Override
    public void run() {
        /* SEND THE NAME TO THE SERVER */
        try{
            this.declareName();
        }catch (Exception e){
            e.printStackTrace();
        }

        /* CREATE NEW STATE TABLUT and SET IT WHITE */
        State state = new StateTablut(); // Representation of the board and the turn
        state.setTurn(State.Turn.WHITE); // White makes first move

        /* CREATE GAME ENGINE WITH ASHTON RULES*/
        GameAshtonTablut gameAshtonTablut = new GameAshtonTablut(0, -1,
                "logs", "white_ai", "black_ai");

        /* LET'S PLAY! */
        while (true){
            /* READ ACTUAL STATE FROM SERVER */
            try {
                this.read();
            }catch (Exception e){
                e.printStackTrace();
                System.exit(1);
            }
            /* UPDATE STATE WITH THE ONE JUST RECEIVED */
            state = this.getCurrentState();
            System.out.println(TEAM_NAME + " (" + player + ") CURRENT STATE is: \n" +
                    state.toString());
            /* CHECK WHICH PLAYER I AM, WHITE OR BLACK? */
            if (this.getPlayer().equals(State.Turn.WHITE)){
                // PLAYING AS WHITE

                /* CHECK WHICH PLAYER HAVE TO DO THE NEXT MOVE */
                switch (state.getTurn()) {
                    case WHITE:
                        // MY TURN AS WHITE PLAYER

                        System.out.println(TEAM_NAME + ": i'm thinking which is the next move...\n");
                        Action action = findNextMove(gameAshtonTablut, state); //Find the best next move
                        System.out.println(action.toString());
                        try {
                            // TRY TO SEND AT SERVER THE NEXT MOVE
                            this.write(action);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case BLACK:
                        // OPPONENT TURN AS BLACK PLAYER

                        System.out.println(TEAM_NAME + ": i'm waiting to opponent move choice....\n");
                        break;
                    case WHITEWIN:
                        // I WON AS WHITE PLAYER

                        System.out.println(TEAM_NAME + ": I WON \n\n:D\n\n");
                        System.exit(0);
                        break;
                    case BLACKWIN:
                        // I LOST AS WHITE PLAYER

                        System.out.println(TEAM_NAME + ": I LOST \n\n:'(\n\n");
                        System.exit(0);
                        break;
                    default:
                        // DRAW ---> NOBODY WON

                        System.out.println(TEAM_NAME + ": DRAW \n\n:|\n\n");
                        System.exit(0);
                        break;
                }
            } else {
                // PLAYING AS BLACK

                /* CHECK WHICH PLAYER HAVE TO DO THE NEXT MOVE */
                switch (state.getTurn()){
                    case BLACK:
                        // MY TURN AS BLACK PLAYER

                        System.out.println(TEAM_NAME + ": i'm thinking which is the next move...\n");
                        Action action = findNextMove(gameAshtonTablut, state); //Find the best next move
                        System.out.println(action.toString());
                        try {
                            // TRY TO SEND AT SERVER THE NEXT MOVE
                            this.write(action);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case WHITE:
                        // OPPONENT TURN AS WHITE PLAYER

                        System.out.println(TEAM_NAME + ": i'm waiting to opponent move choice....\n");
                        break;
                    case BLACKWIN:
                        // I WON AS WHITE PLAYER

                        System.out.println(TEAM_NAME + ": I WON \n\n:D\n\n");
                        System.exit(0);
                        break;
                    case WHITEWIN:
                        // I LOST AS WHITE PLAYER

                        System.out.println(TEAM_NAME + ": I LOST \n\n:'(\n\n");
                        System.exit(0);
                        break;
                    case DRAW:
                        // NOBODY WON

                        System.out.println(TEAM_NAME + ": DRAW \n\n:|\n\n");
                        System.exit(0);
                        break;
                }
            }
        }
    }

    private Action findNextMove(GameAshtonTablut gameAshtonTablut, State state){
        /* BANNERLORD SEARCH OBJECT TO SEARCH BEST MOVE */
        BannerlordSearch bannerlordSearch = new BannerlordSearch(gameAshtonTablut, Double.MIN_VALUE,
                Double.MAX_VALUE, super.getTimeout() - 1); // From game engine search the best move to do with min-MAX value and alpha-beta pruning, but within timeout (-1 second to be safe)

        bannerlordSearch.setLogEnabled(show); // If show is enabled from args show log

        return bannerlordSearch.makeDecision(state); // Return best move
    }
}
