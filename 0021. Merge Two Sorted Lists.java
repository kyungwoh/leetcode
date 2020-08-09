/*
21. Merge Two Sorted Lists
merge sort랑 같은 방식으로 한다. 두 값 중에 작은 걸 prev.next에 붙이고, prev와 해당 노드를 next한다. 근데 한 쪽이 남을 경우가 있는데, 남는 부분은 next를 고칠 필요가 없이 그냥 붙이면 된다. while(둘 다 널이 아닐 때만 돌고), 그 다음에 prev.next = 널이 아닌 쪽을 붙이면 된다. 둘 다 끝났어도 어차피 null이므로 뭘 붙이던 상관없다. 이러면 좀 더 간단하다.
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
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(), prev = head;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                prev.next = l1;
                l1 = l1.next;
            } else {
                prev.next = l2;
                l2 = l2.next;
            }
            prev = prev.next;
        }
        prev.next = l1 == null? l2 : l1;
        return head.next;
    }
}