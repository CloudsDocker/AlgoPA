package algo;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class GraphRottingOrange {
    public static void main(String[] args) {
        /*
        In a given grid, each cell can have one of three values:

the value 0 representing an empty cell;
the value 1 representing a fresh orange;
the value 2 representing a rotten orange.
Every minute, any fresh orange that is adjacent (4-directionally) to a rotten orange becomes rotten.

Return the minimum number of minutes that must elapse until no cell has a fresh orange.  If this is impossible, return -1 instead.



Example 1:
Input: [[2,1,1],[1,1,0],[0,1,1]]
Output: 4

Example 2:
Input: [[2,1,1],[0,1,1],[1,0,1]]
Output: -1
Explanation:  The orange in the bottom left corner (row 2, column 0) is never rotten, because rotting only happens 4-directionally.

Example 3:
Input: [[0,2]]
Output: 0
Explanation:  Since there are already no fresh oranges at minute 0, the answer is just 0.


Note:

1 <= grid.length <= 10
1 <= grid[0].length <= 10
grid[i][j] is only 0, 1, or 2.
         */

        GraphRottingOrange inst = new GraphRottingOrange();
        int[][] grid = new int[][]{{2, 1, 1}, {1, 1, 0}, {0, 1, 1}};
        System.out.println("===output of findRottenMinutes:" + inst.orangesRotting(grid));

    }



    public int orangesRotting_Iterative(int[][] grid) {
        if(grid == null || grid.length == 0) return 0;
        int rows = grid.length;
        int cols = grid[0].length;
        Queue<int[]> queue = new LinkedList<>();
        int count_fresh = 0;
        //Put the position of all rotten oranges in queue
        //count the number of fresh oranges
        for(int i = 0 ; i < rows ; i++) {
            for(int j = 0 ; j < cols ; j++) {
                if(grid[i][j] == 2) {
                    queue.offer(new int[]{i , j});
                }
                else if(grid[i][j] == 1) {
                    count_fresh++;
                }
            }
        }
        //if count of fresh oranges is zero --> return 0
        if(count_fresh == 0) return 0;
        int count = 0;
        int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};
        //bfs starting from initially rotten oranges
        while(!queue.isEmpty()) {
            ++count;
            int size = queue.size();
            for(int i = 0 ; i < size ; i++) {
                int[] point = queue.poll();
                for(int dir[] : dirs) {
                    int x = point[0] + dir[0];
                    int y = point[1] + dir[1];
                    //if x or y is out of bound
                    //or the orange at (x , y) is already rotten
                    //or the cell at (x , y) is empty
                    //we do nothing
                    if(x < 0 || y < 0 || x >= rows || y >= cols || grid[x][y] == 0 || grid[x][y] == 2) continue;
                    //mark the orange at (x , y) as rotten
                    grid[x][y] = 2;
                    //put the new rotten orange at (x , y) in queue
                    queue.offer(new int[]{x , y});
                    //decrease the count of fresh oranges by 1
                    count_fresh--;
                }
            }
        }
        return count_fresh == 0 ? count-1 : -1;
    }


    public int orangesRotting(int[][] grid) {

        int m = grid.length;
        int n = grid[0].length;
        List<String> listRotten = new ArrayList<>();
        List<String> listFresh = new ArrayList<>();
        int nMinutes = 0;

        //firstly, find and enlist rotten ones
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 2) {
                    //current cell is a rotten tomato, so check adjacent and contract them
                    listRotten.add(i + "" + j);
                } else if (grid[i][j] == 1) {
                    // fresh tomato, to record it , so check zero of this list to confirm ALL tomato got infected
                    listFresh.add(i + "" + j);
                }
            }
        }

        // loop until empty of fresh ones
        while (!listFresh.isEmpty()) {
            List<String> infected = new ArrayList<>();
            for (String strRotten : listRotten) {
                int x = strRotten.charAt(0) - '0';
                int y = strRotten.charAt(1) - '0';
                //to search 4 directions both vertically and horizentoally
                int[][] directions = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
                for (int[] direction : directions) {
                    int newX = x + direction[0];
                    int newY = y + direction[1];
                    String newLoc = newX + "" + newY;
                    if (listFresh.contains(newLoc)) {
                        // make new tomato as rotten
                        listFresh.remove(newLoc);
//                        listRotten.add(newLoc);
                        infected.add(newLoc); // add to infected, rather than Rotten to avoid "ConcurrentModificationException" as it's our loop list
                    }
                }
            }

            // return -1 in case no more been infected
            if (infected.isEmpty()) {
                return -1;
            }

            // assign infected to listRotten to further check
            listRotten=infected;
            ++nMinutes;
        }


        return nMinutes;
    }
}
