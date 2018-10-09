# https://leetcode.com/problems/majority-element/description/
# Boyer-Moore Voting Algorithm
# Time: O(n), Space: O(1)
class Solution(object):
    def majorityElement(self, nums):
        prev,cnt = None,0
        for n in nums:
            if not prev: prev=n
            elif prev==n: cnt+=1
            elif cnt>0: cnt-=1
            else: prev,cnt=n,0
        return prev
