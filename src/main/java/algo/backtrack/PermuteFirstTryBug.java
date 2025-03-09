package algo.backtrack;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class PermuteFirstTryBug {
    static List<List<Integer>> res  = new ArrayList();
    public static void main(String[] args) {
        int[] data = new int[]{1,2,3};
permute(data);
//        backtrack(data, new LinkedList<>());
        System.out.println("ourput is:"+res);
    }

    private static List<List<Integer>> permute(int[] data){
        List track = new ArrayList();
        backtrack(data,track);
        return res;
    }

    private static void backtrack(int[] data, List track) {
        // termination
        if (data.length == track.size()) {
            res.add(new LinkedList(track));
//            return;
        }else {

            // add to candidate track

            // for loop
            for (int i = 0; i < data.length; i++) {
                if (track.contains(data[i])) continue; // skip if already contains
                track.add(data[i]);
                backtrack(data, track); // BUG: here should pas in track rather then final result
//            track.remove(data[i]); // could be track.removeLast()
                track.remove(track.size()-1); // could be track.removeLast(

            }
        }

        // remove candidate track
    }
}
