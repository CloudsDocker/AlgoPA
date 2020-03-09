package algo;

public class climbStairs {
    /*
    70. Climbing Stairs
Easy

3470

113

Add to List

Share
You are climbing a stair case. It takes n steps to reach to the top.

Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?

Note: Given n will be a positive integer.

Example 1:

Input: 2
Output: 2
Explanation: There are two ways to climb to the top.
1. 1 step + 1 step
2. 2 steps
Example 2:

Input: 3
Output: 3
Explanation: There are three ways to climb to the top.
1. 1 step + 1 step + 1 step
2. 1 step + 2 steps
3. 2 steps + 1 step
     */

    public int climbStairs(int n) {
        // base cases
        if(n <= 0) return 0;
        if(n == 1) return 1;
        if(n == 2) return 2;

        int one_step_before = 2;
        int two_steps_before = 1;
        int all_ways = 0;

        for(int i=2; i<n; i++){
            all_ways = one_step_before + two_steps_before;
            two_steps_before = one_step_before;
            one_step_before = all_ways;
        }
        return all_ways;
    }

    /*
    Same simple algorithm written in every offered language. Variable a tells you the number of ways to reach the current step,
     'and b tells you the number of ways to reach the next step. So for the situation one step further up, the old b becomes the new a,
     and the new b is the old a+b, since that new step can be reached by climbing 1 step from what b represented or 2 steps from what a represented.

Ruby wins, and "the C languages" all look the same.
     */
    public int climbStairs_Concise(int n) {
        int a = 1, b = 1;
        while (n-- > 0)
            a = (b += a) - a;
        return a;
    }
}
