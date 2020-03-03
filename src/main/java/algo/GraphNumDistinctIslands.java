package algo;

import java.util.HashSet;
import java.util.Set;

public class GraphNumDistinctIslands{


private static int rows, cols;
    private static int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public int numDistinctIslands_best(int[][] grid) {

        cols = grid[0].length;
        rows = grid.length;
        Set<String> uniqueShapes = new HashSet<>(); // Unique shpes.
        StringBuilder shape;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1) {
                    grid[i][j] = 0; // mark it as 'visited'
                    shape = new StringBuilder("s"); //'s' indicate Start
                    dfsTraversal(i, j, grid, shape);
                    uniqueShapes.add(shape.toString());
                }
            }
        }

        return uniqueShapes.size();
    }


    private static void dfsTraversal(int x, int y, int[][] matrix, StringBuilder shape) {

        for (int i = 0; i < directions.length; i++) {
            int nx = x + directions[i][0];
            int ny = y + directions[i][1];
            if (nx >= 0 && ny >= 0 && nx < rows && ny < cols) {
                if (matrix[nx][ny] == 1) {
                    matrix[nx][ny] = 0; // mark as 'visited'
                    shape.append(i);
                    dfsTraversal(nx, ny, matrix, shape);
                }
            }
        }
        shape.append("_");

    }



    //=======
    class Solution {

    int[][] dirs= new int[][]{{1,0},{0,1},{-1,0},{0,-1}};
    public int numDistinctIslands(int[][] grid) {
         Set<String> set= new HashSet<>();
        int res=0;

        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[0].length;j++){
                if(grid[i][j]==1) {
                    StringBuilder sb= new StringBuilder();
                    helper(grid,i,j,0,0, sb);
                    String s=sb.toString();
                    if(!set.contains(s)){
                    res++;
                    set.add(s);
}
                }
            }
        }
            return res;
    }

    public  void helper(int[][] grid,int i,int j, int xpos, int ypos,StringBuilder sb){
        grid[i][j]=0;
        sb.append(xpos+""+ypos);
        for(int[] dir : dirs){
            int x=i+dir[0];
            int y=j+dir[1];
            if(x<0 || y<0 || x>=grid.length || y>=grid[0].length || grid[x][y]==0) continue;
            helper(grid,x,y,xpos+dir[0],ypos+dir[1],sb);
        }
    }
}
//UPDATE: We can use direction string instead of using number string in set.
//Below is @wavy code using direction string.

public int numDistinctIslands(int[][] grid) {
    Set<String> set = new HashSet<>();
    for(int i = 0; i < grid.length; i++) {
        for(int j = 0; j < grid[i].length; j++) {
            if(grid[i][j] != 0) {
                StringBuilder sb = new StringBuilder();
                dfs(grid, i, j, sb, "o"); // origin
                grid[i][j] = 0;
                set.add(sb.toString());
            }
        }
    }
    return set.size();
}
private void dfs(int[][] grid, int i, int j, StringBuilder sb, String dir) {
    if(i < 0 || i == grid.length || j < 0 || j == grid[i].length
       || grid[i][j] == 0) return;
    sb.append(dir);
    grid[i][j] = 0;
    dfs(grid, i-1, j, sb, "u");
    dfs(grid, i+1, j, sb, "d");
    dfs(grid, i, j-1, sb, "l");
    dfs(grid, i, j+1, sb, "r");
    sb.append("b"); // back
}
}