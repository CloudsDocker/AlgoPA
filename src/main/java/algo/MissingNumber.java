package algo;

public class MissingNumber {
    public static void main(String[] args) {
        /*
        268. Missing Number
Given an array containing n distinct numbers taken from 0, 1, 2, ..., n, find the one that is missing from the array.

Example 1:

Input: [3,0,1]
Output: 2
Example 2:

Input: [9,6,4,2,3,5,7,0,1]
Output: 8
Note:
Your algorithm should run in linear runtime complexity. Could you implement it using only constant extra space complexity?
         */
        int[] nums=new int[]{9,6,4,2,3,5,7,0,1};
        System.out.println("==find missing number:"+missingNumber(nums));
    }

    public static int missingNumber(int[] nums) {
        if(nums==null || nums.length<0 ) return 0;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            while(nums[i]>=0 && nums[i]<n && nums[i]!=nums[nums[i]]){
                swap(nums,i, nums[i]);
            }
        }

        if(nums[0]!=0) return 0;

        for (int i = 0; i < n; i++) {
            if(nums[i]!=i){
                return i;
            }
        }
        return n;
    }

    private static void swap(int[] nums, int x, int y){
        nums[x] ^=nums[y];
        nums[y] ^=nums[x];
        nums[x] ^=nums[y];
    }
}
