# https://leetcode.com/problems/validate-binary-search-tree/description/
# Inherit min & max of subtrees
# Time: O(n), Space: O(n)

# Definition for a binary tree node.
# class TreeNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

class Solution(object):
    def isValidBST(self, root):
        if not root: return True
        if root.left:
            if not self._isValidBST(root.left, None, root.val):
                return False
        if root.right:
            if not self._isValidBST(root.right, root.val, None):
                return False
        return True
    
    def _isValidBST(self, node, minVal, maxVal):
        if node:
            #print(node.val, minVal, maxVal)
            if minVal!=None and minVal>=node.val: return False
            if maxVal!=None and maxVal<=node.val: return False
            if node.left:
                if not self._isValidBST(node.left, minVal, node.val):
                    return False
            if node.right:
                if not self._isValidBST(node.right, node.val, maxVal):
                    return False
        return True
        
