# https://leetcode.com/problems/wiggle-sort/description/
# It's enought just swapping adjacent numbers
# --> if there 3 adjacent numbers of (n0,n1,n2), there are only cases
# 1. n0==n1==n2, 2. n0==n1< or > n2, 3. n0< or >n1==n2, 4. n0< or >n1< or >n2
# all cases, swapping is enough
# Time: O(n), Space: O(1)
# Or, sort first and perfect shuffle : O(nlogn)
class Solution:
    def wiggleSort(self, nums):
        for i in range(1,len(nums)):
            if i%2==1:
                if nums[i-1]>nums[i]: nums[i-1],nums[i]=nums[i],nums[i-1]
            else:
                if nums[i-1]<nums[i]: nums[i-1],nums[i]=nums[i],nums[i-1]
