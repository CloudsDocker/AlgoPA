class Solution:
    def findPeakElement(self, nums: List[int]) -> int:
        left,right=0,len(nums)-1
        mid=(right-left)//2
        while left<right:
            if nums[mid-1]<mid and mid>nums[mid+1]:
                return mid
            elif nums[mid-1]> mid:
                right=mid
            else:
                left=mid+1
        return left
        