# https://leetcode.com/problems/spiral-matrix/description/
# Time: O(nm), Space: O(1)
class Solution:
    def spiralOrder(self, matrix):
        ans = []
        l1 = len(matrix)
        if l1==0: return ans
        l2 = len(matrix[0])
        if l2==0: return ans
        cnt = l1*l2
        i,j,iL,iR,jL,jR = 0,0,1,l1-1,0,l2-1
        while True:
            if cnt==0: break
            for jx in range(jL,jR+1): ans.append(matrix[i][jx])
            cnt -= jR-jL+1
            j = jR
            jR -= 1
            
            if cnt==0: break
            for ix in range(iL,iR+1): ans.append(matrix[ix][j])
            cnt -= iR-iL+1
            i = iR
            iR -= 1
            
            if cnt==0: break
            for jx in range(jR,jL-1,-1): ans.append(matrix[i][jx])
            cnt -= jR-jL+1
            j = jL
            jL += 1
            
            if cnt==0: break
            for ix in range(iR,iL-1,-1): ans.append(matrix[ix][j])
            cnt -= iR-iL+1
            i = iL
            iL += 1
        return ans
            
