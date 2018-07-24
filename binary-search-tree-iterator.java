# https://leetcode.com/problems/binary-search-tree-iterator/description/
# Definition for a  binary tree node
# class TreeNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

# Using stack
# Time: Init O(h), hasNext O(1), next O(h)
# Space: Init O(h), hasNext O(1), next O(h)
class BSTIterator(object):
    
    def __init__(self, root):
        self._stack = []
        self._pushLefts(root)
    
    def _pushLefts(self, node):
        if node:
            self._stack += node,
            if node.left: self._pushLefts(node.left)

    def hasNext(self):
        return self._stack

    def next(self):
        node = self._stack.pop()
        self._pushLefts(node.right)
        return node.val

# Inorder traverse
# Time: Init O(n), hasNext O(1), next O(1)
# Space: Init O(n), hasNext O(1), next O(1)
class BSTIterator2(object):
    
    def __init__(self, root):
        """
        :type root: TreeNode
        """
        self._inorder = []
        self._i = 0
        self._travel(root)
    
    def _travel(self, node):
        if node:
            if node.left: self._travel(node.left)
            self._inorder += node.val,
            if node.right: self._travel(node.right)

    def hasNext(self):
        """
        :rtype: bool
        """
        if self._i < len(self._inorder): return True
        else: return False

    def next(self):
        """
        :rtype: int
        """
        self._i += 1
        return self._inorder[self._i - 1]

# Your BSTIterator will be called like this:
# i, v = BSTIterator(root), []
# while i.hasNext(): v.append(i.next())
