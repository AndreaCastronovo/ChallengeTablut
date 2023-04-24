package it.unibo.ai.didattica.competition.tablut.Bannerlord;

/* MANAGEMENT EXCEPTION FROM I/O */
import java.io.IOException;

/* BANNERLORD CLASS CLIENT */
import it.unibo.ai.didattica.competition.tablut.Bannerlord.clients.Bannerlord;

public class Main {

    public static void main(String[] args) throws IOException {
        /* VARIABLE */
        String player = "none";     // Role of player (W|B) must be specified from user
        String ip = "localhost";    // Ip of server, local as default
        int timeout = 60;           // Max time of game, 60' [s] as default
        boolean show = false;       // Flag to show the depth and information about nodes expanded


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
                player = args[0];
                break;
            case 2:
                //Role and timeout are specified
                player = args[0];
                try {
                    timeout = Integer.parseInt(args[1]);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    System.out.printf("ERROR: TIMEOUT MUST BE AN INTEGER THAT REPRESENTING SECONDS.\n" + USAGE);
                    System.exit(1);
                }
                break;
            case 3:
                //Role, timeout and ip of server are specified
                try {
                    timeout = Integer.parseInt(args[1]);
                    ip = args[2];
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    System.out.printf("ERROR: TIMEOUT MUST BE AN INTEGER THAT REPRESENTING SECONDS.\n" + USAGE);
                    System.exit(1);
                }
                break;
            case 4:
                //Role, timeout, ip and debug flag are specified
                try {
                    timeout = Integer.parseInt(args[1]);
                    ip = args[2];
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    System.out.printf("ERROR: TIMEOUT MUST BE AN INTEGER THAT REPRESENTING SECONDS.\n" + USAGE);
                    System.exit(1);
                }

                if (args[3].equals("debug"))
                    show = true;
                else {
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

        System.out.println("\n *** Team Bannerlord - Andrea Castronovo *** \n");
        System.out.println("Player: " + player + "\nTimeout: " + timeout + "[s]\nServer-ip: " + ip
                + "\nDebug: " + show + "\n");

        Bannerlord client = new Bannerlord(player, timeout, ip, show);
        client.run();
    }
}
