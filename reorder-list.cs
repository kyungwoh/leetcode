// https://leetcode.com/problems/reorder-list/description/
// 1. Find the middle node
// 2. Reverse (mid to end)
// 3. Mix (start to mid) with (mid to end)
// Time: O(n), Space: O(1)
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     public int val;
 *     public ListNode next;
 *     public ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public void ReorderList(ListNode head) {
        if(head==null) return;
        
        //1. Find the middle
        ListNode slow = head, fast = head;
        while(fast!=null && fast.next!=null){
            slow = slow.next;
            fast = fast.next.next;
        }
        //Console.WriteLine(slow.val);
        //2. Reverse mid to end
        ListNode mid = slow, last = slow, curr = last.next;
        while(true){
            //Console.WriteLine(last.val+" "+curr);
            if(curr==null) break;
            else{
                ListNode next = curr.next;
                curr.next = last;
                if(last==mid) last.next = null;
                last = curr;
                curr = next;
                
            }
        }
        
        //3. Mix start-mid / end-mid
        ListNode n1 = head, n2 = last;
        while(n1!=null && n2!=null && n1!=mid && n2!=mid){
            //Console.WriteLine(n1.val+" "+n2.val);
            ListNode n1next = n1.next;
            n1.next = n2;
            ListNode n2next = n2.next;
            n2.next = n1next;
            n1 = n1next;
            n2 = n2next;
        }
    }
}
