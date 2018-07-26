# https://leetcode.com/problems/populating-next-right-pointers-in-each-node/description/
# Definition for binary tree with next pointer.
# class TreeLinkNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None
#         self.next = None

class Solution:
    # BFS without queue
    # Time: O(n), Space: O(1)
    def connect(self, root):
        if root:
            currLeftmost = root
            while currLeftmost.left:
                curr = currLeftmost
                while curr:
                    curr.left.next = curr.right
                    if curr.next: curr.right.next = curr.next.left
                    curr = curr.next
                currLeftmost = currLeftmost.left
                
                    
    # BFS with queue
    # Time: O(n), Space: O(n)
    def connect2(self, root):
        q = collections.deque()
        if root: q.append(root)
        while q:
            l = len(q)
            last = None
            for i in range(l):
                curr = q.popleft()
                if curr.left: q.append(curr.left)
                if curr.right: q.append(curr.right)
                
                if last: last.next = curr
                last = curr
