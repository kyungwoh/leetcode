// https://leetcode.com/problems/reverse-nodes-in-k-group/description/
// 1. Save kheadPrev, khead, ktail (on every k steps)
// 2. kheadPrev->curr
// 3. Reverse khead->ktail
// 4. khead->next
// Time: O(n), Space: O(1)
public class Solution {
    public ListNode ReverseKGroup(ListNode head, int k) {
        if(k>1){
            int i=1;
            ListNode curr=head, khead=head, kheadPrev=head;
            while(curr!=null){
                //Console.WriteLine("i="+i+" curr="+curr.val);
                if(i%k==0){
                    if(i/k==1) head=curr;
                    else kheadPrev.next=curr;
                    
                    ListNode ktail=curr;
                    curr=khead;
                    //Console.WriteLine("khead="+khead.val+" ktail="+ktail.val);
                    ListNode next=curr.next;
                    while(curr!=ktail){
                        ListNode prev=curr;
                        curr=next;
                        next=next.next;                        
                        curr.next=prev;
                        //Console.WriteLine(prev.val+" "+curr.val+" "+next.val+" "+curr.next.val);
                    }
                    //Console.WriteLine("khead="+khead.val+" "+khead.next.val);
                    khead.next=next;
                    //Console.WriteLine("khead="+khead.val+" "+khead.next.val);
                    kheadPrev=khead;
                    khead=next;
                    curr=next;                    
                }else curr = curr.next;                
                i++;
            }
        }
        return head;
    }
}
