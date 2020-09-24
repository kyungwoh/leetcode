/*
142. Linked List Cycle II
Set으로 반복되는 노드를 찾아도 되지만, slow / fast runner로 찾아도 된다.
일단 slow == fast 가 있는 것만으로 cycle 여부를 알 수 있지만, 진입점을 알고 싶다면 같은 속도로 한 번 더 돌아야 한다.
Time O(n), Space O(1)
*/
public class Solution {
    public ListNode detectCycle(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                slow = head;
                while (slow != fast) {
                    slow = slow.next;
                    fast = fast.next;
                }
                return slow;
            }
        }
        return null;
    }
}