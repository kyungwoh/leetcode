# https://leetcode.com/problems/4sum-ii/description/
# 4 sum = 2 sum + 2 sum
# dictionary {} : put O(1), get O(1)
# Time: O(n^2), Space: O(n^2)
class Solution:
    def fourSumCount(self, A, B, C, D):
        dic = {}
        for i in range(len(A)):
            for j in range(len(B)):
                sum = A[i]+B[j]
                if sum in dic: dic[sum] += 1
                else: dic[sum] = 1
        cnt = 0
        for i in range(len(C)):
            for j in range(len(D)):
                sum = -C[i]-D[j]
                if sum in dic: cnt += dic[sum]
        return cnt
