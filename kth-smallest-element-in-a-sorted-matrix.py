# https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/description/
# Use HeapQ
# Time: O(klogn), Space: O(n)
class Solution:
    def kthSmallest(self, matrix, k):
        """
        :type matrix: List[List[int]]
        :type k: int
        :rtype: int
        """
        n = len(matrix)
        heap = []
        for i in range(n):
            heapq.heappush(heap, (matrix[i][0], i, 0))
        while True:
            k -= 1
            cur = heapq.heappop(heap)
            #print(cur)
            val, i, j = cur[0], cur[1], cur[2]
            if k==0: return val
            else:
                if j<(n-1):
                    j += 1
                    heapq.heappush(heap, (matrix[i][j], i, j))
        return -1
