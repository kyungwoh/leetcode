/*
2. Add Two Numbers
맨 끝자리부터 순서대로 더해나가는데, 1. 노드가 있는지 2. 캐리가 남아있는지 체크해서 돈다. 그리고 캐리에 (노드가 있을때만) 노드 값을 더하고, 캐리 % 10 으로 생성, 캐리 / 10 으로 캐리한다. Curr.next에 붙이고, head 없으면 최초 값 저장하고 리턴한다.
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
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = null, curr = new ListNode();
        for (int c = 0; l1 != null || l2 != null || c > 0; c /= 10) {
            if (l1 != null) { c += l1.val; l1 = l1.next; }
            if (l2 != null) { c += l2.val; l2 = l2.next; }
            curr = curr.next = new ListNode(c % 10);
            if (head == null) head = curr;
        }
        return head;
    }
}