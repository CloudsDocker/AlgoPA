package algo;

public class LongestParenthese {
    public static void main(String[] args) {
        System.out.println("longest parenthese");
        String str="()(()";
         str=")()())";
        System.out.println(longestParenthese(str));
    }

    static int longestParenthese(String str){
        int max=0;
        char[] chars=str.toCharArray();
        int[] dp=new int[chars.length+1]; // DP[i] means longest parenthese on ith (1 based) char
        int openned=0;
        for(int i=1;i<=chars.length;i++){
            if(chars[i-1]==')'){
                if(--openned >=0){
                    // means matched
                    dp[i] = 2+dp[i-1]; // add 2 chars of matched
                    dp[i] += dp[i-dp[i]]; // add previous matches,
                    max=Math.max(max, dp[i]);
                }
                else
                    openned=0;

            }else{
                // char is '('
                openned++;
            }
        }

        return max;
    }
}
