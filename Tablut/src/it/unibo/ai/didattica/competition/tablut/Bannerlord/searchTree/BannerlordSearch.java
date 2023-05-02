package it.unibo.ai.didattica.competition.tablut.Bannerlord.searchTree;

/* IMPORT OF AIMA LIB */
import aima.core.search.adversarial.Game;
import aima.core.search.adversarial.IterativeDeepeningAlphaBetaSearch;
/* EXTENSION OF BASIC TABLUT CLIENT TO MY BANNERLORD CLIENT */
import it.unibo.ai.didattica.competition.tablut.domain.Action;
import it.unibo.ai.didattica.competition.tablut.domain.State;

/**
 * Use of AIMA Iterative Deepening minMAX search with Alpha-Beta pruning.
 *
 * @see aima.core.search.adversarial.IterativeDeepeningAlphaBetaSearch
 * @author Andrea Castronovo
 */
public class BannerlordSearch extends IterativeDeepeningAlphaBetaSearch<State, Action, State.Turn>{

    private static final String TEAM_NAME = "Bannerlord - Andrea Castronovo";   // Team Name


    /* CONSTRUCTOR */
    public BannerlordSearch(Game<State, Action, State.Turn> game, double utilMin, double utilMax, int time){
        super(game, utilMin, utilMax, time);
    }

    /**
     * Method controlling the search. It is based on minmax with iterative deepening and tries to make
     * to a good decision in limited time. It is overrided to print metrics.
     *
     * @param state the current state
     *
     * @return the chosen action
     */
    @Override
    public Action makeDecision(State state) {
        // SHOW INFO
        Action action = super.makeDecision(state);
        System.out.println(TEAM_NAME + ": " + " have been explored " + getMetrics().get(METRICS_NODES_EXPANDED) +
                " knots, reaching a depth of " + getMetrics().get(METRICS_MAX_DEPTH));

        return action;
    }
}
