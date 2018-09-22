# https://leetcode.com/problems/longest-common-prefix/description/
# Vertical scanning
# Time: O(nm) n=len(strs), m=len(strs[i]), Space: O(1)
class Solution:
    def longestCommonPrefix(self, strs):
        ans, i = "", 0
        if len(strs)>0:
            while True:
                if i+1>len(strs[0]): break
                else:
                    c = strs[0][i]
                    isFound = True
                    for j in range(1,len(strs)):
                        if i+1>len(strs[j]) or c!=strs[j][i]:
                            isFound = False
                            break
                    if isFound:
                        ans += c
                        i += 1
                    else: break
        return ans
            
