# https://leetcode.com/problems/diameter-of-binary-tree/description/
# DFS
# Time: O(n), Space: O(n)
class Solution:
    def diameterOfBinaryTree(self, root):
        """
        :type root: TreeNode
        :rtype: int
        """
        def dfs(node):
            if node:
                left = dfs(node.left)+1 if node.left else 0
                right = dfs(node.right)+1 if node.right else 0
                self.maxD = max(self.maxD, left+right)
                return max(left, right)
            else: return 0
        
        self.maxD = 0
        dfs(root)
        return self.maxD
