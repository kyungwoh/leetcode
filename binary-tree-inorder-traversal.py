# https://leetcode.com/problems/binary-tree-inorder-traversal/description/
# Inorder traversal + DFS
# Time: O(n), Space: O(n)
class Solution:
    def inorderTraversal(self, root):
        def dfs(cur):
            if cur:
                dfs(cur.left)
                ans.append(cur.val)
                dfs(cur.right)
        ans=[]
        dfs(root)
        return ans
