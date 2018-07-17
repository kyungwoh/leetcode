// https://leetcode.com/problems/merge-two-sorted-lists/
// Just merge sort
// Time: O(n), Space: O(1)
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode curr = null, root = null;
        while(l1!=null && l2!=null){
            if(l1.val <= l2.val){
                //System.out.println(1+" "+l1.val);
                if(curr==null){
                    curr = l1;
                    root = curr;
                }else{
                    curr.next = l1;
                    curr = l1;
                }
                l1 = l1.next;
            }else{
                //System.out.println(2+" "+l2.val);
                if(curr==null){
                    curr = l2;
                    root = curr;
                }else{
                    curr.next = l2;
                    curr = l2;
                }
                l2 = l2.next;
            }
        }
        while(l1!=null){
            //System.out.println(11+" "+l1.val);
            if(curr==null){
                curr = l1;
                root = curr;
            }else{
                curr.next = l1;
                curr = l1;
            }
            l1 = l1.next;
        }
        while(l2!=null){
            //System.out.println(22+" "+l2.val);
            if(curr==null){
                curr = l2;
                root = curr;
            }else{
                curr.next = l2;
                curr = l2;
            }
            l2 = l2.next;
        }
        return root;
    }
}
