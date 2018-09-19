# https://leetcode.com/submissions/detail/177149898/
# Time: O(n), Space: O(n)
class Solution:
    def isSameTree(self, p, q):
        if not p and not q: return True
        elif not p or not q or p.val!=q.val: return False
        else: return self.isSameTree(p.left,q.left) and self.isSameTree(p.right,q.right)
