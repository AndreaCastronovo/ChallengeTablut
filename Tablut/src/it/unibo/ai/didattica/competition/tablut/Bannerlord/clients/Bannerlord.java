package it.unibo.ai.didattica.competition.tablut.Bannerlord.clients;

/* EXTENSION OF BASIC TABLUT CLIENT TO MY BANNERLORD CLIENT */
import it.unibo.ai.didattica.competition.tablut.client.TablutClient;

/* MANAGEMENT OF EXCEPTION I/O AND IP HOST */
import java.io.IOException;

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
    public Bannerlord(String player) throws IOException{
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
    public Bannerlord(String player, int timeout, String server_ip) throws IOException{
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
    public Bannerlord(String player, int timeout, String server_ip, boolean show) throws IOException{
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
     * Method of runnable interface.
     * <p></p>
     * @author Andrea Castronovo
     * @see it.unibo.ai.didattica.competition.tablut.server.Server
     */
    @Override
    public void run() {

    }
}
