# https://leetcode.com/problems/meeting-rooms-ii/description/
# Sort & Tail min heap
# Time: O(nlogn), Space: O(n)
class Solution:
    def minMeetingRooms(self, intervals):
        intervals.sort(key=lambda x: x.start)
        q, ans = [], 0
        for i in intervals:
            while q and q[0]<=i.start: heapq.heappop(q)
            heapq.heappush(q,i.end)
            ans = max(ans, len(q))
        return ans
