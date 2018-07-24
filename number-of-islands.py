# https://leetcode.com/problems/number-of-islands/description/
# Union Find + Pass compression
# Time: O(nlogn), Space: O(n)
class Solution:
    
    def numIslands(self, grid):
        """
        :type grid: List[List[str]]
        :rtype: int
        """
        if not grid: return 0
        
        len1 = len(grid)
        len2 = len(grid[0])
        parents = {}
        cnt = 0
        for i in range(len1):
            for j in range(len2):
                if grid[i][j]=='1':
                    parents[j*len1+i] = j*len1+i
                    cnt += 1
        
        for i in range(len1):
            for j in range(len2):
                if grid[i][j]=='1':
                    if i>0 and grid[i-1][j]=='1':
                        cnt -= self._union(len1*j+i, len1*j+i-1, parents)
                    if j>0 and grid[i][j-1]=='1':
                        cnt -= self._union(len1*j+i, len1*(j-1)+i, parents)
        return cnt
    
    def _union(self, n1, n2, parents):
        p1 = self._find(n1, parents)
        p2 = self._find(n2, parents)
        if p1==p2: return 0
        else:
            parents[p1] = p2
            return 1
    
    def _find(self, n, parents):
        if parents[n] != n:
            parents[n] = self._find(parents[n], parents)
        return parents[n]
