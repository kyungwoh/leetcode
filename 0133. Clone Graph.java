/*
133. Clone Graph
deep copy하는 문제. 만약 이게 clone tree라면 중복 체크를 할 필요가 없다. 하지만 graph이기 때문에 중복 체크를 해야 한다.
-> 찾아보니 그런 문제가 이미 있다. Clone N-ary Tree

중복체크는 Map<old, new> 또는 Node[old] = new 로 저장해놓고, 있으면 그걸 바로 return한다. visited + memo 다.
head부터 시작해서 neighbors로 dfs 또는 bfs로 타고 내려가면 된다. recursive하게 푸는게 제일 코드가 짧다.
*/
class Solution {
    Map<Node, Node> map = new HashMap<>();
    public Node cloneGraph(Node node) {
        if (node == null) return null;
        if (map.containsKey(node)) return map.get(node);
        
        Node node2 = new Node(node.val);
        map.put(node, node2);
        for (Node neighbor : node.neighbors) {
            node2.neighbors.add(cloneGraph(neighbor));
        }
        return node2;
    }
}
class Solution {
    Node[] visited = new Node[101];
    public Node cloneGraph(Node node) {
        if (node == null) return null;
        if (visited[node.val] != null) return visited[node.val];
        
        Node node2 = new Node(node.val);
        visited[node.val] = node2;
        for (Node neighbor : node.neighbors) {
            node2.neighbors.add(cloneGraph(neighbor));
        }
        return node2;
    }
}
class Solution {
    Node[] nodes2;
    public Node cloneGraph(Node node) {
        if (node == null) return null;
        
        nodes2 = new Node[101];
        Node node2 = new Node(node.val);
        traverse(node, node2);
        return node2;
    }
    void traverse(Node n, Node n2) {
        nodes2[n.val] = n2;
        if (n.neighbors != null && n.neighbors.size() > 0) {
            for (Node nn : n.neighbors) {
                if (nodes2[nn.val] != null) {
                    n2.neighbors.add(nodes2[nn.val]);
                } else {
                    Node nn2 = new Node(nn.val);
                    traverse(nn, nn2);
                    n2.neighbors.add(nn2);
                }
            }
        }
    }
}
/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;
    
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
*/