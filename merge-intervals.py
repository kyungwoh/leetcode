# Definition for an interval.
# class Interval:
#     def __init__(self, s=0, e=0):
#         self.start = s
#         self.end = e
# https://leetcode.com/problems/merge-intervals/description/
class Solution:
    # Sort, then sweep
    # Time: O(nlogn), Space: O(n)
    def merge(self, intervals):
        ans = []
        for i in sorted(intervals, key=lambda i : i.start):
            if ans and ans[-1].end >= i.start:
                ans[-1].end = max(ans[-1].end, i.end)
            else:
                ans += i,
        return ans
    # Sort, then sweep (same)
    # But reuse input array (slower! because of del O(n))
    # Time: O(nlogn), Space: O(1) excep input
    def merge2(self, intervals):
        """
        :type intervals: List[Interval]
        :rtype: List[Interval]
        """
        intervals.sort(key=operator.attrgetter('start'))
        i = 1
        while i < len(intervals):
            if intervals[i-1].end >= intervals[i].start:
                intervals[i-1].end = max(intervals[i-1].end, intervals[i].end)
                del intervals[i]
            else:
                i += 1
        return intervals
            
