# https://leetcode.com/problems/combination-sum/description/
class Solution:
    # Backtracking w/ stack
    # Don't need to check duplicates
    # c = # of candidates, t = target
    # Time: O(c^t) if candidates have 1 (worst case)
            --> but candidates > 1, significantly faster
    # Space: O(t) height of the tree
    def combinationSum(self, candidates, target):
        def backtrack(t, c):
            if t==0: rList.append(subset.copy())
            if t<=0 or t-candidates[c]<0: return
            else:
                for i in range(c, len(candidates)):
                    subset.append(candidates[i])
                    backtrack(t-candidates[i], i)
                    subset.pop()
        candidates.sort()
        subset, rList = [], []
        backtrack(target, 0)
        return rList
    
    # DP
    # c = # of candidates, t = target
    # Time: O(ct), Space: O(ct)
    def combinationSum2(self, candidates, target):
        dp = [set() for x in range(target+1)]
        for i in range(1, target+1):
            for c in candidates:
                if i-c>=0:
                    if dp[i-c]:
                        #print(i-c,dp[i-c])
                        for dpPrev in dp[i-c]:
                            dpNew = list(dpPrev)
                            dpNew.append(c)
                            dpNew.sort()
                            #print(i,c,dpPrev,dpNew)
                            dp[i].add(tuple(dpNew))
                    elif i==c:
                        dp[i].add((c,))
        return [list(d) for d in dp[target]]
