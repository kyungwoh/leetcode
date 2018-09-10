# https://leetcode.com/problems/longest-palindromic-substring/description/
# Expand from the middle point
# Time: O(n^2), Space: O(1)
class Solution:
    def longestPalindrome(self, s):
        ans = ""
        for i in range(len(s)):
            l,r=i,i
            while l>0 and r<len(s)-1 and s[l-1]==s[r+1]:
                l -= 1
                r += 1
            if r-l+1>len(ans):
                ans = s[l:r+1]
        for i in range(1,len(s)):
            l,r=i,i-1
            while l>0 and r<len(s)-1 and s[l-1]==s[r+1]:
                l -= 1
                r += 1
            if r-l+1>len(ans):
                ans = s[l:r+1]
        return ans
