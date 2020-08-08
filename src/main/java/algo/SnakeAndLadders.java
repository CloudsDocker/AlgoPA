package algo;

import java.util.LinkedList;
import java.util.Queue;

public class SnakeAndLadders {
    public static void main(String[] args) {
        System.out.println("snake and ladders");
        int[][] board=new int[][]{
                {-1,-1,-1,-1,-1,-1},
                {-1,-1,-1,-1,-1,-1},
                {-1,-1,-1,-1,-1,-1},
                {-1,35,-1,-1,13,-1},
                {-1,-1,-1,-1,-1,-1},
                {-1,15,-1,-1,-1,-1}
        };
        System.out.println(new SnakeAndLadders().snakesAndLadders(board));
    }
    public int snakesAndLadders(int[][] board) {
        int n = board.length;
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(1);
        boolean[] visited = new boolean[n * n + 1];
        for (int move = 0; !queue.isEmpty(); move++) {
            for (int size = queue.size(); size > 0; size--) {
                int num = queue.poll();
                if (visited[num]) continue;
                visited[num] = true;
                if (num == n * n) return move;
                for (int i = 1; i <= 6 && num + i <= n * n; i++) {
                    int next = num + i;
                    int value = getBoardValue(board, next);
                    if (value > 0) next = value;
                    if (!visited[next]) queue.offer(next);
                }
            }
        }
        return -1;
    }

    private int getBoardValue(int[][] board, int num) {
        int n = board.length;
        int oldRow = (num - 1) / n;
        int row = n-1 -oldRow;
        int oldCol = (num-1) % n;
        int col = oldRow % 2 == 0 ? oldCol : n - 1 - oldCol;

        return board[row][col];
    }


//
//    public int snakesAndLadders(int[][] board) {
//        //base
//        if(board==null || board.length<1) return -1;
//        int n=board.length;
//
//
//        // construct val board
//        int[][] vals=new int[n][n];
//        boolean bZig=true;
//        int num=0;
//        for(int i=n-1;i>=0;i--){
//            for(int j=0;j<n;j++){
//                int col=bZig?j:n-j-1;
//                vals[i][col]=++num;
////                bZig=!bZig; //[!!! this line should be AFTER for loop of "j"
//            }
//            bZig=!bZig;
//        }
//        return -1;
//    }
}
