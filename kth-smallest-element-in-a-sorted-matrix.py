# https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/description/
class Solution:
    # Use Binary search (minNum to maxNum) & (find number with i=0->n-1 & j=n-1->0)
    # Count numbers by row & column
    # n = # of matrix, m = range(minNum to maxNum of matrix)
    # Time: O(nlogm), Space: O(1)
    def kthSmallest(self, matrix, k):
        n = len(matrix)
        lo,hi = matrix[0][0],matrix[n-1][n-1]
        while lo<hi:
            mid,cnt,i,j = (lo+hi)//2,0,0,n-1
            while i<n:
                while j>=0 and matrix[i][j]>mid: j -= 1
                cnt += (j+1)
                i += 1
            if k > cnt: lo = mid+1
            else: hi = mid
        return lo
    
    # Use HeapQ
    # Time: O(klogn), Space: O(n)
    def kthSmallest2(self, matrix, k):
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
