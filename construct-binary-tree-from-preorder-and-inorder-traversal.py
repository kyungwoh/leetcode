# https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/description/
# Time: O(n), Space: O(n^2)
class Solution:
    def buildTree(self, preorder, inorder):
        if inorder:
            curr = TreeNode(preorder.pop(0))
            mid = inorder.index(curr.val)
            curr.left = self.buildTree(preorder, inorder[:mid])
            curr.right = self.buildTree(preorder, inorder[mid+1:])
            return curr
        return None
