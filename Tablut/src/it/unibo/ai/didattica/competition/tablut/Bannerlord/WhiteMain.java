package it.unibo.ai.didattica.competition.tablut.Bannerlord;
/* MANAGEMENT EXCEPTION FROM I/O */

import java.io.IOException;

/**
 * <b>Main</b> is my principal WhiteRun player for the competition of A.I. Tablut Challenge 2023
 * @author Andrea Castronovo
 * @see <a href="https://github.com/AndreaCastronovo/ChallengeTablut">this</a> for GitHub page of project.
 */
public class WhiteMain {

    public static void main(String[] args) throws ClassNotFoundException, IOException{

        /* TAKE GIVEN ARGUMENT "WHITE" AND CONCATENATE WITH ARGS */
        String[] arguments = new String[args.length + 1];
        arguments[0] = "WHITE";                                     // Take new array of strings with first given argument
        System.arraycopy(args, 0, arguments, 1, args.length); // Copy args (pass from input), if there are, in new array string

        /* LAUNCH MAIN.main WITH WHITE PLAYER AND ARGS IF GIVEN */
        Main.main(arguments);
    }
}
