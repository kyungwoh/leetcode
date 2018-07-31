# https://leetcode.com/problems/wildcard-matching/description/
class Solution:
    def isMatch(self, s, p):
        return self._dfs(s, p, 0, 0) > 1
    def _dfs(self, s, p, si, pi):
        if si==len(s):
            if pi==len(p): return 2 #matched
            if p[pi]!='*': return 0 #unmatched w/ all s
        if pi==len(p): return 1 #unmatched w/o all s (try more)
        if p[pi]=='*':
            if pi+1<len(p) and p[pi+1]=='*': return self._dfs(s, p, si, pi+1) #skip **
            for i in range(len(s)-si+1):
                r = self._dfs(s, p, si+i, pi+1) #try all possibles
                if r==0 or r==2: return r #if matched or unmatched w/ all s, stop
        if p[pi]=='?' or s[si]==p[pi]: return self._dfs(s, p, si+1, pi+1) #go 1 step
        return 1 #unmatched w/o all s (try more)
        
