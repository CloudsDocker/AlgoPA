package algo;

import java.util.Arrays;

public class RemoveDuplicate {
    /*
    26. Remove Duplicates from Sorted Array
Easy

Given a sorted array nums, remove the duplicates in-place such that each element appear only once and return the new length.

Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.

Example 1:

Given nums = [1,1,2],

Your function should return length = 2, with the first two elements of nums being 1 and 2 respectively.

It doesn't matter what you leave beyond the returned length.
     */
    public static void main(String[] args) {

        int[] nums=new int[]{1,1,2,3,4};
        int n=removeDuplicates(nums);
        System.out.println("===remove duplicate:"+n+","+ Arrays.toString(nums));
        nums=new int[]{1,1,2,3,4};
        System.out.println("===remove duplicate better:"+removeDuplicatesShort(nums)+","+ Arrays.toString(nums));
    }

    public static int removeDuplicates(int[] nums){
        if(nums==null || nums.length<0) return 0;
        int pos=0;
        nums[pos++]=nums[0];
        for (int i = 1; i < nums.length; i++) {
            // iterate every element in the loop
            if(nums[i]!=nums[i-1]){
                // difference found , so increase cursor 'pos'
                nums[pos++]=nums[i];
            }
        }
        return pos;
    }

    // a better as it's shorter version
    public static int removeDuplicatesShort(int[] nums){
        if(nums==null || nums.length<0) return 0;
        int pos=0;
        for (int num:nums) {
            if(nums[pos]!=num){
                nums[++pos]=num;
            }
        }
        return ++pos;
    }
}
