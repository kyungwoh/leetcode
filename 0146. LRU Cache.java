/*
146. LRU Cache
LRU는 리스트로 (큐, 스택 안됨) 맨 앞으로 넣고, 맨 뒤에서 빼는데 중간에서 지울 수 있어야 한다. 더블 링크드 리스트. 그리고 중간을 바로 가야 하니까 맵<키,노드>를 쓴다. Prev.next = curr; next.prev = curr; 더미 헤드, 테일을 만들고 서로 연결. add() remove() 펑션 구현. 일단 prev, curr, next를 저장해놓고 연결을 바꾼다. 그리고 맵에 넣었다 뺐다 한다. head에 붙이거나, tail.next에서 지우거나 한다. 쓰거나 추가할때마다 맨 뒤에서 지워서 맨 앞에다가 붙인다.
*/
class LRUCache {
    Map<Integer, Node> map;
    int size;
    int capacity;
    Node head, tail;
    
    public LRUCache(int capacity) {
        map = new HashMap<>();
        size = 0;
        this.capacity = capacity;
        head = new Node(0,0);
        tail = new Node(0,0);
        head.next = tail;
        tail.prev = head;
    }
    
    public int get(int key) {
        if (!map.containsKey(key)) return -1;
        Node node = map.get(key);
        remove(node);
        add(node);
        return node.v;
    }
    
    private void remove(Node node) {
        Node prev = node.prev;
        Node next = node.next;
        prev.next = next;
        next.prev = prev;
        node.prev = null;
        node.next = null;
    }
    
    private void add(Node node) {
        Node prev = head;
        Node next = head.next;
        node.prev = prev;
        node.next = next;
        prev.next = node;
        next.prev = node;
    }
    
    public void put(int key, int value) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            node.v = value;
            remove(node);
            add(node);
            return;
        }
        if (size == capacity) {
            map.remove(tail.prev.k);
            remove(tail.prev);
        }
        else size++;
        Node node = new Node(key, value);
        map.put(key, node);
        add(node);
    }
}
class Node {
    int k, v;
    Node prev, next;
    Node(int key, int value) { k = key; v = value; }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */