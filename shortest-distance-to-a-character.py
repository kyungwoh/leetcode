# https://leetcode.com/problems/shortest-distance-to-a-character/description/
# Two pass (left->right, right->left)
# Time: O(n), Space: O(1) without output
class Solution:
    def shortestToChar(self, S, C):
        ans = [-1 for i in range(len(S))]
        cnt = -1
        for i in range(len(S)):
            if S[i]==C: cnt=0
            ans[i] = cnt
            if cnt!=-1: cnt += 1
        cnt = -1
        for i in range(len(S)-1,-1,-1):
            if S[i]==C: cnt=0
            if ans[i]==-1: ans[i]=cnt
            elif cnt!=-1: ans[i]=min(ans[i],cnt)
            if cnt!=-1: cnt += 1
        return ans
