package algo;

import java.util.*;

public class CombinationSum {
    public static void main(String[] args) {

//        int[] candidates = new int[]{10, 1, 2, 7, 6, 1, 5};
        int[] candidates = new int[]{ 2, 3, 6, 7};
        System.out.println("combination sum is : "+findCombination(candidates,7));


    }

    public static Set<List<Integer>> findCombination(int[] candidates, int target){
        Set<List<Integer>> results=new HashSet<>();
        Arrays.sort(candidates);
        findCombinationBackTracking(candidates,0,target,new ArrayList<Integer>(), results);
        return results;
    }

    public static void findCombinationBackTracking(int[] candidates, int start, int target,List<Integer> current, Set<List<Integer>> results){

        //base case
        if(target==0){
            results.add(new ArrayList<>(current));
        }else if(target<0){
            return;
        }else {
            // iterate candiate to combine to sum
            for (int i = start; i < candidates.length; i++) {
            // add current number to 'current list'
                current.add(candidates[i]);
                findCombinationBackTracking(candidates,i,target-candidates[i],current,results);
//                current.remove(candidates[i]);
                current.remove(current.size()-1);
            }
        }
    }

}
