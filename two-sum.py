# https://leetcode.com/problems/two-sum/description/
# Using Dictionary (complement of target)
# Time: O(n), Space: O(n)
class Solution:
    def twoSum(self, nums, target):
        """
        :type nums: List[int]
        :type target: int
        :rtype: List[int]
        """
        numsDic = {}
        for i,n in enumerate(nums):
            if target-n in numsDic:
                return [numsDic[target-n], i]
            else:
                numsDic[n] = i
        
