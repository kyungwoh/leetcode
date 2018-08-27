// https://leetcode.com/problems/remove-nth-node-from-end-of-list/description/
// one-pass: save to the queue (length = n+1)
// Time: O(k) k=len(List)
// Space: O(n)
public class Solution {
    public ListNode RemoveNthFromEnd(ListNode head, int n) {
        LinkedList<ListNode> q = new LinkedList<ListNode>();
        ListNode curr = head;
        while(curr!=null){
            q.AddLast(curr);
            if(q.Count>(n+1)) q.RemoveFirst();
            curr = curr.next;
        }
        ListNode first = q.First.Value;
        if(q.Count==n){
            head = head.next;
            first.next = null;
        }else{
            ListNode second = first.next, third = second.next;
            first.next = third;
            second.next = null;
        }
        return head;
    }
}
