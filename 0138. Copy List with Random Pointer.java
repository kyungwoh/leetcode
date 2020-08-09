/*
138. Copy List with Random Pointer
노드의 값이 unique하지 않고, random 포인터가 n:1일 수 있고, null도 되기 때문에 간단히 하기가 어렵다. Map에 Key를 Node의 pointer로 잡는 수밖에 없다. 그래서 curr node와 currNew node의 쌍으로 찾아야 한다. 널 처리, 중복 처리, 처음 처리를 잘 해줘야 한다. 처음에 curr, currNew를 잡아주는 건 for loop 안에서 하기가 어렵다. 그냥 따로 하는 게 낫다. 아니면 curr -> currNew -> curr -> currNew 이렇게 interwine하면 curr.next = currNew 이므로 Map을 쓸 필요가 없다. 그러면 Space O(n) -> O(1)로 준다.
*/
/*
// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
*/
class Solution {
    Map<Node, Node> visited = new HashMap<>();
    public Node copyRandomList(Node head) {
        if (head == null) return null;
        
        Node curr = head;
        Node currNew = new Node(curr.val);
        visited.put(curr, currNew);
        
        while (curr != null) {
            currNew.random = getClone(curr.random);
            currNew.next = getClone(curr.next);
            
            curr = curr.next;
            currNew = currNew.next;
        }
        
        return visited.get(head);
    }
    Node getClone(Node curr) {
        if (curr == null) return null;
        if (!visited.containsKey(curr)) visited.put(curr, new Node(curr.val));
        return visited.get(curr);
    }
}
class Solution {
    public Node copyRandomList(Node head) {
        Node curr = head;
        while (curr != null) {
            Node next = curr.next;
            curr.next = new Node(curr.val);
            curr.next.next = next;
            curr = next;
        }
        curr = head;
        while (curr != null) {
            if (curr.random != null) curr.next.random = curr.random.next;
            curr = curr.next.next;
        }
        curr = head;
        Node newHead = curr == null ? null : curr.next;
        Node prevOld = null, prevNew = null;
        while (curr != null) {
            Node currNew = curr.next;
            if (prevOld != null) prevOld.next = curr;
            if (prevNew != null) prevNew.next = currNew;
            prevOld = curr;
            prevNew = currNew;
            curr = curr.next.next;
        }
        if (prevOld != null) prevOld.next = null;
        return newHead;
    }
}