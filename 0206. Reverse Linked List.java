/*
206. Reverse Linked List

iterative하게 푼다면 curr.next = prev 하게 가면 된다.

recursive하게 간다면 head.next == null 일때까지 끝까지 가서 현재 노드를 돌려준다.
이것이 newHead가 될 것이다. 뒤집는 것이니까 tail이 head가 되는 것이다.
그러면서 empty list일때 head == null 처리도 같이 해주면 좋다.
head == null return null, head.next == null return head 니까 합쳐서 (head == null || head.next == null) return head; 하면 편리하다.
리턴은 굳이 안 받고 그냥 head.next == null 일때 newHead = head; 로 저장해놔도 된다.
그런 다음 포인터를 뒤집는데, 현재 것을 curr, 다음 것이 curr.next 니까 curr.next.next = curr; 해주는데,
그러면서 현재 것의 다음 것을 끊어야 하니까 curr.next = null; 도 꼭 해줘야 한다. 그런 다음 맨 마지막에 newHead를 돌려주면 된다.
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
    public ListNode reverseList(ListNode head) {
        ListNode prev = null, curr = head;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }
}
class Solution {
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode newHead = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }
}