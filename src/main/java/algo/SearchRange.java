package algo;

import java.util.Arrays;

public class SearchRange {
    public static void main(String[] args) {
        int[] data=new int[]{5,7,7,8,8,10};
//        int[] data=new int[]{1};
//        int[] data=new int[]{2,2};
        System.out.println(" search range:"+ Arrays.toString(new SearchRange().searchRange(data, 8)));
//        34. Find First and Last Position of Element in Sorted Array
//        Given an array of integers nums sorted in ascending order, find the starting and ending position of a given target value.
//        Your algorithm's runtime complexity must be in the order of O(log n).
//        If the target is not found in the array, return [-1, -1].
    }
    public int[] searchRange(int[] nums, int target) {
        int[] rtn=new int[]{-1,-1};
        if(nums==null ||nums.length==0 ) return rtn; // shouln't return default for "|| nums.length<1", e.g. [1], target "1"

        int lo=0,hi=nums.length-1,mi=0;

        // find left side
        while(lo<hi){
            mi=lo+(hi-lo)/2;
            if(target>nums[mi]){ //8<9
                lo=mi+1; //move right ward for "one"
            }else{ //8>7 // (a) target==nums[mi] => [1,7,8,(8),9,10] (b) target < nums[mi] =>[1,7,8,8,(9),10]
                // so keep on search *left* part
                hi=mi; //rather than mi-1
            }
        }
        // here lo==hi
        if(nums[lo]==target){// to check "low" barrier
            rtn[0]=lo;// return "low" rather than "mi"
        }else{
            rtn[0]=-1;
        }


        // to find right side
        // reset hi, no need worries about "lo",while "hi" was still "high" in left part, so reset its high
        hi=nums.length-1;
        while(lo<hi){
            mi=(lo+(hi-lo)/2)+1; // to make right side bias
            if(target<nums[mi]){ //[1,7,8,8,(9),15], move leftward
                hi=mi-1; // rather than "mi", so [!!!] make sure "-1" to move left wards
            }else{ // (a) target==nums[mi] => [1,7,8,(8),9,15] (b) target>nums[mi] [1,(7),8,8,9,15]. both to make "lo" as middle
                lo=mi;
            }
        }
        if(nums[hi]==target){ //[!!!] check nums[hi] rather than nums[mi]
            rtn[1]=hi;
        }else{
            rtn[1]=-1;
        }



        return rtn;
    }
//    public int[] searchRange(int[] nums, int target) {
//        int[] rtn=new int[]{-1,-1};
//        if(nums==null ) return rtn; // shouln't return default for "|| nums.length<1", e.g. [1], target "1"
//
//        int i=0,j=nums.length-1;
//
//        while(i<=j){ //[!!!!]  this should be "<" and "="
//            int m=i+(j-i)/2;
//            if(nums[m]>target){
//                //target on left, so update right edge to keep on search left
//                j=m-1;
//            }else if(nums[m]<target){
//                // target on right, so update left edge to keep on search right
//                i=m+1;
//
//            }else{
//                //find target, to fine left and right edge
//                // nums[m]==target
//
//                // left edge
//                int i2=0,j2=m;
//                while(i2<=j2){ //[!!!!!] Should be < and "="
//                    int m2=i2+(j2-i2)/2;
//                    if(nums[m2]<target){
//                        // too small, go right section
//                        i2=m2+1;
//                    }else{
//                        // nums[m2]==target, it impssobile nums[m2]>target
//                        if(nums.length==1 || (m2>1 && nums[m2-1]<nums[m2])){ //[!!!!!] length==1 is for one edge case [1], target=1
//                            rtn[0]=m2;// find the left edge
//                            break;
//                        }else{
//                            // still in middle of dup of target number
//                            // go left section
//                            j2=m2-1;
//                        }
//                    }
//                }
//
//                // to search for right edge
//                i2=m;   //[!!!!] should start from "m", rather than m+1
//                j2=nums.length-1;
//                while(i2<=j2){
//                    int m2=i2+(j2-i2)/2;
//                    if(nums[m2]>target){
//                        // too large, go left section
//                        j2=m2-1;
//                    }else{
//                        // nums[m2]==target, it impssobile nums[m2]<target
//                        if(nums.length==1 ||( m2<nums.length-1 && nums[m2]<nums[m2+1])){
//                            rtn[1]=m2;
//                            return rtn;
//                        }else{
//                            i2=m2+1;
//                        }
//                    }
//                }
//            }
//        }
//        return rtn;
//    }
}
