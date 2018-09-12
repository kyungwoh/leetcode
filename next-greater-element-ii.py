# https://leetcode.com/problems/next-greater-element-ii/description/
# Use stack & travel backwards twice
# Pop until finding next greater value, then push the current value
# Time: O(n), Space: O(n)
class Solution:
    def nextGreaterElements(self, nums):
        stk = collections.deque()
        nLen = len(nums)
        ans = [0 for x in range(nLen)]
        for i in range(nLen*2-1,-1,-1):
            i %= nLen
            while stk and stk[-1]<=nums[i]: stk.pop()
            ans[i] = stk[-1] if stk else -1
            stk.append(nums[i])            
        return ans
