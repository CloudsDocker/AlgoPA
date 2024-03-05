package algo.dp;

import java.util.Arrays;

public class CoinChanges1stTry {
    public static void main(String[] args) {
        int[] coins = new int[]{1,2,5};
        int amount = 12;
        System.out.println("result of coins change for "+amount+" is:"+coinChange(coins, amount));
    }

    private static int coinChange(int[] coins, int amount) {
        int[] dp=new int[amount+1];

        // !!! (1) to calculate minium value, firslty to init all array to infinte value
        Arrays.fill(dp, amount+1);

        //!!! (2) the base case should be AFTER init with max values
        // base case
        dp[0]=0;

        for (int i = 1; i <dp.length; i++) {

            for (int j = 0; j < coins.length; j++) {
                if(coins[j]>i){
                    continue;
                }
                dp[i]=Math.min(dp[i],1+dp[i-coins[j]]);
            }
        }
        return dp[amount]>amount?-1:dp[amount];
    }
}
