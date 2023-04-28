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

    /* CONSTRUCTOR */
    public BannerlordSearch(Game<State, Action, State.Turn> game, double utilMin, double utilMax, int time){
        super(game, utilMin, utilMax, time);
    }
}
