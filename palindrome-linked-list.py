# https://leetcode.com/problems/palindrome-linked-list/description/
# Time: O(n), Space: O(n)
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution:
    def isPalindrome(self, head):
        memo = []
        slow, fast = head, head
        while slow and fast and fast.next:
            memo.append(slow.val)
            slow = slow.next
            fast = fast.next.next
        
        if fast: slow = slow.next
        i = -1
        while slow:
            if memo[i]!=slow.val: return False
            slow = slow.next
            i -= 1
        return True          
        
