# https://leetcode.com/problems/longest-increasing-path-in-a-matrix/description/
class Solution:
    def decreaseOut(self, key, out, q):
        out[key] -= 1
        if out[key]==0:
            q.append(key)
    
    def longestIncreasingPath(self, matrix):
        l1 = len(matrix)
        if l1==0: return 0
        l2 = len(matrix[0])
        if l2==0: return 0
        
        out = {}
        for i in range(l1):
            for j in range(l2):
                key = l2*i+j
                out[key] = 0
                if i>0 and matrix[i-1][j] > matrix[i][j]: out[key] += 1
                if j>0 and matrix[i][j-1] > matrix[i][j]: out[key] += 1
                if i<(l1-1) and matrix[i+1][j] > matrix[i][j]: out[key] += 1
                if j<(l2-1) and matrix[i][j+1] > matrix[i][j]: out[key] += 1
        q = collections.deque()
        for k,v in out.items():
            if v==0: q.append(k)
        epoch = 0
        while q:
            epoch += 1
            qLen = len(q)
            for s in range(qLen):
                key = q.popleft()
                i = key//l2
                j = key%l2
                if i>0 and matrix[i-1][j] < matrix[i][j]: self.decreaseOut(l2*(i-1)+j, out, q)
                if j>0 and matrix[i][j-1] < matrix[i][j]: self.decreaseOut(l2*i+j-1, out ,q)
                if i<(l1-1) and matrix[i+1][j] < matrix[i][j]: self.decreaseOut(l2*(i+1)+j, out, q)
                if j<(l2-1) and matrix[i][j+1] < matrix[i][j]: self.decreaseOut(l2*i+j+1, out, q)
                #print(epoch, out, q)
        return epoch
            
