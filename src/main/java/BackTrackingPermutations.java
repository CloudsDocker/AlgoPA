package algo;

import java.util.ArrayList;
import java.util.List;

public class BackTrackingPermutations {
    public static void main(String[] args) {

        int[] data=new int[] {1,2,3};
        List<List<Integer>> results = new ArrayList<>();
        System.out.println("==permutate:"+permute(data));
    }

    // It's a common backtracking pattern, choose --> explore --> unchoose .
    public static List<List<Integer>> permute(int[] data){
        List<List<Integer>> results = new ArrayList<>();
        backtracking(data,results,new ArrayList<>());

        return results;
    }

    public static void backtracking(int[] data, List<List<Integer>> result, List<Integer> current){
        // 1st, for backtracking, we'd define step out step
        if(current.size()==data.length){
            // all characters filled
            result.add(new ArrayList<>(current));
        }else{
            // for loop data
            for (int i = 0; i < data.length; i++) {
                if(current.contains(data[i])){
                    //already contais this, so skip
                    continue;
                }
                // 2nd, for backtracking, chose
                current.add(data[i]);
                //3rd, for backtacking, explore
                backtracking(data,result,current);
                //last, for backtacking, unshose
                current.remove(current.size()-1);
            }
        }
    }

}
