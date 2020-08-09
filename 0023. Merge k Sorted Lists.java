/*
23. Merge k Sorted Lists
merge sort처럼 k개 중에 제일 작은 걸 빼서 붙여가면 되는데, 이 k개 중에 제일 작은 걸 가져오는데 PriorityQueue를 쓰면 logk가 되어 O(nlogk)에 풀 수 있다. 맨 처음 1개씩은 먼저 붙여놓고 시작하낟. 단 ans에 붙이고 나서 항상 curr.next = null 해줘야 꼬이는 걸 방지할 수 있다. 붙이려면 prev, 결과를 위해 head가 있어야 함. PriorityQueue도 lambda comparator가 된다. (a,b)->Integer.compare(a,b)
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
    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> pq = new PriorityQueue<>((a, b) -> Integer.compare(a.val, b.val));
        for (ListNode l : lists) {
            if (l != null) pq.offer(l);
        }
        ListNode prev = null, head = null;
        while (pq.size() > 0) {
            ListNode curr = pq.poll();
            if (head == null) head = curr;
            if (prev != null) prev.next = curr;
            prev = curr;
            if (curr.next != null) pq.offer(curr.next);
            curr.next = null;
        }
        return head;
    }
}