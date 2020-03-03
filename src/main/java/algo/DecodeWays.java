package algo;

public class DecodeWays {
    public static void main(String[] args) {
        /*

        Similar questions:
62. Unique Paths
70. Climbing Stairs
509. Fibonacci Number

Practice them in a row for better understanding 😉




        91. Decode Ways
A message containing letters from A-Z is being encoded to numbers using the following mapping:

'A' -> 1
'B' -> 2
...
'Z' -> 26
Given a non-empty string containing only digits, determine the total number of ways to decode it.

Example 1:

Input: "12"
Output: 2
Explanation: It could be decoded as "AB" (1 2) or "L" (12).
Example 2:

Input: "226"
Output: 3
Explanation: It could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).
         */
        DecodeWays inst = new DecodeWays();
        System.out.println(" decode ways: "+ inst.numDecodings("12"));

    }






    /*
"""
s = 123

build up from right =>

num_ways ("") => 1 (empty string can be represented by empty string) (i.e. num_ways[n] = 1) NOTE: only for build up with a valid string. Empty string on it's own doesn't need to be decoded.
num_ways ("3") => 1 (only one way), i.e. num_ways[n-1] = 1
num_ways ("23") => "23" or "2"-"3",
num_ways ("33") => "3""3"
num_ways ("123") => "12"(num_ways("3")) + "1"("num_ways("23")) (i.e. num_ways[i+2] + num_ways[i+1])
num_ways ("323") => "3"(num_ways("23")) (i.e. num_ways[i+1])

so basically if s[i:i+1] (both included) <= 26,
num_ways[i+2] + num_ways[i+1]
else:
num_ways[i+1]

case with 0:

num_ways ("103")
num_ways ("3") => 1 (only one way)
num_ways ("03") => 0 (can't decode 0)
num_ways ("003") => "00"(num_ways("3")) + "0"(num_ways("03")) => no way to decode "00" = 0 + 0
num_ways ("103") => "10"(num_ways("3")) + "1"(num_ways("03")) => num_ways[i+2] + num_ways[i+1](= 0 in this case)
num_ways ("1003") => "10"(num_ways("03")) + "1"(num_ways("003")) => same eq = 0(no way to decode "03") + 0(no way to decode 003)

Therefore, if i = '0', let memo[i] = 0, also implements for a string where the ith character == '0', string[i:end] can be decoded in 0 ways.
"""
     */
//    public class Solution {
        public int numDecodings(String s) {
            int n = s.length();
            if (n == 0) return 0;

            int[] memo = new int[n+1];
            memo[n]  = 1;
            memo[n-1] = s.charAt(n-1) != '0' ? 1 : 0;

            for (int i = n - 2; i >= 0; i--)
                if (s.charAt(i) == '0') continue;
                else memo[i] = (Integer.parseInt(s.substring(i,i+2))<=26) ? memo[i+1]+memo[i+2] : memo[i+1];

            return memo[0];
        }
//    }


     /*
    Thank you so much for this clean and intuitive solution!!

I wrote some notes for myself reference, hope it might help someone to understand this solution.

dp[i]: represents possible decode ways to the ith char(include i), whose index in string is i-1

Base case: dp[0] = 1 is just for creating base; dp[1], when there's one character, if it is not zero, it can only be 1 decode way. If it is 0, there will be no decode ways.

Here only need to look at at most two digits before i, cuz biggest valid code is 26, which has two digits.

For dp[i]: to avoid index out of boundry, extract substring of (i-1,i)- which is the ith char(index in String is i-1) and substring(i-2, i)

First check if substring (i-1,i) is 0 or not. If it is 0, skip it, continue right to check substring (i-2,i), cuz 0 can only be decode by being together with the char before 0.

Second, check if substring (i-2,i) falls in 10~26. If it does, means there are dp[i-2] more new decode ways.

Time: should be O(n), where n is the length of String
Space: should be O(n), where n is the length of String
     */

    public int numDecodings_v2(String s) {
        // this is one DP question, so create DP matrxi first
        int[] dp = new int[s.length()+1];

        // base case
        dp[0]=1;
        // for only one char, if first char is 0, which is not in the mapping list, so return 0, otherwise return 1
        dp[1]=s.charAt(0)=='0'?0:1;
        int m=s.length();

        for (int i = 2; i <=m ; i++) {
            int digitOne=Integer.valueOf(s.substring(i-1,i));
            int digitTwo=Integer.valueOf(s.substring(i-2,i));
            if(digitOne>=1){
                dp[i] = dp[i] +dp[i-1]; // add one to DP as take this single digit into account
            }

            if(digitTwo>=10 && digitTwo<=26){
                dp[i] = dp[i] + dp[i-2];
            }
        }
        return  dp[m];
    }

}
