# https://leetcode.com/problems/edit-distance/description/
class Solution:
    # DP
    # Time: O(mn), Space: O(mn)
    def minDistance(self, word1, word2):
        m,n=len(word1),len(word2)
        dp = [[(0 if i>0 else j) if j>0 else i for j in range(n+1)] for i in range(m+1)]
        for i in range(m):
            for j in range(n):
                if word1[i]==word2[j]: dp[i+1][j+1]=dp[i][j]
                else: dp[i+1][j+1]=min(dp[i][j], dp[i+1][j], dp[i][j+1])+1
        return dp[m][n]
    
    # Recursive + Memo (Time Limit Exceeded)
    # Time: O(2^max(m,n)), Space: O(mn)
    memo = {}
    def minDistance2(self, word1, word2):
        w = word1+","+word2
        if w in self.memo: return self.memo[w]
        else:
            if len(word1)==0: ans=len(word2)
            elif len(word2)==0: ans=len(word1)
            else:
                ans = max(len(word1),len(word2))
                for i1 in range(len(word1)):
                    for i2 in range(len(word2)):
                        if word1[i1]==word2[i2]:
                            ans = min(ans, self.minDistance(word1[:i1],word2[:i2])+self.minDistance(word1[i1+1:],word2[i2+1:]))
            self.memo[w] = ans
            return ans
