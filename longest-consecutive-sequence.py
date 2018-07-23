# https://leetcode.com/problems/longest-consecutive-sequence/description/
# Save nums to set() & save start num
# Then, start from start
# Time: O(n), Space: O(n)
class Solution:
    def longestConsecutive(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        if not nums:
            return 0
        
        used = set()
        start = set()
        
        for n in nums:
            used.add(n)
            if n-1 not in used:
                start.add(n)
            if n+1 in start:
                start.remove(n+1)
        
        longest = 0
        for s in start:
            i = s
            length = 0
            while i in used:
                i += 1
                length += 1
            longest = max(longest, length)
            
        return longest
