# https://leetcode.com/problems/permutations-ii/description/
# Sort + DFS + Visit checking + Prune adjacent duplicated numbers
# Time: O(n!), Space: O(n)
class Solution:
    def permuteUnique(self, nums):
        def dfs(cnt):
            if cnt==len(nums): ans.append(cur[:])
            else:
                for i in range(len(nums)):
                    if not visit[i] and (i==0 or nums[i-1]!=nums[i] or visit[i-1]):
                        visit[i]=True
                        cur.append(nums[i])
                        dfs(cnt+1)
                        cur.pop()
                        visit[i]=False
        nums.sort()
        visit=[False for i in range(len(nums))]
        ans,cur=[],[]
        dfs(0)
        return ans
