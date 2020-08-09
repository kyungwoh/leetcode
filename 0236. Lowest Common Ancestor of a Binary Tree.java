/*
236. Lowest Common Ancestor of a Binary Tree
DFS로 각 노드마다 left, curr, right == p, q 가 가장 먼저 되는게 LCA다.
left, right에서 p, q와 일치하는 걸 위로 return해줘야 하는데,
p, q와 일치할 경우 p,q를 리턴하고 아니면 null을 리턴하게 할 수도 있고
 p, q 여부와 상관없이 일치하는 cnt를 리턴할 수도 있다.

 p, q가 일치하지 않으므로 어떻게 더해도 2를 넘지 않으니 == 2 로 검사하면 된다.
 그리고 일단 LCA찾으면 덮어쓰지 말아야 한다.

아니면 각 노드의 parent가 뭔지 Map에다 저장해놓는다. 이걸 p, q 둘 다 도달할때까지 계속 내려간다. Stack이던 Queue던 상관없다.
그 다음에 p의 parent들을 Set에다 다 넣어놓고, 그 다음에 q가 그 중에 있는지, 없으면 q의 parent로 올라가서 있을 때까지 반복한다.
그러면 p와 q의 가장 낮은 공통 parent를 찾을 수 있다.
*/
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    TreeNode lca, p, q;
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        lca = null;
        this.p = p;
        this.q = q;
        dfs(root);
        return lca;
    }
    int dfs(TreeNode root) {
        if (root == null || lca != null) return 0;
        
        int cnt = root == p || root == q ? 1 : 0;
        cnt += dfs(root.left);
        cnt += dfs(root.right);
        
        if (lca == null && cnt == 2) lca = root;
        return cnt;
    }
}
class Solution {
    TreeNode lca, p, q;
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        lca = null;
        this.p = p;
        this.q = q;
        dfs(root);
        return lca;
    }
    TreeNode dfs(TreeNode root) {
        if (root == null || lca != null) return null;
        TreeNode left = dfs(root.left);
        TreeNode right= dfs(root.right);
        boolean foundP = left == p || right == p || root == p;
        boolean foundQ = left == q || right == q || root == q;
        if (foundP && foundQ) { lca = root; return null; }
        if (foundP) return p;
        if (foundQ) return q;
        return null;
    }
}
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        Stack<TreeNode> stack = new Stack<>();
        Map<TreeNode, TreeNode> parent = new HashMap<>();
        parent.put(root, null);
        stack.push(root);
        while(!parent.containsKey(p) || !parent.containsKey(q)) {
            TreeNode node = stack.pop();
            if (node.left != null) {
                parent.put(node.left, node);
                stack.push(node.left);
            }
            if (node.right != null) {
                parent.put(node.right, node);
                stack.push(node.right);
            }
        }
        Set<TreeNode> ancestors = new HashSet<>();
        while (p != null) {
            ancestors.add(p);
            p = parent.get(p);
        }
        while (!ancestors.contains(q)) {
            q = parent.get(q);
        }
        return q;
    }
}
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        Queue<TreeNode> queue = new LinkedList<>();
        Map<TreeNode, TreeNode> parent = new HashMap<>();
        parent.put(root, null);
        queue.offer(root);
        while(!parent.containsKey(p) || !parent.containsKey(q)) {
            TreeNode node = queue.poll();
            if (node.left != null) {
                parent.put(node.left, node);
                queue.offer(node.left);
            }
            if (node.right != null) {
                parent.put(node.right, node);
                queue.offer(node.right);
            }
        }
        Set<TreeNode> ancestors = new HashSet<>();
        while (p != null) {
            ancestors.add(p);
            p = parent.get(p);
        }
        while (!ancestors.contains(q)) {
            q = parent.get(q);
        }
        return q;
    }
}