# https://leetcode.com/problems/intersection-of-two-linked-lists/description/
# len(A+B) = len(B+A) = len(dedicated + shared)
# Time: O(n), Space: O(1)
class Solution(object):
    def getIntersectionNode(self, headA, headB):
        if not headA or not headB: return None        
        curA, curB = headA, headB
        while curA is not curB:
            curA = headB if not curA else curA.next
            curB = headA if not curB else curB.next
        return curA
        
