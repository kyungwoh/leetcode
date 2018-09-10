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
    
    def longestPalindrome2(self, s):
        def longestStr(l,r):
            while l>0 and r<len(s)-1 and s[l-1]==s[r+1]:
                l -= 1
                r += 1
            if r-l+1>self.ansLen:
                self.ansL = l
                self.ansR = r
                self.ansLen = r-l+1
            
        self.ansL,self.ansR,self.ansLen=0,0,0
        for i in range(len(s)):
            longestStr(i,i)
            if i>0: longestStr(i,i-1)
        return s[self.ansL:self.ansR+1]
