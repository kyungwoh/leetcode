# https://leetcode.com/problems/diagonal-traverse/description/
# Just follow the directions
# Time: O(n), Space: O(1)
class Solution:
    def findDiagonalOrder(self, matrix):
        ans = []
        
        li = len(matrix)
        if li==0: return ans
        lj = len(matrix[0])
        if lj==0: return ans
        
        i,j,d = 0,0,-1
        for k in range(li*lj):
            ans.append(matrix[i][j])
            if d==1:
                i2 = i+1
                j2 = j-1
                if i2==li:
                    i2 = i
                    j2 = j+1
                    d = -1
                elif j2==-1:
                    j2 = j
                    d = -1
            else:
                i2 = i-1
                j2 = j+1
                if j2==lj:
                    i2 = i+1
                    j2 = j
                    d = 1
                elif i2==-1:
                    i2 = i
                    d = 1
            i,j=i2,j2
        return ans
        
