# https://leetcode.com/problems/longest-valid-parentheses/description/
class Solution:
    # Two paths: left->right, right->left
    # Reset 0 when invalid
    # Time: O(n), Space: O(1)
    def longestValidParentheses(self, s):
        lCnt, rCnt, maxLen = 0, 0, 0
        for i in range(len(s)):
            if s[i]=='(': lCnt += 1
            elif s[i]==')': rCnt += 1
            
            if lCnt==rCnt: maxLen = max(maxLen, lCnt*2)
            elif lCnt<rCnt: lCnt, rCnt = 0, 0
        
        lCnt, rCnt = 0, 0
        for i in range(len(s)-1,-1,-1):
            if s[i]=='(': lCnt += 1
            elif s[i]==')': rCnt += 1
            
            if lCnt==rCnt: maxLen = max(maxLen, lCnt*2)
            elif lCnt>rCnt: lCnt, rCnt = 0, 0
        
        return maxLen
    
    # Stack (last valid position)
    # 1. First, push -1 (as a start position)
    # 2. When stack is empty, push the current position
    # Time: O(n), Space: O(n)
    def longestValidParentheses2(self, s):
        stack = collections.deque()
        stack.append(-1)
        i,maxLen = 0,0
        for c in s:
            if c=='(':
                stack.append(i)
            elif c==')':
                stack.pop()
                if stack:
                    maxLen = max(maxLen, i-stack[-1])
                else:
                    stack.append(i)
            i += 1
        return maxLen
    
    # DP (maxLength) + Stack (last position)
    # Time: O(n), Space: O(n)
    def longestValidParentheses3(self, s):
        dp = [0]*len(s)
        stack = collections.deque()
        i,maxDp = 0,0
        for c in s:
            if c=='(':
                stack.append(i)
            elif c==')' and stack:
                dp[i] = i - stack.pop() +1
                if dp[i]<i: dp[i] += dp[i-dp[i]]
                maxDp = max(maxDp, dp[i])
            i += 1
        return maxDp
