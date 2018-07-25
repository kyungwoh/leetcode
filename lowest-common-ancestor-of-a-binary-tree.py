# https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/description/
# 1. Recursively go down left & right (if root is matched => root)
# 2. If found left & right => root
#    If found left only => left
#    If found right only = right
# Time: O(n), Space: O(n)

# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

class Solution:
    def lowestCommonAncestor(self, root, p, q):
        if root==None or root==p or root==q: return root
        if root.left!=None: root.left = self.lowestCommonAncestor(root.left, p, q)
        if root.right!=None: root.right = self.lowestCommonAncestor(root.right, p, q)
        if root.left!=None:
            if root.right!=None:
                return root
            else:
                return root.left
        else:
            return root.right
