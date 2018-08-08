# https://leetcode.com/problems/kth-smallest-element-in-a-bst/description/
# In-order traversal
# Time: O(n), Space: O(height of tree)=O(logn)
class Solution:
    def kthSmallest(self, root, k):
        def traverse(node):
            if node:
                if node.left:
                    r = traverse(node.left)
                    if r!=None: return r
                self.cnt -= 1
                #print(self.cnt, k, node.val)
                if self.cnt==0: return node.val
                if node.right:
                    r = traverse(node.right)
                    if r!=None: return r
        
        self.cnt = k
        return traverse(root)
