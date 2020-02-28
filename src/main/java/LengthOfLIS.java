package algo;

public class LengthOfLIS {

    public static void main(String[] args) {
        System.out.println("===start=======");
        LengthOfLIS inst = new LengthOfLIS();
//        System.out.println("===out:"+inst.lengthOfLIS(new int[]{10,9,2,5,3,7,101,18}));
        System.out.println("===test failed case (naive) :"+inst.lengthOfLIS_naive(new int[]{4,10,4,3,8,9}));
        System.out.println("===test failed case (DP) :"+inst.lengthOfLIS_tail(new int[]{4,10,4,3,8,9}));
        System.out.println("===test failed case (DP) :"+inst.lengthOfLIS_tail(new int[]{2,2})); //expect output "1"


    }

    public int lengthOfLIS_naive(int[] nums){
        //edge case
        if(nums.length<0){
            return 0;
        }
        int m=nums.length;
        int max=0; // global max
        int[] dp=new int[m];
        //embeded loop to search max value brute forcely
        for (int i = 0; i <m ; i++) {
            // loop each digits
            int localMax=0; // holder for MAX length of increase sequence before i
            for (int j = 0; j < i; j++) {
                // loop to find all increasing BEFORE this number
                if(dp[j]>localMax && nums[j]<nums[i]){
                    // previous number is SMALLER than i and greater than local max, that means it's increasing
                    localMax=dp[j];
                }
            }
            // after looped ALL previous numbers, add current one
            dp[i]=localMax+1;
            max = Math.max(max,dp[i]);
        }
        return max;
    }

    public int lengthOfLIS_tail(int[] nums){

        int m=nums.length;
        if(m==0) return 0;
        int[] dp=new int[m]; // dp[x]=y : value "y" of dp stores "the last number" (tail) of increasing sequence whose length is "x"
        int maxLen=0;
        dp[0]=nums[0];
        //for loop each number in array
        for (int i = 1; i < m; i++) {   //[!!!!!!!!1111111] it should start with "1", as "0" is already setup
            // there are 3 scenarios we need to update dp
            if(nums[i]<dp[0]){
                // current number is even smaller than most samllest LIS, update it
                dp[0]=nums[i];
            }else if(nums[i]>dp[maxLen]){
                //current number is greater than 'tail' of largest LIS, then update the last LIS
                dp[++maxLen]=nums[i];
            }else{
                // current number is in the middle, so we go to find the *correct* position to locate the LIS in DP
                dp[binarySearchLIS(dp,0,maxLen,nums[i])]=nums[i];
            }
        }
        return maxLen+1; // because dp is zero based, so add one for result
    }

    public int binarySearchLIS(int[] dp, int min, int max, int target){
        while(min<=max){
            int middle =min + (max-min)/2; //[!!!!!!!] don't forget to add prefix "min +" in front of (max-min)/2
            if(dp[middle]==target){
                return middle;
            }else if(dp[middle]>target){
                max=middle-1;
            } else if(dp[middle]<target){
                min=middle+1;
            }
        }
        return min;

    }

}

//    public class Solution {
//        public int longestIncreasingSubsequence(int[] nums) {
//            // write your code here
//            if(nums.length == 0){
//                return 0;
//            }
//            // len表示当前最长的升序序列长度（为了方便操作tails我们减1）
//            int len = 0;
//            // tails[i]表示长度为i的升序序列其末尾的数字
//            int[] tails = new int[nums.length];
//            tails[0] = nums[0];
//            // 根据三种情况更新不同升序序列的集合
//            for(int i = 1; i < nums.length; i++){
//                if(nums[i] < tails[0]){
//                    tails[0] = nums[i];
//                } else if (nums[i] >= tails[len]){
//                    tails[++len] = nums[i];
//                } else {
//                    // 如果在中间，则二分搜索
//                    tails[binarySearch(tails, 0, len, nums[i])] = nums[i];
//                }
//            }
//            return len + 1;
//        }
//
//        private int binarySearch(int[] tails, int min, int max, int target){
//            while(min <= max){
//                int mid = min + (max - min) / 2;
//                if(tails[mid] == target){
//                    return mid;
//                }
//                if(tails[mid] < target){
//                    min = mid + 1;
//                }
//                if(tails[mid] > target){
//                    max = mid - 1;
//                }
//            }
//            return min;
//        }
//

//
//    public int longestIncreasingSubsequence(int[] nums) {
//        // write your code here
//        if(nums.length == 0){
//            return 0;
//        }
//        // 构建最长升序序列长度的数组
//        int[] lis = new int[nums.length];
//        lis[0] = 1;
//        int max = 0;
//        for (int i = 1; i < nums.length; i++){
//            // 找到dp[0]到dp[i-1]中最大的升序序列长度且nums[j]<nums[i]
//            for (int j = 0; j < i; j++){
//                if (nums[j] <= nums[i]){
//                    lis[i] = Math.max(lis[i], lis[j]);
//                }
//            }
//            // 加1就是该位置能构成的最长升序序列长度
//            lis[i] += 1;
//            // 更新全局长度
//            max = Math.max(max, lis[i]);
//        }
//        return max;
//    }
//
//
////    public class Solution {
//        public int longestIncreasingSubsequence(int[] nums) {
//            if(nums.length == 0){
//                return 0;
//            }
//            int[] lis = new int[nums.length];
//            int max = 0;
//            for (int i = 0; i < nums.length; i++){
//                int localMax = 0;
//                // 找出当前点之前的最大上升序列长度
//                for (int j = 0; j < i; j++){
//                    if (lis[j] > localMax && nums[j] <= nums[i]){
//                        localMax = lis[j];
//                    }
//                }
//                // 当前点则是该局部最大上升长度加1
//                lis[i] = localMax + 1;
//                // 用当前点的长度更新全局最大长度
//                max = Math.max(max, lis[i]);
//            }
//            return max;
//        }
//    }
    /*
    Following approach failed at: new int[]{4,10,4,3,8,9}));
     */
//    public int lengthOfLIS(int[] nums) {
//
//        int m = nums.length;
//        // edge case
//        if(m<1) return 0;
//        int[] dp=new int[m+1];
//        dp[0]=0;
//        dp[1]=1;
//        for (int i = 1; i <m ; i++) {
//            if(nums[i]>nums[i-1]){
//                dp[i+1]=dp[i]+1;
//            }else{
//                dp[i+1]=dp[i];
//            }
//        }
//        return dp[m];
//    }
//}
