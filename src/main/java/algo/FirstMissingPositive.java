package algo;

import java.util.Arrays;

public class FirstMissingPositive {
    /*
    41. First Missing Positive
Hard
Given an unsorted integer array, find the smallest missing positive integer.

Example 1:

Input: [1,2,0]
Output: 3
Example 2:

Input: [3,4,-1,1]
Output: 2
Example 3:

Input: [7,8,9,11,12]
Output: 1
Note:

Your algorithm should run in O(n) time and uses constant extra space.
     */
    public static void main(String[] args) {
        int[] nums=new int[]{3,10,-9,20,1,-5,0,2};
        int[] numsFails=new int[]{-1,4,2,1,9,10};

//        System.out.println("===find first missing positive number:"+findFirstMissingPositive(nums));
        System.out.println("===find first missing positive number:"+findFirstMissingPositive(numsFails));
        int[] neg=new int[]{-2,0};
        swap(neg,0,1);
        System.out.println("== test swap negative value:"+ Arrays.toString(neg));
    }

    public static int findFirstMissingPositive(int[] nums){
        if(nums==null || nums.length<1) return 1;

        int n=nums.length;

        for (int i = 0; i < n; i++) {
            // loop each number in array
            //use nums array itself, the ideal array should be {1,2,3,4}
            while(nums[i]>0 && nums[i]<=n && nums[i]!=nums[nums[i]-1]) // must be while rather than "if", as the array is keep onchangin
                //swap if nums[index] != index + 1;
                swap(nums,i, nums[i]-1);
        }

        // to find the first positive number
        for (int i = 0; i < n; i++) {
            if(nums[i]!=i+1){
                return i+1;
            }
        }
        return n+1;
    }

    private static void swap(int[] nums, int x, int y){
        nums[x] ^= nums[y];
        nums[y] ^= nums[x];
        nums[x] ^= nums[y];
    }
}
