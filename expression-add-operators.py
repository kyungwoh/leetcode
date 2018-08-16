# https://leetcode.com/problems/expression-add-operators/description/
# DFS
# Time: O(n^2), Space: same as time
class Solution(object):
    def addOperators(self, num, target):
        def dfs(remain, expr, prev, curr):
            if remain:
                for i in range(1,len(remain)+1):
                    if i>1 and remain[0]=='0': break
                    else:
                        left, right = remain[:i], remain[i:]
                        l = int(left)
                        if expr:
                            dfs(right, expr+'*'+left, prev*l, curr-prev+prev*l)
                            dfs(right, expr+'+'+left, l, curr+l)
                            dfs(right, expr+'-'+left, -l, curr-l)
                        else:
                            dfs(right, left, l, l)  
            elif curr==target: ans.append(expr)
            
        ans = []
        dfs(num, "", 0, 0)
        return ans
