package algo.dp;

public class CoinChange1stTry {
    public static void main(String[] args) {
        int[] coins = new int[]{1,2,5};
        int amount =11;
        System.out.println("fewest number of coins to form amount is : "+coinChange(coins,amount));
    }

    private static String coinChange(int[] coins, int amount) {

        // base case
        if(amount<=0){
            return "0";
        }
        int[] dp=new int[amount+1];
        for(int i=1;i<=amount;i++){
            for(int j=0;j<coins.length;j++){
                if(coins[j]<=i){
                    dp[i]=Math.min(dp[i],1+dp[i-coins[j]]);
                }
            }
//            dp[i]=dp[i-]
        }

        return dp[amount]==0?"-1":String.valueOf(dp[amount]);
    }
}
