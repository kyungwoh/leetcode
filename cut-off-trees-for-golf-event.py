# https://leetcode.com/problems/cut-off-trees-for-golf-event/description/
# 1. Sort by numbers
# 2. BFS 
# Time Limit Exceeded (needs to change 2. BFS -> Hadlock's Algorithm)
class Solution:
    def cutOffTree(self, forest):
        l1 = len(forest)
        if l1==0: return 0
        l2 = len(forest[0])
        if l2==0: return 0
        if forest[0][0]==0: return -1
        p = {}
        for i in range(l1):
            for j in range(l2):
                if forest[i][j]!=0: p[forest[i][j]]=[i,j]
        pKeys = sorted(p.keys())
        print(len(pKeys))
        ans = 0
        preP = [0,0]
        for k in range(len(pKeys)):
            curP=p[pKeys[k]]
            print(curP)
            if forest[curP[0]][curP[1]]==0: return -1
            visited = [[False for j in range(l2)] for i in range(l1)]
            q = collections.deque()
            q.append([preP[0],preP[1],0])
            curCnt = -1
            while q:
                cur = q.popleft()
                i,j,cnt = cur[0],cur[1],cur[2]
                #print(i,j,cnt,visited[i][j])
                if not visited[i][j]:
                    visited[i][j]=True
                    if i==curP[0] and j==curP[1]:
                        curCnt=cnt
                        break
                    else:
                        if i>0 and not visited[i-1][j] and forest[i-1][j]!=0: q.append([i-1,j,cnt+1])
                        if j>0 and not visited[i][j-1] and forest[i][j-1]!=0: q.append([i,j-1,cnt+1])
                        if i<l1-1 and not visited[i+1][j] and forest[i+1][j]!=0: q.append([i+1,j,cnt+1])
                        if j<l2-1 and not visited[i][j+1] and forest[i][j+1]!=0: q.append([i,j+1,cnt+1])
            if curCnt==-1: return -1
            else:
                ans+=curCnt
                preP=curP
        return ans
        
