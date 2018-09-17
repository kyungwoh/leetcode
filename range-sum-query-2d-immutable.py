# https://leetcode.com/problems/range-sum-query-2d-immutable/description/
# Partial Sum
# Time: init() O(mn), sumRegion() O(1)
# Space: O(mn)
class NumMatrix:
    def __init__(self, matrix):
        pSum = [[0 for j in range(len(matrix[0]))] for i in range(len(matrix))]
        for i in range(len(matrix)):
            for j in range(len(matrix[0])):
                if i==0 and j==0: pSum[i][j]=matrix[i][j]
                else:
                    pSum[i][j]=matrix[i][j]
                    pSum[i][j]+=pSum[i-1][j] if i>0 else 0
                    pSum[i][j]+=pSum[i][j-1] if j>0 else 0
                    pSum[i][j]-=pSum[i-1][j-1] if i>0 and j>0 else 0
        self.pSum=pSum
    def sumRegion(self, row1, col1, row2, col2):
        pSum=self.pSum
        ans=pSum[row2][col2]
        ans-=pSum[row1-1][col2] if row1>0 else 0
        ans-=pSum[row2][col1-1] if col1>0 else 0
        ans+=pSum[row1-1][col1-1] if row1>0 and col1>0 else 0
        return ans
