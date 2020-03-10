package algo;

public class FindDuplicate {
    public static void main(String[] args) {
        /*
        287. Find the Duplicate Number
Medium

Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive), prove that at least one duplicate number must exist. Assume that there is only one duplicate number, find the duplicate one.

Example 1:

Input: [1,3,4,2,2]
Output: 2
Example 2:

Input: [3,1,3,4,2]
Output: 3
Note:

You must not modify the array (assume the array is read only).
You must use only constant, O(1) extra space.
Your runtime complexity should be less than O(n2).
There is only one duplicate number in the array, but it could be repeated more than once.

         */
        System.out.println("==find duplicate:"+findDuplicate(new int[]{3,1,3,4,2}));
        System.out.println("==find duplicate:"+findDuplicateBS(new int[]{3,1,3,4,2}));
    }

    public static int findDuplicate(int[] nums) {
        // the key is find middle, and calculate how many element not bigger than middle, and then search half , unitl find result
        int low=1; // the number set start with 1
        int high =nums.length-1; // high is len - 1 becuase there is ONE dup number
        int mid;

        while(low<high){

            // get middle
            mid=low+(high-low)/2;
            int countLessThanMiddle=0; // reset to '0'
            // iterative to find counts not bigger than middle
            for(int i=0;i<nums.length;i++){
                if(nums[i]<=mid){
                    countLessThanMiddle++;
                }
            }

            // if the count > middle, means more items in left, so update 'high', otherwise update 'low'
            if(countLessThanMiddle>mid){
                // more 'small' items, so dup in left
                high=mid;
            }else{
                low=mid+1;
            }
        }
        return low;
    }

    public static int findDuplicate_Floyd(int[] nums) {

            int n = nums.length;
            int slow = n;
            int fast = n;
            do{
                slow = nums[slow-1];
                fast = nums[nums[fast-1]-1];
            }while(slow != fast);
            slow = n;
            while(slow != fast){
                slow = nums[slow-1];
                fast = nums[fast-1];
            }
            return slow;
    }

    public static int findDuplicateBS(int[] nums) {
        if (nums.length == 0 || nums == null)
            return 0;
        int low = 1, high = nums.length - 1, mid;
        while (low < high) {
            mid = low + (high - low) / 2;
            int count = 0;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] <= mid)
                    count++;
            }
            if (count > mid)
                high = mid;
            else
                low = mid + 1;
        }
        return low;
    }
}
