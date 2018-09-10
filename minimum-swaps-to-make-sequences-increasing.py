# https://leetcode.com/problems/minimum-swaps-to-make-sequences-increasing/description/
# DP (no swap + swap)
# Time: O(n), Space: O(n) but can improve to O(1)
class Solution:
    def minSwap(self, A, B):
        n = len(A)
        noswap = [sys.maxsize if x>0 else 0 for x in range(n)]
        swap = [sys.maxsize if x>0 else 1 for x in range(n)]
        for i in range(1,n):
            if A[i-1]<A[i] and B[i-1]<B[i]:
                noswap[i]=min(noswap[i], noswap[i-1])
                swap[i]=min(swap[i], swap[i-1]+1)
            if A[i-1]<B[i] and B[i-1]<A[i]:
                noswap[i]=min(noswap[i], swap[i-1])
                swap[i]=min(swap[i], noswap[i-1]+1)
        return min(noswap[n-1], swap[n-1])
