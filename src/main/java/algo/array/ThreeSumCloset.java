package algo.array;

import java.util.Arrays;

/**
 * @author todd
 * @at 20220208 0919
 */
public class ThreeSumCloset {

    public int threeSumClosestLC(int[] num, int target) {
        int result = num[0] + num[1] + num[num.length - 1];
        Arrays.sort(num);
        for (int i = 0; i < num.length - 2; i++) {
            int start = i + 1, end = num.length - 1;
            while (start < end) {
                int sum = num[i] + num[start] + num[end];
                if (sum > target) {
                    end--;
                } else {
                    start++;
                }
                if (Math.abs(sum - target) < Math.abs(result - target)) {
                    result = sum;
                }
            }
        }
        return result;
    }

    public int threeSumClosest(int[] nums, int target) {
        if(nums==null || nums.length<1){
            return target;
        }

        //int minInt=Integer.MAX_VALUE;
        // instead, (1) define guess result directly
        // (2) put the result BEFORE sort
        int result = nums[0]+nums[1]+nums[nums.length-1];

        //sort array first
        Arrays.sort(nums);

        // (3) we should loop whole array as outter loop and while loop as inner one to expand
        for(int i=0;i<nums.length-2;i++){
            int l=i+1, r=nums.length-1;
            while(l<r){
                // (4) the sum is from 3 points, left, right and current cursor i
                int curSum=nums[l]+nums[i]+nums[r];
                if(curSum==target)
                    // (6) this is a bug, this question expect actual 'sum' rather than 'closed diff' (which is 0)
                    return 0;
                else if (curSum>target)
                    r--;
                else
                    l++;

                // (5) this is a bug, it's actually assign abs(result-target) to result, it should be result itself
//                result = Math.min(Math.abs(curSum-target), Math.abs(result-target));
                if (Math.abs(curSum - target) < Math.abs(result - target)) {
                    result = curSum;
                }
            }

        }

        // int l=0,r=nums[nums.length-1], p=0;
        // while(l<r){
        //     int curInt=nums[l]+nums[p]+nums[r];
        //     if(curInt==target){
        //         return 0;
        //     }else if(curInt>target){
        //         r--;
        //     }else{
        //         l++;
        //     }
        //     minInt=Math.min(minInt, Math.abs(curInt-target));
        // }
        return result;

    }
    public int threeSumClosestDummy(int[] nums, int target) {
        if(nums==null || nums.length<1){
            return target;
        }

        //int minInt=Integer.MAX_VALUE;
        // instead, (1) define guess result directly
        // (2) put the result BEFORE sort
        int result = nums[0]+nums[1]+nums[nums.length-1];

        //sort array first
        Arrays.sort(nums);

        // (3) we should loop whole array as outter loop and while loop as inner one to expand
        for(int i=0;i<nums.length-2;i++){
            int l=i+1, r=nums.length-1;
            while(l<r){
                // (4) the sum is from 3 points, left, right and current cursor i
                int curSum=nums[l]+nums[i]+nums[r];
                if(curSum==target)
                    return 0;
                else if (curSum>target)
                    r--;
                else
                    l++;

                if(Math.abs(curSum-target)< Math.abs(result-target)){
                    result=curSum;
                }
            }

        }

        // int l=0,r=nums[nums.length-1], p=0;
        // while(l<r){
        //     int curInt=nums[l]+nums[p]+nums[r];
        //     if(curInt==target){
        //         return 0;
        //     }else if(curInt>target){
        //         r--;
        //     }else{
        //         l++;
        //     }
        //     minInt=Math.min(minInt, Math.abs(curInt-target));
        // }
        return result;

    }
}
