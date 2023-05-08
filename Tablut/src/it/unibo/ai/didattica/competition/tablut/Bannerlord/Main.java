package it.unibo.ai.didattica.competition.tablut.Bannerlord;

/* MANAGEMENT EXCEPTION FROM I/O */
import java.io.IOException;

/* BANNERLORD CLASS CLIENT */
import it.unibo.ai.didattica.competition.tablut.Bannerlord.clients.Bannerlord;

/**
 * <b>Main</b> is my principal run player for the competition of A.I. Tablut Challenge 2023
 * @author Andrea Castronovo
 * @see <a href="https://github.com/AndreaCastronovo/ChallengeTablut">this</a> for GitHub page of project.
 */
public class Main {
    public static void main(String[] args) throws IOException {

        /* CREATE BANNERLORD CLIENT */
        Bannerlord client = null;

        /* MANAGEMENT OF INPUT */
        String USAGE = "\tUSAGE: ./runmyplayer <balck|white> <timeout-in-seconds> " +
                "<server-ip> <debug>\n";
        switch (args.length) {
            case 0:
                //Nobody argument
                System.out.printf("ERROR: AT LEAST ONE ARGUMENT IS NECESSARY, " +
                        "you must specify which player you are [WHITE | BLACK]\n" + USAGE);
                System.exit(1);
                break;
            case 1:
                //Only role of player is specified
                client = new Bannerlord(args[0]);
                break;
            case 2:
                //Role and timeout are specified
                try {
                    client = new Bannerlord(args[0], Integer.parseInt(args[1]));
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    System.out.printf("ERROR: TIMEOUT MUST BE AN INTEGER THAT REPRESENTING SECONDS.\n" + USAGE);
                    System.exit(1);
                }
                break;
            case 3:
                //Role, timeout and ip of server are specified
                try {
                    client = new Bannerlord(args[0], Integer.parseInt(args[1]), args[2]);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    System.out.printf("ERROR: TIMEOUT MUST BE AN INTEGER THAT REPRESENTING SECONDS.\n" + USAGE);
                    System.exit(1);
                }
                break;
            case 4:
                //Role, timeout, ip and debug flag are specified
                if (args[3].equals("debug")){
                    try {
                        client = new Bannerlord(args[0], Integer.parseInt(args[1]), args[2], true);
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                        System.out.printf("ERROR: TIMEOUT MUST BE AN INTEGER THAT REPRESENTING SECONDS.\n"
                                + USAGE);
                        System.exit(1);
                    }
                } else {
                    System.out.printf("ERROR: THE LAST ARGUMENT CAN BE ONLY 'debug'\n" + USAGE);
                    System.exit(1);
                }
                break;
            default:
                //Number of arguments is major than maximal
                System.out.printf("ERROR: TOO MANY ARGUMENTS!\n" + USAGE);
                System.exit(1);
                break;
        }
        /* PRINT START INFO OF THIS GAME AND RUN IT */
        System.out.println("\n *** " + client.getTeamName() + " *** \n");
        System.out.println("Player: " + client.getPlayerName() + "\nTimeout: " + client.getTimeout()
                + " [s]\nServer-ip: " + client.getServer_ip() + "\nShow: " + client.getShow() + "\n");
        client.run();
    }
}
