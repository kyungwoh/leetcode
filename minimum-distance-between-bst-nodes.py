# https://leetcode.com/problems/minimum-distance-between-bst-nodes/description/
# BST inorder traversal = ascending order
# Time: O(n), Space: O(1)

# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

class Solution:
    def minDiffInBST(self, root):
        self._minDiff = sys.maxsize
        self._lastVal = -sys.maxsize-1        
        self._traverse(root)        
        return self._minDiff
    
    def _traverse(self, node):
        if node.left: self._traverse(node.left)
        self._minDiff = min(self._minDiff, node.val - self._lastVal)
        self._lastVal = node.val
        if node.right: self._traverse(node.right)
        
