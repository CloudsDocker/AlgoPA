//package algo;
//
//public class TicTacToe {
//    /*
//    348. Design Tic-Tac-Toe
//Design a Tic-tac-toe game that is played between two players on a n x n grid.
//
//You may assume the following rules:
//
//A move is guaranteed to be valid and is placed on an empty block.
//Once a winning condition is reached, no more moves is allowed.
//A player who succeeds in placing n of their marks in a horizontal, vertical, or diagonal row wins the game.
//Example:
//Given n = 3, assume that player 1 is "X" and player 2 is "O" in the board.
//
//TicTacToe toe = new TicTacToe(3);
//
//toe.move(0, 0, 1); -> Returns 0 (no one wins)
//|X| | |
//| | | |    // Player 1 makes a move at (0, 0).
//| | | |
//
//toe.move(0, 2, 2); -> Returns 0 (no one wins)
//|X| |O|
//| | | |    // Player 2 makes a move at (0, 2).
//| | | |
//
//toe.move(2, 2, 1); -> Returns 0 (no one wins)
//|X| |O|
//| | | |    // Player 1 makes a move at (2, 2).
//| | |X|
//
//toe.move(1, 1, 2); -> Returns 0 (no one wins)
//|X| |O|
//| |O| |    // Player 2 makes a move at (1, 1).
//| | |X|
//
//toe.move(2, 0, 1); -> Returns 0 (no one wins)
//|X| |O|
//| |O| |    // Player 1 makes a move at (2, 0).
//|X| |X|
//
//toe.move(1, 0, 2); -> Returns 0 (no one wins)
//|X| |O|
//|O|O| |    // Player 2 makes a move at (1, 0).
//|X| |X|
//
//toe.move(2, 1, 1); -> Returns 1 (player 1 wins)
//|X| |O|
//|O|O| |    // Player 1 makes a move at (2, 1).
//|X|X|X|
//Follow up:
//Could you do better than O(n2) per move() operation?
//     */
//
////    /** Initialize your data structure here. */
////    public TicTacToe(int n) {
////
////    }
////
////    /** Player {player} makes a move at ({row}, {col}).
////     @param row The row of the board.
////     @param col The column of the board.
////     @param player The player, can be either 1 or 2.
////     @return The current winning condition, can be either:
////     0: No one wins.
////     1: Player 1 wins.
////     2: Player 2 wins. */
////    public int move(int row, int col, int player) {
////
////    }
//
//
//
//
//
//
//    /// (A) clear approach
//
////    class TicTacToe {
//
//    // to check four lines once current player placed one step
////    判断当前落子所在行与列是否都是该棋手的子，以及如果落子在对角线上,则同时需要判断对角线上的子是否都是该棋手的子。
//        private int[][] data;
//
//        public TicTacToe(int n) {
//            data = new int[n][n];
//        }
//
//        public int move(int row, int col, int player) {
//            data[row][col] = player;
//            // check each ROW, return true if ALL cells are current player
//            for(int i = 0; i < data.length; i++){
//                if(data[i][col] != player){
//                    break;
//                }
//                if(i == data.length - 1){
//                    return player;
//                }
//            }
//            // check each CoLUMN, return true if ALL cells are current player
//            for(int j = 0; j < data.length; j++){
//                if(data[row][j] != player){
//                    break;
//                }
//                if(j == data.length - 1){
//                    return player;
//                }
//            }
//
//            // check down-right-wards diagonal, return true if ALL cells are current player
//            if(row == col){
//                for(int i = 0; i < data.length; i++){
//                    if(data[i][i] != player){
//                        break;
//                    }
//                    if(i == data.length - 1){
//                        return player;
//                    }
//                }
//            }
//
//            // check rith-up-wards diagonal, return true if ALL cells are current player
//            if(row + col == data.length - 1){
//                for(int i = 0; i < data.length; i++){
//                    if(data[i][data.length-1-i] != player){
//                        break;
//                    }
//                    if(i == data.length - 1){
//                        return player;
//                    }
//                }
//            }
//            return 0;
//        }
////    }
//
//    // (B) most efficient approach
//    //  user positive to represent player 1
////    user negative to represent player 2
//    // each row and col to accumulate values, if one row sum is equals to n-1,  that means all cells of this row is for player1
//    // check above for each ROW, COL, DIAGNOAL, BACK-DIAGNOAL
//    int[] rows, cols, dig;
//    int n;
//    public TicTacToe(int n) {
//        rows = new int[n];
//        cols = new int[n];
//        dig = new int[2];
//        this.n = n;
//    }
//    public int move(int row, int col, int player) {
//        return (
//                (player == 1 && rows[row]++ == n-1 | cols[col]++ == n-1 | (row == col && dig[0]++ == n-1) | (row + col == n-1 && dig[1]++ == n-1))
//                        || (player == 2 && rows[row]-- == 1-n | cols[col]-- == 1-n | (row == col && dig[0]-- == 1-n) | (row + col == n-1 && dig[1]-- == 1-n))
//        ) ? player : 0;
//    }
//
//
//}
