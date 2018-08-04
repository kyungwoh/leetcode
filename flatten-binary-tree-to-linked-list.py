# https://leetcode.com/problems/flatten-binary-tree-to-linked-list/description/
# Use stack (left first)
# Time: O(n), Space: O(h) h=height

# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

class Solution:
    def flatten(self, root):
        if root:
            stack = []
            stack.append(root)
            prev = None
            while stack:
                curr = stack.pop()
                if prev:
                    prev.left = None
                    prev.right = curr
                prev = curr

                if curr.right: stack.append(curr.right)
                if curr.left: stack.append(curr.left)
