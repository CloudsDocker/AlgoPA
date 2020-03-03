package algo;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BackTrackingPermutationII {

    public static void main(String[] args) {
        int[] data = new int[]{1,1,2};
        System.out.println("ourput is:"+permutation(data));
    }

    public static List<List<Integer>> permutation(int[] data){
        /*
        47. Permutations II Medium
Given a collection of numbers that might contain duplicates, return all possible unique permutations.
Example:
Input: [1,1,2]
Output:
[
  [1,1,2],
  [1,2,1],
  [2,1,1]
]

         */
        List<List<Integer>> results = new ArrayList<>();
        Arrays.sort(data);// [!!!] this is NEEDED, as use to check dup with neighbor
        backtracking(data,new ArrayList<>(), results, new boolean[data.length]);
        return results;
    }

    public static void backtracking(int[] data, List<Integer> current, List<List<Integer>> results, boolean[] visited){
        // step out step
        if(current.size()==data.length){
            results.add(new ArrayList<>(current));
        }else{
            for (int i = 0; i < data.length; i++) {

                if(visited[i] || (i>0 && data[i]==data[i-1] && !visited[i-1])) continue;
                visited[i]=true;
                // 1st, chose
                current.add(data[i]);
                // 2nd explore
                backtracking(data,current, results,visited);

                // 3rd unchose
                visited[i]=false;
                current.remove(current.size()-1);

            }
        }

    }
}
