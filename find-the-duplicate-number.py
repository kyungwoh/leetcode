# https://leetcode.com/problems/find-the-duplicate-number/description/
# Slow & Fast runner (Tortoise & Hare algorithm)
# Time: O(n), Space: O(n)
class Solution:
    def findDuplicate(self, nums):
        slow, fast = 0, 0
        while True:
            slow = nums[slow]
            fast = nums[nums[fast]]
            if slow==fast: break
        
        fast = 0
        while True:
            slow = nums[slow]
            fast = nums[fast]
            if slow==fast: break
        
        return fast
