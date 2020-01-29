import java.util.Arrays;

public class MakeChange {
    public static void main(String[] args) {

        System.out.println("args = " + makeChange_naive(17));
    }

    // Brute force solution. Go through every
// combination of coins that sum up to c to // find the minimum number
    public static int makeChange_naive(int c) {
        int[] coins = new int[]{10, 6, 1};
        if (c == 0) return 0;
        int minCoins = Integer.MAX_VALUE;
// Try removing each coin from the total and // see how many more coins are required
        for (int coin : coins) {
            // Skip a coin if it’s value is greater
            // than the amount remaining
            if (c - coin >= 0) {
                int currMinCoins = makeChange_naive(c - coin);
                if (currMinCoins < minCoins)
                    minCoins = currMinCoins;
            }
        }
        // Add back the coin removed recursively
        return minCoins + 1;
    }


    // Top down dynamic solution. Cache the values as we compute them
    // transform naive approach to top-down need:
    // overload existing method with new one accept cache
    // while existing one do two tasks: (1) initialize cache (2) call new method passing in cache
    public int makeChange_top_down(int c) {
        // Initialize cache with values as -1
        int[] cache = new int[c + 1];
        for (int i = 1; i < c + 1; i++)
            cache[i] = -1;
        return makeChange_top_down(c, cache);
    }
    // Overloaded recursive function
    private int makeChange_top_down(int c, int[] cache) {
        int[] coins = new int[]{10, 6, 1};
        // Return the value if it’s in the cache
        if (cache[c] >= 0) return cache[c];
        int minCoins = Integer.MAX_VALUE;   //declare result oppositely, e.g. question is "min", so init return value would be Integer.MAX_VALUE
        // Find the best coin
        for (int coin : coins) {
            if (c - coin >= 0) {
                int currMinCoins =
                        makeChange_top_down(c - coin, cache);
                if (currMinCoins < minCoins)
                    minCoins = currMinCoins;
            } }
        // Save the value into the cache
        cache[c] = minCoins + 1;    // add one to the return value
        return cache[c];
    }
}
