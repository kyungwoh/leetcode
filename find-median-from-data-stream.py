https://leetcode.com/problems/find-median-from-data-stream/description/
# Use 2 Heaps
# Time: add O(logn), find O(1)
# Space: O(n)
class MedianFinder:

    def __init__(self):
        self._cnt = 0
        self._loHeap = []
        self._hiHeap = []

    def addNum(self, num):
        self._cnt += 1
        heapq.heappush(self._loHeap, -num)
        heapq.heappush(self._hiHeap, -heapq.heappop(self._loHeap))
        if self._cnt%2==1: heapq.heappush(self._loHeap, -heapq.heappop(self._hiHeap))
    def findMedian(self):
        if self._cnt%2==1: return -self._loHeap[0]
        else: return (-self._loHeap[0] + self._hiHeap[0])/2.0
        


# Your MedianFinder object will be instantiated and called as such:
# obj = MedianFinder()
# obj.addNum(num)
# param_2 = obj.findMedian()
