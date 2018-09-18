# https://leetcode.com/problems/search-in-rotated-sorted-array-ii/description/
# Binary search
# Time: average O(logn), worst O(n) when duplicates
# Space: O(1)
class Solution:
    def search(self, nums, target):
        l,r=0,len(nums)-1
        while l<=r:
            m=(r-l)//2+l
            if nums[m]==target: return True
            elif nums[l]==nums[m] and nums[m]==nums[r]:
                l+=1
                r-=1
            elif nums[l]<=nums[m]:
                if nums[l]<=target and nums[m]>target: r=m-1
                else: l=m+1
            else:
                if nums[m]<target and nums[r]>=target: l=m+1
                else: r=m-1
        return False
