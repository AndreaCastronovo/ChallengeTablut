package it.unibo.ai.didattica.competition.tablut.Bannerlord.heuristics;

import it.unibo.ai.didattica.competition.tablut.domain.State;

import java.util.IllegalFormatCodePointException;

public abstract class BannerlordHeuristics {

    protected State state;

    public BannerlordHeuristics(State state) {
        this.state = state;
    }

    public double evaluateState() {
        return 0;
    }

    /**
     * @return check to see if king can win with 2 moves without being eaten
     */
    public boolean isWinSafe(){
        State.Pawn[][] board =  state.getBoard();
        boolean rightSafe = true;
        boolean leftSafe = true;

        if (board[5][4].equals(State.Pawn.EMPTY)) {
            // MOVES ON BOTTOM IS ALLOWED

            /* RAW 6 MUST BE EMPTY TO HAVE POSSIBILITIES TO WIN */
            for(int j = 0; j < 4; j++){
                // LEFT TO CENTER
                if (!board[6][j].equals(State.Pawn.EMPTY))
                    return false;
                // RIGHT TO CENTER
                if (!board[6][(board.length - 1)-j].equals(State.Pawn.EMPTY))
                    return false;
            }
            if (!board[6][4].equals(State.Pawn.EMPTY) && !board[6][4].equals(State.Pawn.KING)){
                return false;
            }

            /* RAW 5 CANNOT GIVE POSSIBILITIES AT ENEMIES TO EAT KING */
            for(int j = 0; j < 4; j++){
                // LEFT TO CENTER
                if (board[5][j].equals(State.Pawn.BLACK))
                    leftSafe = false;
                else if (board[5][j].equals(State.Pawn.WHITE))
                    leftSafe = true;
                // RIGHT TO CENTER
                if (board[5][(board.length - 1)-j].equals(State.Pawn.BLACK))
                    rightSafe = false;
                else if (board[5][(board.length - 1)-j].equals(State.Pawn.WHITE))
                    rightSafe = true;
            }
            return leftSafe && rightSafe;

        }else if (board[4][3].equals(State.Pawn.EMPTY)) {
            // MOVES ON LEFT IS ALLOWED

            /* COLUMN 2 MUST BE EMPTY TO HAVE POSSIBILITIES TO WIN */
            for(int i = 0; i < 4; i++){
                // LEFT TO CENTER
                if (!board[i][2].equals(State.Pawn.EMPTY))
                    return false;
                // RIGHT TO CENTER
                if (!board[(board.length - 1)-i][2].equals(State.Pawn.EMPTY))
                    return false;
            }
            if (!board[4][2].equals(State.Pawn.EMPTY) && !board[4][2].equals(State.Pawn.KING)){
                return false;
            }

            /* COLUMN 3 CANNOT GIVE POSSIBILITIES AT ENEMIES TO EAT KING */
            for(int i = 0; i < 4; i++){
                // LEFT TO CENTER
                if (board[i][3].equals(State.Pawn.BLACK))
                    leftSafe = false;
                else if (board[i][3].equals(State.Pawn.WHITE))
                    leftSafe = true;
                // RIGHT TO CENTER
                if (board[(board.length - 1)-i][3].equals(State.Pawn.BLACK))
                    rightSafe = false;
                else if (board[(board.length - 1)-i][3].equals(State.Pawn.WHITE))
                    rightSafe = true;
            }
            return leftSafe && rightSafe;

        } else if (board[3][4].equals(State.Pawn.EMPTY)) {
            // MOVES ON TOP IS ALLOWED

            /* RAW 2 MUST BE EMPTY TO HAVE POSSIBILITIES TO WIN */
            for(int j = 0; j < 4; j++){
                // LEFT TO CENTER
                if (!board[2][j].equals(State.Pawn.EMPTY))
                    return false;
                // RIGHT TO CENTER
                if (!board[2][(board.length - 1)-j].equals(State.Pawn.EMPTY))
                    return false;
            }
            if (!board[2][4].equals(State.Pawn.EMPTY) && !board[2][4].equals(State.Pawn.KING)){
                return false;
            }

            /* RAW 3 CANNOT GIVE POSSIBILITIES AT ENEMIES TO EAT KING */
            for(int j = 0; j < 4; j++){
                // LEFT TO CENTER
                if (board[3][j].equals(State.Pawn.BLACK))
                    leftSafe = false;
                else if (board[3][j].equals(State.Pawn.WHITE))
                    leftSafe = true;
                // RIGHT TO CENTER
                if (board[3][(board.length - 1)-j].equals(State.Pawn.BLACK))
                    rightSafe = false;
                else if (board[3][(board.length - 1)-j].equals(State.Pawn.WHITE))
                    rightSafe = true;
            }
            return leftSafe && rightSafe;


        } else if (board[4][5].equals(State.Pawn.EMPTY)) {
            // MOVES ON RIGHT IS ALLOWED

            /* COLUMN 6 MUST BE EMPTY TO HAVE POSSIBILITIES TO WIN */
            for(int i = 0; i < 4; i++){
                // LEFT TO CENTER
                if (!board[i][6].equals(State.Pawn.EMPTY))
                    return false;
                // RIGHT TO CENTER
                if (!board[(board.length - 1)-i][6].equals(State.Pawn.EMPTY))
                    return false;
            }
            if (!board[4][6].equals(State.Pawn.EMPTY) && !board[4][6].equals(State.Pawn.KING)){
                return false;
            }

            /* COLUMN 5 CANNOT GIVE POSSIBILITIES AT ENEMIES TO EAT KING */
            for(int i = 0; i < 4; i++){
                // LEFT TO CENTER
                if (board[i][5].equals(State.Pawn.BLACK))
                    leftSafe = false;
                else if (board[i][5].equals(State.Pawn.WHITE))
                    leftSafe = true;
                // RIGHT TO CENTER
                if (board[(board.length - 1)-i][5].equals(State.Pawn.BLACK))
                    rightSafe = false;
                else if (board[(board.length - 1)-i][5].equals(State.Pawn.WHITE))
                    rightSafe = true;
            }
            return leftSafe && rightSafe;

        }

        return false;
    }

    /**
     * @return the position of the King
     */
    public int[] kingPosition() {
        int[] pos = new int[2];

        State.Pawn[][] board = state.getBoard();

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (state.getPawn(i, j).equalsPawn("K")) {
                    pos[0] = i;
                    pos[1] = j;

                    return pos;
                }
            }
        }
        return pos;
    }

    /**
     * @param position position of king
     * @return check if king is in throne or not
     */
    public boolean isKingThrone(int[] position){
        return position[0] == 4 && position[1] == 4;
    }

    /**
     * @param position position of king
     * @return check if king is in throne or not
     */
    public boolean isKingBeforeWin(int[] position){
        return (position[0] == 6 && position[1] == 4) ||
                (position[0] == 4 && position[1] == 6) ||
                (position[0] == 2 && position[1] == 4) ||
                (position[0] == 4 && position[1] == 2);
    }
}
