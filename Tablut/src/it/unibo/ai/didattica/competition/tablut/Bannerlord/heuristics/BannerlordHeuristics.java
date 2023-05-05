package it.unibo.ai.didattica.competition.tablut.Bannerlord.heuristics;

import it.unibo.ai.didattica.competition.tablut.domain.State;

public abstract class BannerlordHeuristics {

    protected State state;
    protected State.Pawn[][] board;

    public static class Bool_Doub{
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

    //-----------------------------------------------------------------------------------------------
//    // Matrix of camps
//    private final int[][] camps = {
//            {0,3}, {0,4}, {0,5},
//            {1,4},
//
//            {3,0},                                 {3,8},
//            {4,0}, {4,1},      {4,4},       {4,7}, {4,8},
//            {5,0},                                 {5,8},
//
//            {7,4},
//            {8,3}, {8,4}, {8,5},
//    };
//
//    /**
//     * @return the position of the King
//     */
//    public int[] kingPosition(State state) {
//        int[] pos = new int[2];
//
//        State.Pawn[][] board = state.getBoard();
//
//        for (int i = 0; i < board.length; i++) {
//            for (int j = 0; j < board.length; j++) {
//                if (state.getPawn(i, j).equalsPawn("K")) {
//                    pos[0] = i;
//                    pos[1] = j;
//
//                    return pos;
//                }
//            }
//        }
//        return pos;
//    }
//
//    /**
//     * @return true if king is on throne, false otherwise
//     */
//    public boolean isKingOnThrone(State state){
//        return state.getPawn(4, 4).equalsPawn("K");
//    }
//
//    /**
//     * @return the number of adjacent pawns that are target(BLACK, WHITE or King)
//     */
//    public int checkAdjacentPawns(State state, int[] pos, String target) {
//        int count = 0;
//
//        State.Pawn[][] board = state.getBoard();
//        if (board[pos[0]-1][pos[1]].equalsPawn(target))
//            count++;
//        if (board[pos[0]+1][pos[1]].equalsPawn(target))
//            count++;
//        if (board[pos[0]][pos[1]-1].equalsPawn(target))
//            count++;
//        if (board[pos[0]][pos[1]+1].equalsPawn(target))
//            count++;
//        return count;
//    }
//
//    /**
//     * @return the number of adjacent squares the king can move in
//     */
//    public int getKingMovement(State state, int[] pos) {
//        int count = 4;
//
//        State.Pawn[][] board = state.getBoard();
//
//        int[] space = new int[]{pos[0]-1,pos[1]};
//        // vertical checks
//        if (isPositionOccupied(state, space) || Arrays.stream(camps).anyMatch(camp -> Arrays.equals(camp, space)))
//            count--;
//        space[0] = pos[0] + 1;
//        if (isPositionOccupied(state, space) || Arrays.stream(camps).anyMatch(camp -> Arrays.equals(camp, space)))
//            count--;
//
//        // horizontal checks
//        space[0] = pos[0];
//        space[1] = pos[1] - 1;
//        if (isPositionOccupied(state, space) || Arrays.stream(camps).anyMatch(camp -> Arrays.equals(camp, space)))
//            count--;
//        space[1] = pos[1] + 1;
//        if (isPositionOccupied(state, space) || Arrays.stream(camps).anyMatch(camp -> Arrays.equals(camp, space)))
//            count--;
//
//        return count;
//    }
//
//
//    /**
//     * @return true if a camp is adjacent to pos
//     */
//    public boolean checkAdjacentCamp(int[] pos){
//        if (Arrays.stream(camps).anyMatch(camp -> Arrays.equals(camp, new int[]{pos[0]-1,pos[1]})))
//            return true;
//        if (Arrays.stream(camps).anyMatch(camp -> Arrays.equals(camp, new int[]{pos[0]+1,pos[1]})))
//            return true;
//        if (Arrays.stream(camps).anyMatch(camp -> Arrays.equals(camp, new int[]{pos[0],pos[1]-1})))
//            return true;
//        if (Arrays.stream(camps).anyMatch(camp -> Arrays.equals(camp, new int[]{pos[0],pos[1]+1})))
//            return true;
//        return false;
//    }
//
//    /**
//     * @return the adjacent positions occupied by target
//     */
//    protected List<int[]> adjacentPositionsOccupied(State state, int[] position, String target){
//        List<int[]> occupiedPositions = new ArrayList<int[]>();
//        int[] pos = new int[2];
//
//        State.Pawn[][] board = state.getBoard();
//        if (board[position[0]-1][position[1]].equalsPawn(target)) {
//            pos[0] = position[0] - 1;
//            pos[1] = position[1];
//            occupiedPositions.add(pos);
//        }
//        if (board[position[0]+1][position[1]].equalsPawn(target)) {
//            pos[0] = position[0] + 1;
//            pos[1] = position[1];
//            occupiedPositions.add(pos);
//        }
//        if (board[position[0]][position[1]-1].equalsPawn(target)) {
//            pos[0] = position[0];
//            pos[1] = position[1] - 1;
//            occupiedPositions.add(pos);
//        }
//        if (board[position[0]][position[1]+1].equalsPawn(target)) {
//            pos[0] = position[0];
//            pos[1] = position[1] + 1;
//            occupiedPositions.add(pos);
//        }
//
//        return occupiedPositions;
//    }
//
//    /**
//     * @return true if king is adjacent
//     */
//    protected boolean checkAdjacentKing(State state, int[] position) {
//        return checkAdjacentPawns(state, position, "K") > 0;
//    }
//
//    /**
//     * @return true if king is on a center tile
//     */
//    public boolean isKingOnCenter(State state,int[] kingPosition) {
//        return (kingPosition[0] > 2 && kingPosition[0] < 6 && kingPosition[1] > 2 && kingPosition[1] < 6);
//    }
//
//    /**
//     * @return the escapes which king can reach in the following order [ up, down, left, right ]
//     */
//    public int[] getKingEscapes(State state, int[] kingPosition) {
//        int[] escapes = new int[4];
//
//        if (!isKingOnCenter(state, kingPosition)) {
//            if ((!(kingPosition[1] > 2 && kingPosition[1] < 6)) && (!(kingPosition[0] > 2 && kingPosition[0] < 6))) {
//                int[] tempV = countFreeColumn(state, kingPosition);
//                int[] tempH = countFreeRow(state, kingPosition);
//                escapes[0] = tempV[0];
//                escapes[1] = tempV[1];
//                escapes[2] = tempH[0];
//                escapes[3] = tempH[1];
//            }
//            if ((kingPosition[1] > 2 && kingPosition[1] < 6)) {
//                int[] tempH = countFreeRow(state, kingPosition);
//                escapes[2] = tempH[0];
//                escapes[3] = tempH[1];
//            }
//            if ((kingPosition[0] > 2 && kingPosition[0] < 6)) {
//                int[] tempV = countFreeColumn(state, kingPosition);
//                escapes[0] = tempV[0];
//                escapes[1] = tempV[1];
//            }
//            return escapes;
//        }
//
//        return escapes;
//    }
//
//    /**
//     * @return free rows from given position [ left, right ]
//     */
//    public int[] countFreeRow(State state, int[] position) {
//        int row = position[0];
//        int column = position[1];
//        int[] currentPosition = new int[2];
//        int[] freeWays = new int[2];
//
//        freeWays[0] = 1;
//        freeWays[1] = 1;
//
//        currentPosition[0] = row;
//        // left side
//        for (int i = column-1; i >= 0; i--) {
//            currentPosition[1] = i;
//            if (isPositionOccupied(state, currentPosition) || Arrays.stream(camps).anyMatch(camp -> Arrays.equals(camp, currentPosition))){
//                freeWays[0] = 0;
//                break;
//            }
//        }
//
//        // right side
//        for (int i = column+1; i <= 8; i++) {
//            currentPosition[1] = i;
//            if (isPositionOccupied(state, currentPosition) || Arrays.stream(camps).anyMatch(camp -> Arrays.equals(camp, currentPosition))) {
//                freeWays[1] = 0;
//                break;
//            }
//        }
//
//        return freeWays;
//    }
//
//    /**
//     * @return number of free columns from given position [ up, down ]
//     */
//    public int[] countFreeColumn(State state,int[] position){
//        int row = position[0];
//        int column = position[1];
//        int[] currentPosition = new int[2];
//        int[] freeWays = new int[2];
//
//        freeWays[0] = 1;
//        freeWays[1] = 1;
//
//        currentPosition[1]=column;
//        // upside
//        for(int i=row-1; i>=0; i--) {
//            currentPosition[0]=i;
//            if (isPositionOccupied(state, currentPosition) || Arrays.stream(camps).anyMatch(camp -> Arrays.equals(camp, currentPosition))){
//                freeWays[0] = 0;
//                break;
//            }
//        }
//
//        // downside
//        for(int i=row+1; i<=8; i++) {
//            currentPosition[0]=i;
//            if (isPositionOccupied(state, currentPosition) || Arrays.stream(camps).anyMatch(camp -> Arrays.equals(camp, currentPosition))) {
//                freeWays[1] = 0;
//                break;
//            }
//        }
//
//        return freeWays;
//    }
//
//    /**
//     * @return true if a position is occupied, false otherwise
//     */
//    public boolean isPositionOccupied(State state,int[] position){
//        return !state.getPawn(position[0], position[1]).equals(State.Pawn.EMPTY);
//    }
//
//
//    /**
//     * @return number of positions needed to eat king in the current state
//     */
//    public int getNumbToEatKing(State state){
//        int[] kingPosition = kingPosition(state);
//
//        if (kingPosition[0] == 4 && kingPosition[1] == 4){
//            return 4;
//        } else if (isKingOnCenter(state, kingPosition)) {
//            return 3;
//        } else if (checkAdjacentCamp(kingPosition)) {
//            return 1;
//        } else {
//            return 2;
//        }
//    }
//
//    /**
//     * @return number of Target pawns in given quadrant
//     */
//    public int getQuadrantCount(State state, State.Pawn target, int quadrant) {
//        int[] rowRange = new int[2];
//        int[] columnRange = new int[2];
//        int count = 0;
//
//        if (quadrant == 1 || quadrant == 2) {
//            rowRange[0] = 0;
//            rowRange[1] = 3;
//        } else {
//            rowRange[0] = 5;
//            rowRange[1] = 9;
//        }
//
//        if(quadrant == 1 || quadrant == 3) {
//            columnRange[0] = 0;
//            columnRange[1] = 3;
//        } else {
//            columnRange[0] = 5;
//            columnRange[1] = 9;
//        }
//
//        for(int i = rowRange[0]; i <= rowRange[1]; i++) {
//            for(int j = columnRange[0] ; j <= columnRange[1]; j++) {
//                if(state.getBoard()[i][j].equalsPawn(target.toString()))
//                    count++;
//            }
//        }
//
//        return count;
//    }
//
//    /**
//     * @param state the current state of the board
//     * @param enemy color of the opposite pawn
//     * @param position position
//     *
//     * @return return true or false whether an enemy pawn can go to that position from mentioned side
//     */
//    public boolean checkUpside(State state, State.Pawn enemy, int[] position) {
//        for(int i = position[0]; i >= 0; i--) {
//            if (isPositionOccupied(state, position)) {
//                if (state.getBoard()[i][position[1]].equalsPawn(enemy.toString()))
//                    return true;
//                else return false;
//            }
//            if ((position[1] == 0 || position[1] == 8) && i == 3)
//                return false;
//            if ((position[1] == 1 || position[1] == 7) && i == 4)
//                return false;
//        }
//        return false;
//    }
//
//    /**
//     * @param state the current state of the board
//     * @param enemy color of the opposite pawn
//     * @param position position
//     *
//     * @return return true or false whether an enemy pawn can go to that position from mentioned side
//     */
//    public boolean checkDownside(State state, State.Pawn enemy, int[] position) {
//        for(int i = position[0]; i <= 8; i++) {
//            if (isPositionOccupied(state, position)) {
//                if (state.getBoard()[i][position[1]].equalsPawn(enemy.toString()))
//                    return true;
//                else return false;
//            }
//            if ((position[1] == 0 || position[1] == 8) && i == 5)
//                return false;
//            if ((position[1] == 1 || position[1] == 7) && i == 4)
//                return false;
//        }
//        return false;
//    }
//
//    /**
//     * @param state the current state of the board
//     * @param enemy color of the opposite pawn
//     * @param position position
//     *
//     * @return return true or false whether an enemy pawn can go to that position from mentioned side
//     */
//    public boolean checkRightSide(State state, State.Pawn enemy, int[] position) {
//        for(int i = position[1]; i <= 8; i++) {
//            if (isPositionOccupied(state, position)) {
//                if (state.getBoard()[position[0]][i].equalsPawn(enemy.toString()))
//                    return true;
//                else return false;
//            }
//            if ((position[0] == 0 || position[0] == 8) && i == 5)
//                return false;
//            if ((position[0] == 1 || position[0] == 7) && i == 4)
//                return false;
//        }
//        return false;
//    }
//
//    /**
//     * @param state the current state of the board
//     * @param enemy color of the opposite pawn
//     * @param position position
//     *
//     * @return return true or false whether an enemy pawn can go to that position from mentioned side
//     */
//    public boolean checkLeftSide(State state, State.Pawn enemy, int[] position) {
//        for(int i = position[1]; i >= 0; i--) {
//            if (isPositionOccupied(state, position)) {
//                if (state.getBoard()[position[0]][i].equalsPawn(enemy.toString()))
//                    return true;
//                else return false;
//            }
//            if ((position[0] == 0 || position[0] == 8) && i == 3)
//                return false;
//            if ((position[0] == 1 || position[0] == 7) && i == 4)
//                return false;
//        }
//        return false;
//    }
//
//    /**
//     * @param state the current state of the board
//     * @param position the position of the pawn
//     *
//     * @return return a true or false whether a pawn can be captured
//     */
//
//    public boolean canBeCaptured(State state, int[] position, State.Pawn pawn) {
//        if ((position[0] == 0 || position[0] == 8) && (position[1] == 0 || position[1] == 8))
//            return false;
//
//        State.Pawn enemy = (pawn.equalsPawn(State.Pawn.WHITE.toString()) || pawn.equalsPawn(State.Pawn.KING.toString())) ? State.Pawn.BLACK : State.Pawn.WHITE;
//
//        // if is king and on center we have special cases
//        if (pawn.equalsPawn(State.Pawn.KING.toString()) && isKingOnCenter(state, position)) {
//            int needed = getNumbToEatKing(state);
//
//            // if king is in danger (1 more enemy pawn to kill)
//            if (checkAdjacentPawns(state, position, enemy.toString()) == (needed - 1)) {
//                // search for empty space
//                int[] space = new int[]{position[0]-1,position[1]};
//
//                // vertical checks
//                if (isPositionOccupied(state, space) && space[0] != 4 && space[1] != 4)
//                    return (checkLeftSide(state, enemy, space) || checkRightSide(state, enemy, space));
//                space[0] = position[0] + 1;
//                if (isPositionOccupied(state, space) && space[0] != 4 && space[1] != 4)
//                    return (checkLeftSide(state, enemy, space) || checkRightSide(state, enemy, space));
//
//                // horizontal checks
//                space[0] = position[0];
//                space[1] = position[1] - 1;
//                if (isPositionOccupied(state, space) && space[0] != 4 && space[1] != 4)
//                    return (checkUpside(state, enemy, space) || checkDownside(state, enemy, space));
//                space[1] = position[1] + 1;
//                return (checkUpside(state, enemy, space) || checkDownside(state, enemy, space));
//            }
//            else return false;
//        }
//
//        if (verticalCapturePossible(position, enemy))
//            return true;
//        if (horizontalCapturePossible(position, enemy))
//            return true;
//
//        return false;
//    }
//
//    /**
//     * @return true if an Enemy pawn can capture a pawn in given Position vertically
//     */
//    private boolean verticalCapturePossible (int[] position, State.Pawn enemy) {
//        int[] targetPos = new int[2];
//        int[] checkPos = new int[2];
//
//        if (position[0] == 0 || position[0] == 8)
//            return false;
//
//        // upside
//        targetPos[0] = position[0] - 1;
//        targetPos[1] = position[1];
//
//        if (Arrays.stream(camps).anyMatch(camp -> Arrays.equals(camp, targetPos)) ||
//                (isPositionOccupied(state, targetPos) && state.getPawn(targetPos[0], targetPos[1]).equalsPawn(enemy.toString()))) {
//            checkPos[0] = position[0] + 1;
//            checkPos[1] = position[1];
//            if (!isPositionOccupied(state, checkPos) && (checkLeftSide(state, enemy, checkPos) || checkRightSide(state, enemy, checkPos))) {
//                return true;
//            }
//        }
//
//        // downside
//        targetPos[0] = position[0] + 1;
//        targetPos[1] = position[1];
//
//        if (Arrays.stream(camps).anyMatch(camp -> Arrays.equals(camp, targetPos)) ||
//                (isPositionOccupied(state, targetPos) && state.getPawn(targetPos[0], targetPos[1]).equalsPawn(enemy.toString()))) {
//            checkPos[0] = position[0] - 1;
//            checkPos[1] = position[1];
//            if (!isPositionOccupied(state, checkPos) && (checkLeftSide(state, enemy, checkPos) || checkRightSide(state, enemy, checkPos))) {
//                return true;
//            }
//        }
//
//        return false;
//    }
//
//    /**
//     * @return true if an Enemy pawn can capture a pawn in given Position horizontally
//     */
//    private boolean horizontalCapturePossible(int[] position, State.Pawn enemy) {
//        int[] targetPos = new int[2];
//        int[] checkPos = new int[2];
//
//        if (position[1] == 0 || position[1] == 8)
//            return false;
//        // left side present
//        targetPos[0] = position[0];
//        targetPos[1] = position[1] - 1;
//
//        if (Arrays.stream(camps).anyMatch(camp -> Arrays.equals(camp, targetPos)) ||
//                (isPositionOccupied(state, targetPos) && state.getPawn(targetPos[0], targetPos[1]).equalsPawn(enemy.toString()))) {
//            checkPos[0] = position[0];
//            checkPos[1] = position[1] + 1;
//            if (!isPositionOccupied(state, checkPos) && (checkUpside(state, enemy, checkPos) || checkDownside(state, enemy, checkPos))) {
//                return true;
//            }
//        }
//
//        // right side present
//        targetPos[0] = position[0];
//        targetPos[1] = position[1] + 1;
//
//        if (Arrays.stream(camps).anyMatch(camp -> Arrays.equals(camp, targetPos)) ||
//                (isPositionOccupied(state, targetPos) && state.getPawn(targetPos[0], targetPos[1]).equalsPawn(enemy.toString()))) {
//            checkPos[0] = position[0];
//            checkPos[1] = position[1] - 1;
//            if (!isPositionOccupied(state, checkPos) && (checkUpside(state, enemy, checkPos) || checkDownside(state, enemy, checkPos))) {
//                return true;
//            }
//        }
//
//        return false;
//    }
//    //-----------------------------------------------------------------------------------------------
}
