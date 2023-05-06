package it.unibo.ai.didattica.competition.tablut.Bannerlord.heuristics;

import it.unibo.ai.didattica.competition.tablut.domain.GameAshtonTablut;
import it.unibo.ai.didattica.competition.tablut.domain.State;

public class W_BannerlordHeuristics extends BannerlordHeuristics {
    public W_BannerlordHeuristics(State state) {
        super(state);
    }

    /**
     * @return evaluation of current state
     */
    @Override
    public double evaluateState() {
        double stateEval = 1.0;
        int[] kingPos = kingPosition();

        /* CHECK IF KING CAN DO SAFE WIN IN TWO MOVES */
        if (isKingThrone(kingPos)){
            if (isWinSafe()){
                stateEval += Double.MAX_VALUE; // LET'S GO WIN
            }
        }

        /* THROW AWAY KING CAPTURE */
        Bool_Doub bool_doub = kingCanDie(kingPos);

        boolean bool_kingCanDie = bool_doub.getBool(); // FLAG TO SEE IF KING CAN BE DIE
        double doub_kingCanDie = bool_doub.getDoub();  // DOUBLE TO EVALUATE KING DIE BASED ON MANY MOVES REMAINING BEFORE DIE

        if (!bool_kingCanDie){
            /* KING CAN ESCAPE */
           stateEval += doub_kingCanDie;
        }


        /* MINORITY OF WHITE (NEGATIVE) MORE ENEMIES EATEN (POSITIVE) not negative or positive infinity */
        if (isKingThrone(kingPos)){
            int numbOfBlack = state.getNumberOf(State.Pawn.BLACK);
            int numbOfWhite = state.getNumberOf(State.Pawn.WHITE);
            double blackAlive = (double) numbOfBlack/16;
            double whiteAlive = (double) numbOfWhite/8;
            stateEval += (0.99 - blackAlive) * 800;
            stateEval += (whiteAlive - 0.9) * 10;
        } else{
            stateEval += 5; // Too risky stay out
        }

        for (int i = 0; i < board.length; i++){
            for (int j = 0; j < board.length; j++){
                if (board[i][j].equals(State.Pawn.BLACK)) {
                    stateEval += canWhiteEatBlack_Surround(i,j);
                    stateEval += canWhiteEatBlack_Throne(i,j);
                    stateEval += canWhiteEatBlack_Citadels(i,j);
                }
            }
        }

        return stateEval;
    }

    public double canWhiteEatBlack_Citadels(int i, int j){
        double eval = 0.0;

        if (isBlackNearCitadels(i,j)){
            if (i == 1 && j == 3){
                if (board[1][2].equals(State.Pawn.WHITE))
                    eval = 80;
                if (board[2][3].equals(State.Pawn.WHITE))
                    eval = 80;
            }

            // ------------------------------------------------

            if (i == 1 && j == 5){
                if (board[1][6].equals(State.Pawn.WHITE))
                    eval = 80;
                if (board[2][5].equals(State.Pawn.WHITE))
                    eval = 80;
            }

            // ------------------------------------------------

            if (i == 2 && j == 4){
                if (board[3][4].equals(State.Pawn.WHITE))
                    eval = 80;
            }

            // ------------------------------------------------

            if (i == 3 && j == 1){
                if (board[2][1].equals(State.Pawn.WHITE))
                    eval = 80;
                if (board[3][2].equals(State.Pawn.WHITE))
                    eval = 80;
            }

            // ------------------------------------------------

            if (i == 5 && j == 1){
                if (board[5][2].equals(State.Pawn.WHITE))
                    eval = 80;
                if (board[6][1].equals(State.Pawn.WHITE))
                    eval = 80;
            }

            // ------------------------------------------------

            if (i == 4 && j == 2){
                if (board[4][3].equals(State.Pawn.WHITE))
                    eval = 80;
            }

            // ------------------------------------------------

            if (i == 3 && j == 7){
                if (board[2][7].equals(State.Pawn.WHITE))
                    eval = 80;
                if (board[3][6].equals(State.Pawn.WHITE))
                    eval = 80;
            }

            // ------------------------------------------------

            if (i == 4 && j == 6){
                if (board[4][5].equals(State.Pawn.WHITE))
                    eval = 80;
            }

            // ------------------------------------------------

            if (i == 5 && j == 7){
                if (board[5][6].equals(State.Pawn.WHITE))
                    eval = 80;
                if (board[6][7].equals(State.Pawn.WHITE))
                    eval = 80;
            }

            // ------------------------------------------------

            if (i == 7 && j == 3){
                if (board[7][2].equals(State.Pawn.WHITE))
                    eval = 80;
                if (board[6][3].equals(State.Pawn.WHITE))
                    eval = 80;
            }

            // ------------------------------------------------

            if (i == 6 && j == 4){
                if (board[5][4].equals(State.Pawn.WHITE))
                    eval = 80;
            }

            // ------------------------------------------------

            if (i == 7 && j == 5){
                if (board[6][5].equals(State.Pawn.WHITE))
                    eval = 80;
                if (board[7][6].equals(State.Pawn.WHITE))
                    eval = 80;
            }
        }

        return eval;
    }

    public double canWhiteEatBlack_Surround(int rawBlack, int columnBlack){
        double eval = 0.0;
        int[] blackPos = new int[2];
        blackPos[0] = rawBlack;
        blackPos[1] = columnBlack;

        if (!isPawnOutBoard(blackPos)){
            /* TOP TO BOTTOM */
            if (board[rawBlack-1][columnBlack].equals(State.Pawn.WHITE)){
                eval = 10;
                if (board[rawBlack+1][columnBlack].equals(State.Pawn.WHITE))
                    eval = 80;
            }

            /* BOTTOM TO TOP*/
            if (board[rawBlack+1][columnBlack].equals(State.Pawn.WHITE)){
                eval = 10;
                if (board[rawBlack-1][columnBlack].equals(State.Pawn.WHITE))
                    eval = 80;
            }

            /* LEFT TO RIGHT*/
            if (board[rawBlack][columnBlack-1].equals(State.Pawn.WHITE)){
                eval = 10;
                if (board[rawBlack][columnBlack+1].equals(State.Pawn.WHITE))
                    eval = 80;
            }

            /* RIGHT TO LEFT*/
            if (board[rawBlack][columnBlack+1].equals(State.Pawn.WHITE)){
                eval = 10;
                if (board[rawBlack][columnBlack-1].equals(State.Pawn.WHITE))
                    eval = 80;
            }
        }

        return eval;
    }

    public double canWhiteEatBlack_Throne(int i, int j){
        double eval = 0.0;

        if (isBalckNextThrone(i,j)){
            // ABOVE
            if (i == 3 && j == 4){
                if (board[2][4].equals(State.Pawn.WHITE))
                    eval = 80;
            }
            // BELOW
            if (i == 4 && j == 5){
                if (board[4][6].equals(State.Pawn.WHITE))
                    eval = 80;
            }
            // LEFT
            if (i == 5 && j == 4){
                if (board[6][4].equals(State.Pawn.WHITE))
                    eval = 80;
            }
            // RIGHT
            if (i == 4 && j == 3){
                if (board[4][2].equals(State.Pawn.WHITE))
                    eval = 80;
            }
        }

        return eval;
    }

    /**
     * @param kingPos position of king
     * @return boolean: to check if king can die <p>double: positive value to evaluate, as white, state</p>
     */
    public Bool_Doub kingCanDie(int[] kingPos){
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
                    bool_doub.setDoub(Double.MAX_VALUE);
                    break;
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
        }

        bool_doub.setBool(false);
        bool_doub.setDoub(1); // Too risky stay out

        /* TWO ENEMIES SURROUNDED KING */
        // 1 ---> -60 // 2 --> NEGATIVE_INIFNITY
        if (!isKingThrone(kingPos) && !isKingNextThrone(kingPos)){
            if (!isKingOutBoard(kingPos)){
                int rawKing = kingPos[0];
                int columnKing = kingPos[1];
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
            }
        }

        /* ONE SURROUNDED KING IN CITADELS */
        // 1 --> NEGATIVE_INFINITY
        if (!isKingThrone(kingPos) && !isKingNextThrone(kingPos) && isKingNearCitadels(kingPos)){
            if (!isKingOutBoard(kingPos)){
                int rawKing = kingPos[0];
                int columnKing = kingPos[1];

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
            }
        }

        /* KING IS OUT OF THRONE BUT NO ONE IS GOING TO EAT IT */
        return bool_doub;
    }
}
