/*
143. Reorder List

문제 3개를 합친 건데 1. 중간 찾아서 자르기 2. 뒤의 반을 뒤집기 3. 앞과 뒤를 interweave하기
문제 3개를 그대로 풀면 된다.

1. 중간 찾는 건 slow, fast runner로. 그래서 slow가 뒤의 반의 시작이다. (뒤의 반이 짧을 수 잇다)
2. 뒤의 반을 뒤집으면, 맨 마지막 prev가 다시 뒤의 반의 시작이다.
3. 앞의 반과 뒤의 반을 interweave한다. next를 저장하면서 엮어가면 된다. 뒤의 반이 항상 짧으므로 여기만 while 체크.
*/
class Solution {
    public void reorderList(ListNode head) {
        if (head == null) return;
        
        // cut in half
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        
        // reverse an end half
        ListNode curr = slow, prev = null;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        
        // interweave two halves
        ListNode curr1 = head, curr2 = prev;
        while (curr2.next != null) {
            ListNode next1 = curr1.next;
            curr1.next = curr2;
            curr1 = next1;
            
            ListNode next2 = curr2.next;
            curr2.next = curr1;
            curr2 = next2;
        }
    }
}