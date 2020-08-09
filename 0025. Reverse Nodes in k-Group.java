/*
25. Reverse Nodes in k-Group
k-Group을 여러 번 불러야 하므로 recursive나 iterative하게 푼다.
먼저 현재 길이가 k보다 작은지 확인해서, 작으면 그대로 리턴하고, 같으면 reverse하고, 그 다음에 reversed된 리스트를 다음 리스트와 붙이고 새 head를 리턴한다.
현재 head와 tail이 반대로 되니까, new tail = old head.next = recursive하게 붙이면 된다. 그리고 new head = old tail을 리턴하면 된다.
*/
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode curr = head;
        int i;
        for(i = 0; curr != null && i < k; i++) curr = curr.next;
        if (i == k) {
            ListNode newHead = reverse(head, k);
            head.next = reverseKGroup(curr, k);
            return newHead;
        }
        return head;
    }
    ListNode reverse(ListNode head, int k) {
        ListNode prev = null, curr = head;
        for (; k > 0; k--) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }
}