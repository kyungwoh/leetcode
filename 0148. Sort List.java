/*
148. Sort List
그냥 merge sort하면 되는데, single linked list라서 조금 까다롭다.
1. 일단 전체 len을 구해놓는다. 매번 구해도 시간복잡도에 영향을 주진 않지만 그래도 약간 빨라진다.
2. 반으로 나눠서 재귀 호출 (주의: 중간을 null로 끊어줘야 한다)
3. 둘을 merge
4. return
*/
class Solution {
    public ListNode sortList(ListNode head) {
        ListNode curr = head;
        int len = 0;
        while (curr != null) {
            len++;
            curr = curr.next;
        }
        
        return sort(head, len);
    }
    ListNode sort(ListNode head, int len) {
        if (head == null || head.next == null) return head;
        
        // cut half
        ListNode curr = head;
        int halfLen = len/2;
        for (int i = 0; i < halfLen - 1; i++) {
            curr = curr.next;
        }
        ListNode next = curr.next;
        curr.next = null;
        
        // recursive call
        ListNode left = sort(head, halfLen);
        ListNode right = sort(next, len - halfLen);
        
        // merge
        ListNode newHead = null, prev = null;
        while (left != null || right != null) {
            if (left == null || (right != null && left.val > right.val)) {
                curr = right;
                right = right.next;
            } else {
                curr = left;
                left = left.next;
            }
            
            if (newHead == null) newHead = curr;
            if (prev != null) prev.next = curr;
            prev = curr;
        }
        
        // return
        return newHead;
    }
}