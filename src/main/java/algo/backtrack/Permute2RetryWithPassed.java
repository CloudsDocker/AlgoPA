package algo.backtrack;

import java.util.ArrayList;
import java.util.List;

public class Permute2RetryWithPassed {
    static List<List<Integer>> res = new ArrayList<>();
    public static void main(String[] args) {
        int[] data = new int[]{1,2,3};
        backtrack(data, new ArrayList<>());
        System.out.println("ourput is:"+res);
    }

    private static void backtrack(int[] data, List track) {
        // termination
        if(data.length==track.size()){
            res.add(new ArrayList(track));
        }

        // for loop
        for (int item: data) {
             if (track.contains(item)){
                 continue;
             }

             track.add(item);
             backtrack(data, track);
             track.remove(track.size()-1);

        }

    }
}
