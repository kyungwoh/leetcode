# https://leetcode.com/problems/linked-list-cycle/description/
# Slow & Fast runner
# Time: O(n), Space: O(1)
class Solution(object):
    def hasCycle(self, head):
        slow = fast = head
        while slow and fast and fast.next:
            slow = slow.next
            fast = fast.next.next
            if slow and fast and slow==fast: return True
        return False
