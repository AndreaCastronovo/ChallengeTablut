package it.unibo.ai.didattica.competition.tablut.Bannerlord.heuristics;

import it.unibo.ai.didattica.competition.tablut.domain.State;

public class W_BannerlordHeuristics extends BannerlordHeuristics{
    public W_BannerlordHeuristics(State state) {
        super(state);
    }

    /**
     * @return evaluation of current state
     */
    @Override
    public double evaluateState() {
        int[] position = kingPosition();

        /* CHECK IF KING CAN DO SAFE WIN IN TWO MOVES */
        if (isKingThrone(position)){
            if (isWinSafe())
                return Double.MAX_VALUE/2;
            else
                return Double.MIN_VALUE/2;
        }else if (isKingBeforeWin(kingPosition())){
            return Double.MAX_VALUE;
        }

        return super.evaluateState();
    }
}
