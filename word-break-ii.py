# https://leetcode.com/problems/word-break-ii/description/
class Solution:
    # DP + Backtrack
    # 1. Just possible indices instead of string (to reduce memory)
    # 2. After that, backtrack from the last
    # Time: O(s^2*w), Space: O(sw)
    def wordBreak(self, s, wordDict):
        def backtrack(s,wDic,dp,rList,r,i):
            for j in dp[i]:
                if i==0: rList.append(r)
                else: backtrack(s,wDic,dp,rList,s[j:i]+" "+r if r!="" else s[j:i],j)
                
        dp = [[] for x in range(len(s)+1)]
        dp[0].append(0)
        for i in range(1,len(s)+1):
            for j in range(i):
                if dp[j] and s[j:i] in wordDict:
                    dp[i].append(j)
        rList,r = [],""
        backtrack(s,wordDict,dp,rList,r,len(s))
        return rList
    
    # DP
    # Time: O(s^2*w), Space: O(sw)
    # Memory Limit Exceeded    
    def wordBreak2(self, s, wordDict):
        dp = [[] for x in range(len(s))]
        for i in range(len(s)):
            for w in wordDict:
                if i+1>=len(w)-1 and s[i+1-len(w):i+1]==w:
                    cur = []
                    if i>=len(w):
                        cur.extend(dp[i-len(w)])
                        for j in range(len(cur)): cur[j] += " "+w
                    else: cur.append(w)
                    dp[i].extend(cur)
        return dp[len(s)-1]
    
    # DFS
    # Time: O(s^w), Space: O(s) the max depth of DFS
    # Time Limit Exceeded
    def wordBreak3(self, s, wordDict):
        def dfs(s,wDic,rList,r,i):
            if i==len(s): rList.append(r)
            elif i<len(s):
                for w in wDic:
                    if i+len(w)<=len(s) and s[i:i+len(w)]==w:
                        dfs(s,wDic,rList,w if r=="" else r+" "+w, i+len(w))                
        rList = []
        r = ""
        dfs(s,wordDict,rList,r,0)
        return rList
