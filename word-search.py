# https://leetcode.com/problems/word-search/description/
# DFS
# Time: O(3^w), Space: O(3^w)
# w = len(word)
# 3^ = 4 directions - can't go back where I come from
# if I can't modify input board, I need additional space : O(3^w * b) b = len(board)
class Solution:
    def exist(self, board, word):
        l1 = len(board)
        if l1==0: return False
        l2 = len(board[0])
        if l2==0: return False
        
        for i in range(l1):
            for j in range(l2):
                if self._dfs(board, word, i, j, 0, l1, l2): return True
        return False
    
    def _dfs(self, board, word, i, j, k, l1, l2):
        #print(k, i, j, board)
        if board[i][j]==word[k]:
            if (k+1)==len(word): return True
            else:
                temp = board[i][j]
                board[i][j] = '#'
                if i>0:
                    if self._dfs(board, word, i-1, j, k+1, l1, l2): return True
                if j>0:
                    if self._dfs(board, word, i, j-1, k+1, l1, l2): return True
                if i<(l1-1):
                    if self._dfs(board, word, i+1, j, k+1, l1, l2): return True
                if j<(l2-1):
                    if self._dfs(board, word, i, j+1, k+1, l1, l2): return True
                board[i][j] = temp
        return False
        
