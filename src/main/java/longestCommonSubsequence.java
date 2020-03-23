class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        
        // xx-est is meant for dynamic programming
        // x keys for DP
        
        // 1st, declear a DP table for bottom up
        
        // 2nd set global value 
        
        // ================
        // for top down, use memo
        int m = text1.length(); //[!!!!] it's "length()" method for String
        int n = text2.length();
        int[][] memo = new int[m+1][n+1];

        for(int i=0;i<m;i++){  //[!!!!] SHould be "<=", rather than "<"
            for(int j=0;j<n;j++){
                // if(i==0||j==0){      //[!!!!!222] here is no need, because default value in array is zero
                //     // 1st col or 1st row, set to 0
                //     memo[i][j]=0;
                // }else{
                    if(text1.charAt(i)==text2.charAt(j)){ 
                        memo[i+1][j+1] = 1 + memo[i][j]; // [!!!!4444] Be aware it's assign i,j value from previous one, e.g i-1, j-1
                       
                    }else{
                        // current char is different, so go to carry previous biggest value from either left or up
                        memo[i+1][j+1] = Math.max(memo[i+1][j],memo[i][j+1]);
                    }
                // }
            }
        }
        return memo[m][n];
        
    }
    /*
    Space Optimization

Obviously, the code in method 1 only needs information of previous row to update current row. So we just use a two-row 2D array to save and update the matching results for chars in s1 and s2.

Note: use k ^ 1 and k ^= 1 to switch between dp[0] (row 0) and dp[1] (row 1).

    public int longestCommonSubsequence(String s1, String s2) {
        int m = s1.length(), n = s2.length();
        if (m < n)  return longestCommonSubsequence(s2, s1);
        int[][] dp = new int[2][n + 1];
        for (int i = 0, k = 1; i < m; ++i, k ^= 1)
            for (int j = 0; j < n; ++j)
                if (s1.charAt(i) == s2.charAt(j)) dp[k][j + 1] = 1 + dp[k ^ 1][j];
                else dp[k][j + 1] = Math.max(dp[k ^ 1][j + 1], dp[k][j]);
        return dp[m % 2][n];
    }
    */
}
