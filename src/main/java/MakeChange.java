import java.util.Arrays;

public class MakeChange {
    public static void main(String[] args) {
        System.out.println("args = " + makeChange(17));
    }
    // Brute force solution. Go through every
// combination of coins that sum up to c to // find the minimum number
    public static int makeChange(int c) {
     int[] coins = new int[]{10, 6, 1};
        if (c == 0) return 0;
        int minCoins = Integer.MAX_VALUE;
// Try removing each coin from the total and // see how many more coins are required
for (int coin : coins) {
        // Skip a coin if it’s value is greater
        // than the amount remaining
        if (c - coin >= 0) {
            int currMinCoins = makeChange(c - coin);
            if (currMinCoins < minCoins)
                minCoins = currMinCoins;
        } }
    // Add back the coin removed recursively
  return minCoins + 1;
}
}
