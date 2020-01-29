import java.util.Arrays;

public class Knapsack {
    public static void main(String[] args) {
//        int val[] = {22, 20, 15, 30, 24, 54, 21, 32, 18, 25};
//        int wt[] = {4, 2, 3, 5, 5, 6, 9, 7, 8, 10};
       int val[] = {2, 3, 5, 7};
       int wt[] = {1, 5, 2, 4};
        System.out.println("args = " + knapsack01(wt,val,10));
        System.out.println("args = " + bottomUpDP(val,wt,10));
    }

    public static int bottomUpDP(int val[], int wt[], int W){
        int K[][] = new int[val.length+1][W+1];
        for(int i=0; i <= val.length; i++){
            for(int j=0; j <= W; j++){
                if(i == 0 || j == 0){
                    K[i][j] = 0;
                    continue;
                }
                if(j - wt[i-1] >= 0){
                    K[i][j] = Math.max(K[i-1][j], K[i-1][j-wt[i-1]] + val[i-1]);
                }else{
                    K[i][j] = K[i-1][j];
                }
            }
        }
        return K[val.length][W];
    }

    private static int knapsack01(int[] weights, int[] value, int quota) {
        // we are using dynamic programming bottom up

        // one tab to keep track of value, size is quota + 1
        int[][] dp = new int[value.length+1][quota+1];

        // as size is actual size + 1, so here is "<=" , rather than "<"
        for(int i=0;i<=value.length;i++){
            for (int j =0;j<=quota;j++){
                //base value
                if(i==0 || j==0){
                    // initilize first line and first column to '0'
                    dp[i][j] = 0;
                    continue;
                }

                // non zero
                if(j>=weights[i-1]){
                    // current weight not bigger than current quota
                    // so add it to our backtrack
                    // get the max one of (1) Not include , (2) include this node
                    dp[i][j]= Math.max(dp[i-1][j], dp[i-1][j-weights[i-1]]+value[i-1]);

                }else{
                    // required weight is less than provided, so skip this
                    dp[i][j] = dp[i-1][j]; //use value (j) of previous (i-1
                }
            }

        }
        return dp[value.length][quota];
    }
}
