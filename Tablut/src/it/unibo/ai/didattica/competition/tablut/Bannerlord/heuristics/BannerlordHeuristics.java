package it.unibo.ai.didattica.competition.tablut.Bannerlord.heuristics;

import it.unibo.ai.didattica.competition.tablut.domain.State;


/**
 * <b>BannerlordHeuristics</b> is my heuristics class for the competition of A.I. Tablut Challenge 2023
 * @author Andrea Castronovo
 * @see <a href="https://github.com/AndreaCastronovo/ChallengeTablut">this</a> for GitHub page of project.
 */
public abstract class BannerlordHeuristics {

    /* LOCAL VARIABLE */
    protected State state;
    protected State.Pawn[][] board;

    public static class Bool_Doub{      // To have a single variable with 2 type: boolean & double
        boolean bool = true; double doub = 0.0;

        public boolean getBool(){
            return this.bool;
        }

        public void setBool(boolean bool){
            this.bool = bool;
        }

        public double getDoub(){
            return this.doub;
        }

        public void setDoub(double doub){
            this.doub = doub;
        }
    }

    public BannerlordHeuristics(State state) {
        this.state = state;
        this.board =  state.getBoard();
    }

    public double evaluateState() {
        return 0;
    }

    /**
     * @return check to see if king can win with 2 moves without being eaten
     */
    public boolean isWinSafe(){
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
     * @return check if king can escape in one move
     */
    public boolean canKingEscape(int[] position){
        if (isDxRawEmpty(position) && isEscape(position[0])){
            return true;
        }else if (isSxRawEmpty(position) && isEscape(position[0])){
            return true;
        }else if (isUpColumnEmpty(position) && isEscape(position[1])){
            return true;
        }else return isDownColumnEmpty(position) && isEscape(position[1]);
    }

    /**
     * @param rawORcolumn raw|column of Pawn
     * @return check if edge of this raw|column is escape
     */
    public boolean isEscape(int rawORcolumn){
        switch (rawORcolumn){
            case 1: case 2: case 6: case 7:
                return true;
            default:
                return false;
        }
    }

    /**
     * @param position position of Pawn
     * @return check if right raw of it is empty
     */
    public boolean isDxRawEmpty(int[] position){
        int Pawn_raw = position[0];
        int Pawn_column = position[1];

        for (int j = Pawn_column + 1; j < board.length; j++){
            if (!board[Pawn_raw][j].equals(State.Pawn.EMPTY))
                return false;
        }

        return true;
    }

    /**
     * @param position position of Pawn
     * @return check if left raw of it is empty
     */
    public boolean isSxRawEmpty(int[] position){
        int Pawn_raw = position[0];
        int Pawn_column = position[1];

        for (int j = Pawn_column - 1; j >= 0; j--){
            if (!board[Pawn_raw][j].equals(State.Pawn.EMPTY))
                return false;
        }

        return true;
    }

    /**
     * @param position position of Pawn
     * @return check if right column of it is empty
     */
    public boolean isDownColumnEmpty(int[] position){
        int Pawn_raw = position[0];
        int Pawn_column = position[1];

        for (int i = Pawn_raw + 1; i < board.length; i++){
            if (!board[i][Pawn_column].equals(State.Pawn.EMPTY))
                return false;
        }

        return true;
    }

    /**
     * @param position position of Pawn
     * @return check if left column of it is empty
     */
    public boolean isUpColumnEmpty(int[] position){
        int Pawn_raw = position[0];
        int Pawn_column = position[1];

        for (int i = Pawn_raw - 1; i >= 0; i--){
            if (!board[i][Pawn_column].equals(State.Pawn.EMPTY))
                return false;
        }

        return true;
    }

    public boolean isKingNextThrone(int[] kingPos){
        int rawKing = kingPos[0];
        int columnKing = kingPos[1];

        if (rawKing == 4){
            return columnKing == 3 || columnKing == 5;
        }
        if (columnKing == 4){
            return rawKing == 3 || rawKing == 5;
        }

        return false;
    }

    public boolean isKingOutBoard(int[] kingPos){
        int rawKing = kingPos[0];
        int columnKing = kingPos[1];

        return rawKing == 0 || rawKing == board.length - 1 ||
                columnKing == 0 || columnKing == board.length - 1;
    }

    public boolean isPawnOutBoard(int[] pawnPos){
        int rawPawn = pawnPos[0];
        int columnPawn = pawnPos[1];

        return rawPawn == 0 || rawPawn == board.length - 1 ||
                columnPawn == 0 || columnPawn == board.length - 1;
    }

    public boolean isKingNearCitadels(int[] kingPos){
        int rawKing = kingPos[0];
        int columnKing = kingPos[1];

        return (rawKing == 1 && (columnKing == 3 || columnKing == 5)) ||
                (columnKing == 4 && (rawKing == 2 || rawKing == 6)) ||
                (rawKing == 3 && (columnKing == 1 || columnKing == 7)) ||
                (rawKing == 4 && (columnKing == 2 || columnKing == 6)) ||
                (rawKing == 5 && (columnKing == 1 || columnKing == 7)) ||
                (rawKing == 7 && (columnKing == 3 || columnKing == 5));
    }

    public boolean isBlackNextThrone(int i, int j){

        if (i == 4){
            return j == 3 || j == 5;
        }
        if (j == 4){
            return i == 3 || i == 5;
        }

        return false;
    }

    public boolean isBlackNearCitadels(int i, int j){

        return (i == 1 && (j == 3 || j == 5)) ||
                (j == 4 && (i == 2 || i == 6)) ||
                (i == 3 && (j == 1 || j == 7)) ||
                (i == 4 && (j == 2 || j == 6)) ||
                (i == 5 && (j == 1 || j == 7)) ||
                (i == 7 && (j == 3 || j == 5));
    }

    /**
     * @param kingPos position of king
     * @return boolean: to check if king can die <p>double: positive value to evaluate, as white, state</p>
     */
    public Bool_Doub kingCanDie(int[] kingPos, String player){
        Bool_Doub bool_doub = new Bool_Doub();

        /* FOUR ENEMIES SURROUNDED KING */
        // 1 ---> -10   // 2 ---> -30   // 3 ---> -MAX_VALUE   // 4 ---> NEGATIVE_INFINITY
        if (isKingThrone(kingPos)){
            int countBlack = 0;
            if (board[3][4].equals(State.Pawn.BLACK))
                countBlack++;
            if (board[4][5].equals(State.Pawn.BLACK))
                countBlack++;
            if (board[5][4].equals(State.Pawn.BLACK))
                countBlack++;
            if (board[4][3].equals(State.Pawn.BLACK))
                countBlack++;

            if (player.equals("B")){
                switch (countBlack){
                    case 1:
                        bool_doub.setBool(true);
                        bool_doub.setDoub(10);
                        return bool_doub;
                    case 2:
                        bool_doub.setBool(true);
                        bool_doub.setDoub(30);
                        return bool_doub;
                    case 3:
                        bool_doub.setBool(true);
                        bool_doub.setDoub(60);
                        return bool_doub;
                    case 4:
                        bool_doub.setBool(true);
                        bool_doub.setDoub(Double.POSITIVE_INFINITY);
                        return bool_doub;
                    default:
                        bool_doub.setBool(false);
                        bool_doub.setDoub(1.0);
                        break;
                }
            }else {
                switch (countBlack){
                    case 1:
                        bool_doub.setBool(true);
                        bool_doub.setDoub(-10);
                        return bool_doub;
                    case 2:
                        bool_doub.setBool(true);
                        bool_doub.setDoub(-30);
                        return bool_doub;
                    case 3:
                        bool_doub.setBool(true);
                        bool_doub.setDoub(-Double.MAX_VALUE);
                        return bool_doub;
                    case 4:
                        bool_doub.setBool(true);
                        bool_doub.setDoub(Double.NEGATIVE_INFINITY);
                        return bool_doub;
                    default:
                        bool_doub.setBool(false);
                        bool_doub.setDoub(1.0);
                        break;
                }
            }
        }

        /* THREE ENEMIES SURROUNDED KING NEXT TO THRONE */
        // 1 ---> -30   // 2 ---> -MAX_VALUE   // 3 ---> NEGATIVE_INFINITY
        if (isKingNextThrone(kingPos)){
            int countBlack = 0;
            int rawKing = kingPos[0];
            int columnKing = kingPos[1];

            /* KING IS UP THRONE */
            if (rawKing == 3 && columnKing == 4){
                if (board[3][3].equals(State.Pawn.BLACK))
                    countBlack++;
                if (board[2][4].equals(State.Pawn.BLACK))
                    countBlack++;
                if (board[3][5].equals(State.Pawn.BLACK))
                    countBlack++;
            }
            /* KING IS BELOW THRONE */
            if (rawKing == 5 && columnKing == 4){
                if (board[5][3].equals(State.Pawn.BLACK))
                    countBlack++;
                if (board[6][4].equals(State.Pawn.BLACK))
                    countBlack++;
                if (board[5][5].equals(State.Pawn.BLACK))
                    countBlack++;
            }
            /* KING IS LEFT THRONE */
            if (rawKing == 4 && columnKing == 3){
                if (board[3][3].equals(State.Pawn.BLACK))
                    countBlack++;
                if (board[4][2].equals(State.Pawn.BLACK))
                    countBlack++;
                if (board[5][3].equals(State.Pawn.BLACK))
                    countBlack++;
            }
            /* KING IS RIGHT THRONE */
            if (rawKing == 4 && columnKing == 5){
                if (board[3][5].equals(State.Pawn.BLACK))
                    countBlack++;
                if (board[4][6].equals(State.Pawn.BLACK))
                    countBlack++;
                if (board[5][5].equals(State.Pawn.BLACK))
                    countBlack++;
            }

            if (player.equals("W")){
                switch (countBlack){
                    case 1:
                        bool_doub.setBool(true);
                        bool_doub.setDoub(-30);
                        return bool_doub;
                    case 2:
                        bool_doub.setBool(true);
                        bool_doub.setDoub(-Double.MAX_VALUE);
                        return bool_doub;
                    case 3:
                        bool_doub.setBool(true);
                        bool_doub.setDoub(Double.NEGATIVE_INFINITY);
                        return bool_doub;
                    default:
                        bool_doub.setBool(false);
                        bool_doub.setDoub(2);   //Too risky stay out
                        break;
                }
            }else {
                switch (countBlack){
                    case 1:
                        bool_doub.setBool(true);
                        bool_doub.setDoub(30);
                        return bool_doub;
                    case 2:
                        bool_doub.setBool(true);
                        bool_doub.setDoub(60);
                        return bool_doub;
                    case 3:
                        bool_doub.setBool(true);
                        bool_doub.setDoub(Double.POSITIVE_INFINITY);
                        return bool_doub;
                    default:
                        bool_doub.setBool(false);
                        bool_doub.setDoub(1.0);
                        break;
                }
            }
        }

        bool_doub.setBool(false);
        bool_doub.setDoub(1); // Too risky stay out

        /* TWO ENEMIES SURROUNDED KING */
        // 1 ---> -60 // 2 --> NEGATIVE_INIFNITY
        if (!isKingThrone(kingPos) && !isKingNextThrone(kingPos)){
            if (!isKingOutBoard(kingPos)){
                int rawKing = kingPos[0];
                int columnKing = kingPos[1];

                if (player.equals("W")){
                    /* BEFORE LEFT THEN RIGHT */
                    if (board[rawKing][columnKing-1].equals(State.Pawn.BLACK)){
                        bool_doub.setDoub(-60);
                        if (board[rawKing][columnKing+1].equals(State.Pawn.BLACK)){
                            bool_doub.setDoub(Double.NEGATIVE_INFINITY);
                            bool_doub.setBool(true);
                        }
                    }
                    /* BEFORE RIGHT THEN LEFT */
                    if (board[rawKing][columnKing+1].equals(State.Pawn.BLACK)){
                        bool_doub.setDoub(-60);
                        if (board[rawKing][columnKing-1].equals(State.Pawn.BLACK)){
                            bool_doub.setDoub(Double.NEGATIVE_INFINITY);
                            bool_doub.setBool(true);
                        }
                    }
                    /* BEFORE UP THEN DOWN */
                    if (board[rawKing-1][columnKing].equals(State.Pawn.BLACK)){
                        bool_doub.setDoub(-60);
                        if (board[rawKing+1][columnKing].equals(State.Pawn.BLACK)){
                            bool_doub.setDoub(Double.NEGATIVE_INFINITY);
                            bool_doub.setBool(true);
                        }
                    }
                    /* BEFORE DOWN THEN UP */
                    if (board[rawKing+1][columnKing].equals(State.Pawn.BLACK)){
                        bool_doub.setDoub(-60);
                        if (board[rawKing-1][columnKing].equals(State.Pawn.BLACK)){
                            bool_doub.setDoub(Double.NEGATIVE_INFINITY);
                            bool_doub.setBool(true);
                        }
                    }
                }else {
                    /* BEFORE LEFT THEN RIGHT */
                    if (board[rawKing][columnKing-1].equals(State.Pawn.BLACK)){
                        bool_doub.setDoub(60);
                        if (board[rawKing][columnKing+1].equals(State.Pawn.BLACK)){
                            bool_doub.setDoub(Double.POSITIVE_INFINITY);
                            bool_doub.setBool(true);
                        }
                    }
                    /* BEFORE RIGHT THEN LEFT */
                    if (board[rawKing][columnKing+1].equals(State.Pawn.BLACK)){
                        bool_doub.setDoub(60);
                        if (board[rawKing][columnKing-1].equals(State.Pawn.BLACK)){
                            bool_doub.setDoub(Double.POSITIVE_INFINITY);
                            bool_doub.setBool(true);
                        }
                    }
                    /* BEFORE UP THEN DOWN */
                    if (board[rawKing-1][columnKing].equals(State.Pawn.BLACK)){
                        bool_doub.setDoub(60);
                        if (board[rawKing+1][columnKing].equals(State.Pawn.BLACK)){
                            bool_doub.setDoub(Double.POSITIVE_INFINITY);
                            bool_doub.setBool(true);
                        }
                    }
                    /* BEFORE DOWN THEN UP */
                    if (board[rawKing+1][columnKing].equals(State.Pawn.BLACK)){
                        bool_doub.setDoub(60);
                        if (board[rawKing-1][columnKing].equals(State.Pawn.BLACK)){
                            bool_doub.setDoub(Double.POSITIVE_INFINITY);
                            bool_doub.setBool(true);
                        }
                    }
                }
            }
        }

        /* ONE SURROUNDED KING IN CITADELS */
        // 1 --> NEGATIVE_INFINITY
        if (!isKingThrone(kingPos) && !isKingNextThrone(kingPos) && isKingNearCitadels(kingPos)){
            if (!isKingOutBoard(kingPos)){
                int rawKing = kingPos[0];
                int columnKing = kingPos[1];

                if (player.equals("W")){
                    if (rawKing == 1 && columnKing == 3){
                        if (board[1][2].equals(State.Pawn.BLACK)){
                            bool_doub.setDoub(Double.NEGATIVE_INFINITY);
                            bool_doub.setBool(true);
                        }
                        if (board[2][3].equals(State.Pawn.BLACK)) {
                            bool_doub.setDoub(Double.NEGATIVE_INFINITY);
                            bool_doub.setBool(true);
                        }
                    }

                    //---------------------------------------------------------------

                    if (rawKing == 2 && columnKing == 4){
                        if (board[3][4].equals(State.Pawn.BLACK)){
                            bool_doub.setDoub(Double.NEGATIVE_INFINITY);
                            bool_doub.setBool(true);
                        }
                    }

                    //---------------------------------------------------------------

                    if (rawKing == 1 && columnKing == 5){
                        if (board[2][5].equals(State.Pawn.BLACK)){
                            bool_doub.setDoub(Double.NEGATIVE_INFINITY);
                            bool_doub.setBool(true);
                        }
                        if (board[1][6].equals(State.Pawn.BLACK)){
                            bool_doub.setDoub(Double.NEGATIVE_INFINITY);
                            bool_doub.setBool(true);
                        }
                    }

                    //---------------------------------------------------------------

                    if (rawKing == 3 && columnKing == 1){
                        if (board[2][1].equals(State.Pawn.BLACK)){
                            bool_doub.setDoub(Double.NEGATIVE_INFINITY);
                            bool_doub.setBool(true);
                        }
                        if (board[3][2].equals(State.Pawn.BLACK)){
                            bool_doub.setDoub(Double.NEGATIVE_INFINITY);
                            bool_doub.setBool(true);
                        }
                    }

                    //---------------------------------------------------------------

                    if (rawKing == 4 && columnKing == 2){
                        if (board[4][3].equals(State.Pawn.BLACK)){
                            bool_doub.setDoub(Double.NEGATIVE_INFINITY);
                            bool_doub.setBool(true);
                        }
                    }

                    //---------------------------------------------------------------

                    if (rawKing == 5 && columnKing == 1){
                        if (board[6][1].equals(State.Pawn.BLACK)){
                            bool_doub.setDoub(Double.NEGATIVE_INFINITY);
                            bool_doub.setBool(true);
                        }
                        if (board[5][2].equals(State.Pawn.BLACK)){
                            bool_doub.setDoub(Double.NEGATIVE_INFINITY);
                            bool_doub.setBool(true);
                        }
                    }

                    //---------------------------------------------------------------

                    if (rawKing == 3 && columnKing == 7){
                        if (board[2][7].equals(State.Pawn.BLACK)){
                            bool_doub.setDoub(Double.NEGATIVE_INFINITY);
                            bool_doub.setBool(true);
                        }
                        if (board[3][6].equals(State.Pawn.BLACK)){
                            bool_doub.setDoub(Double.NEGATIVE_INFINITY);
                            bool_doub.setBool(true);
                        }
                    }

                    //---------------------------------------------------------------

                    if (rawKing == 4 && columnKing == 6){
                        if (board[4][5].equals(State.Pawn.BLACK)){
                            bool_doub.setDoub(Double.NEGATIVE_INFINITY);
                            bool_doub.setBool(true);
                        }
                    }

                    //---------------------------------------------------------------

                    if (rawKing == 5 && columnKing == 7){
                        if (board[6][7].equals(State.Pawn.BLACK)){
                            bool_doub.setDoub(Double.NEGATIVE_INFINITY);
                            bool_doub.setBool(true);
                        }
                        if (board[5][6].equals(State.Pawn.BLACK)){
                            bool_doub.setDoub(Double.NEGATIVE_INFINITY);
                            bool_doub.setBool(true);
                        }
                    }

                    //---------------------------------------------------------------

                    if (rawKing == 7 && columnKing == 3){
                        if (board[7][2].equals(State.Pawn.BLACK)){
                            bool_doub.setDoub(Double.NEGATIVE_INFINITY);
                            bool_doub.setBool(true);
                        }
                        if (board[6][3].equals(State.Pawn.BLACK)){
                            bool_doub.setDoub(Double.NEGATIVE_INFINITY);
                            bool_doub.setBool(true);
                        }
                    }

                    //---------------------------------------------------------------

                    if (rawKing == 6 && columnKing == 4){
                        if (board[5][4].equals(State.Pawn.BLACK)){
                            bool_doub.setDoub(Double.NEGATIVE_INFINITY);
                            bool_doub.setBool(true);
                        }
                    }

                    //---------------------------------------------------------------

                    if (rawKing == 7 && columnKing == 5){
                        if (board[7][6].equals(State.Pawn.BLACK)){
                            bool_doub.setDoub(Double.NEGATIVE_INFINITY);
                            bool_doub.setBool(true);
                        }
                        if (board[6][5].equals(State.Pawn.BLACK)){
                            bool_doub.setDoub(Double.NEGATIVE_INFINITY);
                            bool_doub.setBool(true);
                        }
                    }
                }else {
                    if (rawKing == 1 && columnKing == 3){
                        if (board[1][2].equals(State.Pawn.BLACK)){
                            bool_doub.setDoub(Double.POSITIVE_INFINITY);
                            bool_doub.setBool(true);
                        }
                        if (board[2][3].equals(State.Pawn.BLACK)) {
                            bool_doub.setDoub(Double.POSITIVE_INFINITY);
                            bool_doub.setBool(true);
                        }
                    }

                    //---------------------------------------------------------------

                    if (rawKing == 2 && columnKing == 4){
                        if (board[3][4].equals(State.Pawn.BLACK)){
                            bool_doub.setDoub(Double.POSITIVE_INFINITY);
                            bool_doub.setBool(true);
                        }
                    }

                    //---------------------------------------------------------------

                    if (rawKing == 1 && columnKing == 5){
                        if (board[2][5].equals(State.Pawn.BLACK)){
                            bool_doub.setDoub(Double.POSITIVE_INFINITY);
                            bool_doub.setBool(true);
                        }
                        if (board[1][6].equals(State.Pawn.BLACK)){
                            bool_doub.setDoub(Double.POSITIVE_INFINITY);
                            bool_doub.setBool(true);
                        }
                    }

                    //---------------------------------------------------------------

                    if (rawKing == 3 && columnKing == 1){
                        if (board[2][1].equals(State.Pawn.BLACK)){
                            bool_doub.setDoub(Double.POSITIVE_INFINITY);
                            bool_doub.setBool(true);
                        }
                        if (board[3][2].equals(State.Pawn.BLACK)){
                            bool_doub.setDoub(Double.POSITIVE_INFINITY);
                            bool_doub.setBool(true);
                        }
                    }

                    //---------------------------------------------------------------

                    if (rawKing == 4 && columnKing == 2){
                        if (board[4][3].equals(State.Pawn.BLACK)){
                            bool_doub.setDoub(Double.POSITIVE_INFINITY);
                            bool_doub.setBool(true);
                        }
                    }

                    //---------------------------------------------------------------

                    if (rawKing == 5 && columnKing == 1){
                        if (board[6][1].equals(State.Pawn.BLACK)){
                            bool_doub.setDoub(Double.POSITIVE_INFINITY);
                            bool_doub.setBool(true);
                        }
                        if (board[5][2].equals(State.Pawn.BLACK)){
                            bool_doub.setDoub(Double.POSITIVE_INFINITY);
                            bool_doub.setBool(true);
                        }
                    }

                    //---------------------------------------------------------------

                    if (rawKing == 3 && columnKing == 7){
                        if (board[2][7].equals(State.Pawn.BLACK)){
                            bool_doub.setDoub(Double.POSITIVE_INFINITY);
                            bool_doub.setBool(true);
                        }
                        if (board[3][6].equals(State.Pawn.BLACK)){
                            bool_doub.setDoub(Double.POSITIVE_INFINITY);
                            bool_doub.setBool(true);
                        }
                    }

                    //---------------------------------------------------------------

                    if (rawKing == 4 && columnKing == 6){
                        if (board[4][5].equals(State.Pawn.BLACK)){
                            bool_doub.setDoub(Double.POSITIVE_INFINITY);
                            bool_doub.setBool(true);
                        }
                    }

                    //---------------------------------------------------------------

                    if (rawKing == 5 && columnKing == 7){
                        if (board[6][7].equals(State.Pawn.BLACK)){
                            bool_doub.setDoub(Double.POSITIVE_INFINITY);
                            bool_doub.setBool(true);
                        }
                        if (board[5][6].equals(State.Pawn.BLACK)){
                            bool_doub.setDoub(Double.POSITIVE_INFINITY);
                            bool_doub.setBool(true);
                        }
                    }

                    //---------------------------------------------------------------

                    if (rawKing == 7 && columnKing == 3){
                        if (board[7][2].equals(State.Pawn.BLACK)){
                            bool_doub.setDoub(Double.POSITIVE_INFINITY);
                            bool_doub.setBool(true);
                        }
                        if (board[6][3].equals(State.Pawn.BLACK)){
                            bool_doub.setDoub(Double.POSITIVE_INFINITY);
                            bool_doub.setBool(true);
                        }
                    }

                    //---------------------------------------------------------------

                    if (rawKing == 6 && columnKing == 4){
                        if (board[5][4].equals(State.Pawn.BLACK)){
                            bool_doub.setDoub(Double.POSITIVE_INFINITY);
                            bool_doub.setBool(true);
                        }
                    }

                    //---------------------------------------------------------------

                    if (rawKing == 7 && columnKing == 5){
                        if (board[7][6].equals(State.Pawn.BLACK)){
                            bool_doub.setDoub(Double.POSITIVE_INFINITY);
                            bool_doub.setBool(true);
                        }
                        if (board[6][5].equals(State.Pawn.BLACK)){
                            bool_doub.setDoub(Double.POSITIVE_INFINITY);
                            bool_doub.setBool(true);
                        }
                    }
                }
            }
        }

        /* KING IS OUT OF THRONE BUT NO ONE IS GOING TO EAT IT */
        return bool_doub;
    }
}
