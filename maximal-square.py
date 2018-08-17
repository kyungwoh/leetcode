# https://leetcode.com/problems/maximal-square/description/
# Using largest rectangle
# + DP is also possible
# Time: O(n^2), Space: O(n^2)
class Solution:
    def maximalSquare(self, matrix):
        l1 = len(matrix)
        if l1==0: return 0
        l2 = len(matrix[0])
        if l2==0: return 0
        
        ans = 0
        h = [0]*l2
        for i in range(l1):
            stack = collections.deque()
            for j in range(l2+1):
                if j==l2: l = 0
                else:
                    num = matrix[i][j]
                    if num=='1': h[j] += 1
                    else: h[j] = 0
                    l = h[j]
                
                while stack and stack[-1][1] >= l:
                    top = stack.pop()
                    width = j - stack[-1][0] -1 if stack else j
                    ans = max(ans, min(top[1], width))
                
                stack.append([j, l])
        return ans**2
