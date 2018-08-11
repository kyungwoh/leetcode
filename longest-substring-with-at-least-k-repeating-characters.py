# https://leetcode.com/problems/longest-substring-with-at-least-k-repeating-characters/
# Divide & Conquer
# Time: O(n^2), Space: O(n^3)
class Solution:
    def longestSubstring(self, s, k):
        #print(s)
        if len(s)<k: return 0
        
        cnts = [0 for x in range(26)]
        for c in s: cnts[ord(c)-97] += 1
        
        isAllAns, start, maxLen = True, 0, 0
        for i in range(len(s)):
            if cnts[ord(s[i])-97]<k:
                isAllAns = False
                maxLen = max(maxLen, self.longestSubstring(s[start:i],k))
                start = i+1
        
        if isAllAns: return len(s)
        else: return max(maxLen, self.longestSubstring(s[start:],k))
