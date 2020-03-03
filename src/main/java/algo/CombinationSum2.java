package algo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class CombinationSum2 {
        public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
//        List<List<Integer>> result = new ArrayList<>();
//        Arrays.sort(candidates); // here is key to make array increasing
//        findCombination(candidates, 0, target, new ArrayList<Integer>(), result);
//        return result;

        Arrays.sort(candidates);
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        List<Integer> path = new ArrayList<Integer>();
        findCombination(candidates, 0, target, path, res);
        return res;
    }

    public static void findCombination(int[] candidates, int idx, int target, List<Integer> current, List<List<Integer>> result) {
        //base case
        if (target == 0) {
            // found correct combination
//            result.add(current);
            result.add(new ArrayList<>(current));  //[!!!!!] Be aware there is new ArrayList(current), rather than result.add(current);, because "current" is reference , it will be changed.
            return; // should return right away after add
        }

        // base case 2
        if (target < 0) {
            // last element lead to combination>target
            return;
        }

        for (int i = idx; i < candidates.length; i++) {
            // loop to try combination by DFS

            // appraoch 1
//            if (i == idx || candidates[i] != candidates[i - 1]) {
//                // here is key for "non dup element"
//                // as first loop is always unique, no dup
//                // for non first loop, check it with previus value
//                current.add(candidates[i]); // Not same as previous one
//                findCombination(candidates, i + 1, (target - candidates[i]), current, result); // here will DFS try to keep on adding new element to current
//                current.remove(current.size() - 1);// when above line returned, it means last element is too big// arraylist is "size()" not "length"
//            }

            //approach 2
            if(i>idx && candidates[i]==candidates[i-1]){
                continue; // to dedup
            }
            current.add(candidates[i]);
            findCombination(candidates,i+1,target-candidates[i],current,result);
            current.remove(current.size()-1);


        }
    }
        
}
