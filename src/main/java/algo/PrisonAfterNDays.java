package algo;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class PrisonAfterNDays {



    /// Best easy understand version
        public int[] prisonAfterNDays(int[] cells, int N) {
            if(cells==null || cells.length==0 || N<=0) return cells;
            boolean hasCycle = false;
            int cycle = 0;
            HashSet<String> set = new HashSet<>();
            for(int i=0;i<N;i++){
                int[] next = nextDay(cells);
                String key = Arrays.toString(next);
                if(!set.contains(key)){ //store cell state
                    set.add(key);
                    cycle++;
                }
                else{ //hit a cycle
                    hasCycle = true;
                    break;
                }
                cells = next;
            }
            if(hasCycle){
                N%=cycle;
                for(int i=0;i<N;i++){
                    cells = nextDay(cells);
                }
            }
            return cells;
        }

        private int[] nextDay(int[] cells){
            int[] tmp = new int[cells.length];
            for(int i=1;i<cells.length-1;i++){
                tmp[i]=cells[i-1]==cells[i+1]?1:0;
            }
            return tmp;
        }

    public int[] prisonAfterNDays_smartButHardToRepeat(int[] cells, int N) {
        Map<String, Integer> seen = new HashMap<>();
        while (N > 0) {
            int[] cells2 = new int[8];
            seen.put(Arrays.toString(cells), N--);
            for (int i = 1; i < 7; ++i)
                cells2[i] = cells[i - 1] == cells[i + 1] ? 1 : 0;
            cells = cells2;
            if (seen.containsKey(Arrays.toString(cells))) {
                N %= seen.get(Arrays.toString(cells)) - N;
                //    seen.get(Arrays.toString(cells)) is the last time when the same cells appear, seen.get(Arrays.toString(cells))-N is the cycle length. %= cuts down the loop times.
            }
        }
        return cells;
    }
/*
Solution 2
I tried to find the pattern of the loop.
Well, the length of loop can be 1, 7, or 14.

So once we enter the loop, every 14 steps must be the same state.

The length of cells is even,
so for any state, we can find a previous state.
So all states are in a loop.

It means that, after a single step from the initial state, we enter the loop.

Java


 */
public int[] prisonAfterNDays_better(int[] cells, int N) {
    for (N = (N - 1) % 14 + 1; N > 0; --N) {
        int[] cells2 = new int[8];
        for (int i = 1; i < 7; ++i)
            cells2[i] = cells[i - 1] == cells[i + 1] ? 1 : 0;
        cells = cells2;
    }
    return cells;
}

    public int[] prisonAfterNDays_v2(int[] cells, int N) {
            Map<Integer, Integer> seen = new HashMap();

            // state  = integer representing state of prison
            int state = 0;
            for (int i = 0; i < 8; ++i) {
                if (cells[i] > 0)
                    state ^= 1 << i;
            }

            // While days remaining, simulate a day
            while (N > 0) {
                // If this is a cycle, fast forward by
                // seen.get(state) - N, the period of the cycle.
                if (seen.containsKey(state)) {
                    N %= seen.get(state) - N;
                }
                seen.put(state, N);

                if (N >= 1) {
                    N--;
                    state = nextDay(state);
                }
            }

            // Convert the state back to the required answer.
            int[] ans = new int[8];
            for (int i = 0; i < 8; ++i) {
                if (((state >> i) & 1) > 0) {
                    ans[i] = 1;
                }
            }

            return ans;
        }

        public int nextDay(int state) {
            int ans = 0;

            // We only loop from 1 to 6 because 0 and 7 are impossible,
            // as those cells only have one neighbor.
            for (int i = 1; i <= 6; ++i) {
                if (((state >> (i-1)) & 1) == ((state >> (i+1)) & 1)) {
                    ans ^= 1 << i;
                }
            }

            return ans;
        }
    }

