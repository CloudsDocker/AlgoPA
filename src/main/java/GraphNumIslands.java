package algo;

public class GraphNumIslands {
    public static void main(String[] args) {
        System.out.printf("====Number of Isalands====");
        /*
        Input:
11110
11010
11000
00000
         */
        int[][] grid = {{1,1,1,1,0},{1,1,0,1,0},{1,1,0,0,0},{0,0,0,0,0}};

        /*
        Input for example 2
        Input:
11000
11000
00100
00011

Output: 3
         */
        int[][] grid3 = {{1,1,0,0,0},{1,1,0,0,0},{0,0,1,0,0},{0,0,0,1,1}};
//        System.out.println("numberOfIslands is :"+numberOfIslands(grid));
        System.out.println("numberOfIslands3 is :"+numberOfIslands(grid3));
    }

    static int numberOfIslands(int[][] grid){
        int number = 0;

        if(grid==null ||  grid.length <0 || grid[0].length<0 ) {
            return 0;
        }
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j <grid[i].length ; j++) {
                if(grid[i][j]==1) {
                    // DFS to clear adjcent "1" to avoid dup counting
                    DFS(grid, i, j);
                    ++number;
                }
            }
        }
        return number;
    }

    static void DFS(int[][] grid, int x, int y){
        //edge case
        if(grid==null || x<0 || x >= grid.length || y<0 || y>=grid[0].length || grid[x][y]==0) { //[!!!!] should >= length, not ">"
//      if(grid==null || x<0 || x > grid.length || y<0 || y>grid[0].length || grid[x][y]==0) {
            // return if cursor node is NOT 1
            return;
        }

        // means current cursor node is "1"
        grid[x][y]=0; // mark this cell as visied

        // check all adjacent cells
        DFS(grid, x-1, y);
        DFS(grid, x+1, y);
        DFS(grid, x, y-1);
        DFS(grid, x, y+1);
    }
}
