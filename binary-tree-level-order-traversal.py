# https://leetcode.com/problems/binary-tree-level-order-traversal/description/
# BFS
# Time: O(n), Space: O(n)

# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

class Solution:
    def levelOrder(self, root):
        """
        :type root: TreeNode
        :rtype: List[List[int]]
        """
        rList = []
        if root:
            que = collections.deque()
            que.append(root)
            while que:
                curL = []
                qLen = len(que)
                for i in range(qLen):
                    curN = que.popleft()
                    curL.append(curN.val)
                    if curN.left!=None: que.append(curN.left)
                    if curN.right!=None: que.append(curN.right)
                rList.append(curL)                   
        return rList
