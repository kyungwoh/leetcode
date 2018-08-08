# https://leetcode.com/problems/regular-expression-matching/description/
# Compare s, p on each position
# But care about 'something'+'*' case (repeatable 0-* times)
# Time: O(3^(len(s)+len(p)), Space : same as time
class Solution:
    def isMatch(self, s, p):
        if not p: return not s
        else:
            currMatched = False if not s else (True if p[0]=='.' else s[0]==p[0])
            #print(currMatched,s,p)
            if len(p)>=2 and p[1]=='*':
                return self.isMatch(s, p[2:]) or (currMatched and self.isMatch(s[1:],p))
            else:
                return currMatched and self.isMatch(s[1:],p[1:])
