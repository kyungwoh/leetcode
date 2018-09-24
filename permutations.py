# https://leetcode.com/problems/permutations/description/
# DFS + Visit checking
# Time: O(n!), Space: O(n)
class Solution:
    def permute(self, nums):
        def dfs(cnt):
            if cnt<len(nums):
                for i in range(len(nums)):
                    if not visit[i]:
                        visit[i]=True
                        cur.append(nums[i])
                        dfs(cnt+1)
                        cur.pop()
                        visit[i]=False
            else: ans.append(cur[:])
        
        visit=[False for i in range(len(nums))]
        ans,cur=[],[]
        dfs(0)
        return ans
