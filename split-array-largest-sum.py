# https://leetcode.com/problems/split-array-largest-sum/submissions/
# DP
# Time: O(mn^2), Space: O(mn)
class Solution(object):
    def splitArray(self, nums, m):
        n = len(nums)
        pSum = [0 if i>0 else nums[0] for i in range(n)]
        for i in range(1,n): pSum[i]=pSum[i-1]+nums[i]
        dp = [[sys.maxsize for j in range(n)] for i in range(m+1)]
        for i in range(1,m+1):
            for j in range(n):
                if i==1: dp[i][j]=(dp[i][j-1] if j>0 else 0)+nums[j]
                else:
                    for j2 in range(j):
                        dp[i][j]=min(dp[i][j], max(dp[i-1][j2],(pSum[j]-pSum[j2])))
        return dp[m][n-1]
