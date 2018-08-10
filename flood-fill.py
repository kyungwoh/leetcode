# https://leetcode.com/problems/flood-fill/description/
# BFS
# Time: O(n), Space: O(n)
class Solution:
    def floodFill(self, image, sr, sc, newColor):
        l1 = len(image)
        if l1==0: return image
        l2 = len(image[0])
        if l2==0: return image
        
        visit = [[True for x in range(l2)] for x in range(l1)]
        que = collections.deque()
        que.append([sr,sc])
        curColor = image[sr][sc]
        while que:
            p = que.popleft()
            i, j = p[0], p[1]
            image[i][j] = newColor
            visit[i][j] = False
            
            if i>0 and visit[i-1][j] and image[i-1][j]==curColor: que.append([i-1,j])
            if j>0 and visit[i][j-1] and image[i][j-1]==curColor: que.append([i,j-1])
            if i<l1-1 and visit[i+1][j] and image[i+1][j]==curColor: que.append([i+1,j])
            if j<l2-1 and visit[i][j+1] and image[i][j+1]==curColor: que.append([i,j+1])
            
        return image
