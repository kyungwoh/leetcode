# https://leetcode.com/problems/unique-paths/description/
# DP
# Time: O(mn), Space: O(mn)
class Solution:
    def uniquePaths(self, m, n):
        dp = [[1 if j==0 or i==0 else 0 for j in range(n)] for i in range(m)]
        for i in range(1,m):
            for j in range(1,n):
                dp[i][j]=dp[i-1][j]+dp[i][j-1]
        return dp[m-1][n-1]
