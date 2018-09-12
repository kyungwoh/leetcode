# https://leetcode.com/problems/set-matrix-zeroes/description/
# Time: O(mn), Space: O(1) without input matrix (in-place)
class Solution:
    def setZeroes(self, matrix):
        l1 = len(matrix)
        if l1==0: pass
        l2 = len(matrix[0])
        if l2==0: pass
        
        firstRow, firstCol = False, False
        for i in range(l1):
            for j in range(l2):
                if matrix[i][j]==0:
                    if i==0: firstRow=True
                    if j==0: firstCol=True
                    if i!=0 and j!=0:
                        matrix[0][j]=0
                        matrix[i][0]=0
        for i in range(1,l1):
            for j in range(1,l2):
                if matrix[0][j]==0 or matrix[i][0]==0: matrix[i][j]=0
        for i in range(l1):
            if firstCol: matrix[i][0]=0
        for j in range(l2):
            if firstRow: matrix[0][j]=0
        
