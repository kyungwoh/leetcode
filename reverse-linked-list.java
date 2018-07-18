// https://leetcode.com/problems/reverse-linked-list/description/
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    // Recursive
    // Time: O(n), Space: O(n)
    public ListNode reverseList(ListNode head) {
        if(head==null || head.next==null) return head;
        else{
            ListNode next2 = reverseList(head.next);
            head.next.next = head;
            head.next = null;
            return next2;
        }
    }
    // Iterative
    // Time: O(n), Space: O(1)
    public ListNode reverseList2(ListNode head) {
        ListNode curr = head;
        if(curr!=null){
            ListNode next = curr.next;
            curr.next = null;
            while(next!=null){
                ListNode next2 = next.next;
                next.next = curr;
                curr = next;
                next = next2;
            }
        }
        return curr;
    }
}
