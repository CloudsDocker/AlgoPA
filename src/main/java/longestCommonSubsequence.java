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
                    if(text1.charAt(i)==text2.charAt(j)){ ///[!!!!3333] here should be "i-1" ,as it's 1 based, and index "0" already been handled in previous "if"
                        // same character, so increase values in DP
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
}
