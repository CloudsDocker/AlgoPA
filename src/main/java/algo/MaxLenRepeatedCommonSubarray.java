package algo;

import java.util.Arrays;

public class MaxLenRepeatedCommonSubarray {

    public static void main(String[] args) {
//        int[] A = new int[]{1,2,3,2,1};
//        int[] B= new int[]{3,2,1,4,7};

        int[] A = new int[]{1,2,3,2,1,4,7};
        int[] B= new int[]{3,2,1,4,7};

//        System.out.println("findLength_naive = " + findLength_naive(A,B));
        System.out.println("findLength_dp = " + findLength_dp(A,B));
    }

//    class Solution {

    //naive solution, which may exceed time limit
public static int findLength_naive(int[] A, int[] B) {
    // [!] for questions with "max", "min", key steps:
    // - have a variable to keep global value
    int maxLen = 0, step = 0, lenA = A.length, lenB = B.length, iLast=0,jLast=0;
    for (int i = 0; i < lenA; i++) {
        for (int j = 0; j < lenB; j++) {
            step = 0;
            if (A[i] == B[j]) {
                while ((i + step) < lenA && (j + step) < lenB) { //[!] (1) most common error for while loop is dead loop, to keep variable increasing
                    // (2) difference of "<" and "<=", if use "<=", "n-1" is required for array index
                    // (3) for while loop, must define 'exit strategy' e.g. continue or break
                    if (A[i + step] == B[j + step] ) {
                        maxLen = Math.max(maxLen, step);
                        step++;
                    } else {
                        break;
                    }
                }
            }

        }
    }
    return maxLen;
}
//    }

    public static int findLength_dp(int[] A, int[] B) {
        // for dynamic programing, normally it compare itself with its sibling, using max/min
        // try to construct a matrix to keep track of path
        int m=A.length,n=B.length,max=0;
        int[][] memo = new int[m+1][n+1]; // "+1" to keep extra space
        for(int i = 0;i <= m;i++) {
            for (int j = 0; j <= n; j++) {
                //for DP, firstly to setup begin point
                if(i==0 || j==0) {
                    memo[i][j]=0;
                }else{
                    if(A[i-1]==B[j-1]){ // it they are same
                        memo[i][j] = 1+ memo[i-1][j-1]; // increase one to cache
                        max = Math.max(max,memo[i][j]); // get global max
                    }
                }
            }
        }
        return max;
    }



    class Solution {
        public int findLength(int[] A, int[] B) {
            if(A == null||B == null) return 0;
            int m = A.length;
            int n = B.length;
            int max = 0;
            //dp[i][j] is the length of longest common subarray ending with nums[i] and nums[j]
            int[][] dp = new int[m + 1][n + 1];
            for(int i = 0;i <= m;i++){
                for(int j = 0;j <= n;j++){
                    if(i == 0 || j == 0){
                        dp[i][j] = 0;
                    }
                    else{
                        if(A[i - 1] == B[j - 1]){
                            dp[i][j] = 1 + dp[i - 1][j - 1];
                            max = Math.max(max,dp[i][j]);
                        }
                    }
                }
            }
            return max;
        }
    }

}
