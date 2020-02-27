class Solution {
    public int coinChange(int[] coins, int amount) {
        // this is one DP problem, so create matrix for number of fewest numbers of coins to form the
        int[] dp = new int[amount+1]; // index of array is the amount to be calculated
        Arrays.fill(dp,amount+1); // fill DP with *invalid* value so we can update it to valid one late
        
        //base case
        dp[0]=0;
        
        for(int i=0;i<=amount;i++){ //[!!!] should be "<=", rather than "<"
            for(int j=0;j<coins.length;j++){
                // if current coin is not greater than i (current amoung to calcluate fewest number)
                if(coins[j]<=i){
                    // two options, do not take current change OR take current change
                    dp[i] = Math.min(dp[i], 1+dp[i-coins[j]]);
                }
            }
        }
        
        // if dp[amount] > amount, it means it's amount+1, which is invalid
        return dp[amount] > amount ? -1:dp[amount];
    }
}
