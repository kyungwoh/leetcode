# https://leetcode.com/problems/jump-game/description/
# Greedy
# Time: O(n), Space: O(1)
class Solution:
    def canJump(self, nums):
        last = len(nums)-1
        for i in range(len(nums)-1,-1,-1):
            if last<=i+nums[i]: last=i
        return True if last==0 else False
