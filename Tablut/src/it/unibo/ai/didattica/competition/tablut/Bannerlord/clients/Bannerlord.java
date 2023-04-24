package it.unibo.ai.didattica.competition.tablut.Bannerlord.clients;

/* EXTENSION OF BASIC TABLUT CLIENT TO MY BANNERLORD CLIENT */
import it.unibo.ai.didattica.competition.tablut.client.TablutClient;

/* MANAGEMENT OF EXCEPTION I/O AND IP HOST */
import java.io.IOException;
import java.net.UnknownHostException;

public class Bannerlord extends TablutClient {

    /* VARIABLE CLASS */
    private static final String TEAM_NAME = "Bannerlord";           // Name of Team
    private static final String PLAYER_NAME = "AndreaCastronovo";   // Name of player ----> My name :D
    private boolean show = false;                                   // Flag to enable live information


    /* CONSTRUCTOR */
    /**
     * MAIN CONSTRUCTOR
     *
     * @param player       The role of player in the game [WHITE | BLACK].
     * @param timeout      The timeout that will be taken into account (in seconds), that is to say the
     *                       maximum time in seconds that the player has to choose the next move.
     * @param server_ip    The ip address of server
     * @param show         The flag to enable prints of depth of the search and information about node expanded
     *
     * @throws IOException To handle I/O errors
     *
     * @
     */
    public Bannerlord(String player, int timeout, String server_ip, boolean show)
            throws IOException {

        super(player, PLAYER_NAME + "_" + TEAM_NAME, timeout, server_ip);
        this.show = show;
    }

    /**
     * EASY CONSTRUCTOR
     * Only role of player is specified.
     *
     * @param       player:       The role of player in the game [WHITE | BLACK].
     * [60]         timeout:      The timeout that will be taken into account (in seconds), that is to say the
     *                                  maximum time in seconds that the player has to choose the next move.
     * [localhost]  server_ip:    The ip address of server.
     * [false]      show:         The flag to enable prints of depth of the search and information about
     *                                  node expanded.
     * ----------------------------------------
     * TEAM_NAME:           Bannerlord
     * PLAYER_NAME:         AndreaCastronovo
     * ----------------------------------------
     * @throws IOException: To handle I/O errors
     *
     */
    public Bannerlord(String player) throws IOException{
        super(player, PLAYER_NAME + "_" + TEAM_NAME, 60, "localhost");
        this.show = false;
    }


    @Override
    public void run() {

    }
}
