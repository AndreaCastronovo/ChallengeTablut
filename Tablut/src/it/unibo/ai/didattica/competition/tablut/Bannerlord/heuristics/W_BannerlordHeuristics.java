package it.unibo.ai.didattica.competition.tablut.Bannerlord.heuristics;

import it.unibo.ai.didattica.competition.tablut.domain.GameAshtonTablut;
import it.unibo.ai.didattica.competition.tablut.domain.State;

import java.util.Arrays;

public class W_BannerlordHeuristics extends BannerlordHeuristics {
    public W_BannerlordHeuristics(State state) {
        super(state);
    }

    /**
     * @return evaluation of current state
     */
    @Override
    public double evaluateState() {
        double stateEval = 0.0;
        int[] kingPos = kingPosition();

        /* CHECK IF KING CAN DO SAFE WIN IN TWO MOVES */
        if (isKingThrone(kingPos)){
            if (isWinSafe())
                stateEval += Double.POSITIVE_INFINITY; // LET'S GO WIN
        }

        /* THROW AWAY KING CAPTURE */
        boolean bool_kingCanDie = kingCanDie(kingPos).getBool(); // FLAG TO SEE IF KING CAN BE DIE
        double doub_kingCanDie = kingCanDie(kingPos).getDoub();  // DOUBLE TO EVALUATE KING DIE BASED ON MANY MOVES REMAINING BEFORE DIE

        if (bool_kingCanDie){
            stateEval += doub_kingCanDie;
        }else{
            stateEval += 50;
            /* CHECK IF KING CAN ESCAPE */
            if (canKingEscape(kingPos))
                stateEval += Double.MAX_VALUE;
        }

        /* MINORITY OF WHITE (NEGATIVE) MORE ENEMIES EATEN (POSITIVE) not negative or positive infinity */

        return stateEval;
    }
}
