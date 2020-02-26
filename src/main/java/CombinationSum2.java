class CombinationSum2 {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(candidates); // here is key to make array increasing
        findCombination(candidates,0,target,new ArrayList<>(),result);
        return result;
    }
    
    public void findCombination(int[] candidates, int idx, int target, List<> current, List<List<>> result){
        //base case
        if(target == 0){
            // found correct combination
            result.add(current);
            return; // should return right away after add
        }
        
        // base case 2
        if(target<0){
            // last element lead to combination>target
            return;
        }
        
        for(int i=idx;i<candidates.length;i++){
            // loop to try combination by DFS
            if(i==idx || candidates[i]!=candidates[i-1]){
                // here is key for "non dup element"
                // as first loop is always unique, no dup
                // for non first loop, check it with previus value
                current.add(candidates[i]); // Not same as previous one
                findCombination(candidates,i+1, target-candidates[i], current, result); // here will DFS try to keep on adding new element to current
                current.remove(candidates.length-1);// when above line returned, it means last element is too big
            }
        }
    }
}
