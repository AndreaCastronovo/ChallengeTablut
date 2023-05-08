package it.unibo.ai.didattica.competition.tablut.Bannerlord.heuristics;

import it.unibo.ai.didattica.competition.tablut.domain.State;

/**
 * <b>B_BannerlordHeuristics</b> is my heuristics class for black
 * client of the competition of A.I. Tablut Challenge 2023
 * @author Andrea Castronovo
 * @see <a href="https://github.com/AndreaCastronovo/ChallengeTablut">this</a> for GitHub page of project.
 */
public class B_BannerlordHeuristics extends BannerlordHeuristics{
    public B_BannerlordHeuristics(State state) {
        super(state);
    }

    public double evaluateState(){
        double stateEval = 1.0;
        int[] kingPos = kingPosition();

        /* CHECK IF KING CAN DO SAFE WIN IN TWO MOVES */
        if (isWinSafe())
            stateEval = Double.NEGATIVE_INFINITY; // Block white win

        /* TAKE KING CAPTURE */
        Bool_Doub bool_doub = kingCanDie(kingPos, "B");

        boolean bool_kingCanDie = bool_doub.getBool(); // FLAG TO SEE IF KING CAN BE DIE
        double doub_kingCanDie = bool_doub.getDoub();  // DOUBLE TO EVALUATE KING DIE BASED ON MANY MOVES REMAINING BEFORE DIE

        if (bool_kingCanDie){
            /* KING CAN ESCAPE */
            stateEval += doub_kingCanDie;
        }

        if (canKingEscape(kingPos))
            stateEval += -30;

        /* MINORITY OF WHITE (POSITIVE) MORE ENEMIES EATEN (NEGATIVE) */
        int numbOfBlack = state.getNumberOf(State.Pawn.BLACK);
        int numbOfWhite = state.getNumberOf(State.Pawn.WHITE);
        double blackAlive = (double) numbOfBlack/16;
        double whiteAlive = (double) numbOfWhite/8;
        stateEval += (0.99 - whiteAlive) * 200;
        stateEval += (blackAlive - 0.9) * 60;

        if(Double.isNaN(stateEval))
            stateEval = Double.NEGATIVE_INFINITY;

        return stateEval;
    }

}
