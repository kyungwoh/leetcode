/*
572. Subtree of Another Tree
각 노드마다 다 새로 시작해서 확인해야 한다. 그냥 루트 값이 같다고 거기서만 시작하다 말면, 일부만 일치하다 말고 그 아래에서 일치는 경우를 놓친다.
그래서 1. 현재 2. 왼쪽 3. 오른쪽 중에 하나만 맞으면 된다. (시작점이니까)
그러면 여기서 1. 현재를 찾아야 하는데 이때는 1. 현재 2. 왼쪽 3. 오른쪽 다 맞아야 같다. (서브트리 전체 다 맞기)

아니면 tree -> string serialize해서 contains()로 풀어도 된다.
serialize를 할 때 가장 중요한 건 preorder(root > left > right)로 돌아야 한다는 것이다. (위의 방법으로 할때는 순서가 상관없다)
left > root > right 로 하면  1 2 4 3 과 2 4 3 이 다른데 같다고 나온다. 왼쪽 노드가 더 있을 경우까지 같다고 나온다.

그리고 delim을 단순히 ,로 하면 12,34와 2,3이 같다고 나온다. [12][34] [2][3] 이렇게 앞뒤를 감싸주거나, 한 노드당 한 char만 쓰거나 해야 한다.
*/
class Solution {
    public boolean isSubtree(TreeNode s, TreeNode t) {
        if (s == null && t == null) return true;
        if (s == null || t == null) return false;
        return isSubtree(s.left, t) || isSubtree(s.right, t) || equals(s, t);
    }
    public boolean equals(TreeNode s, TreeNode t) {
        if (s == null && t == null) return true;
        if (s == null || t == null) return false;
        return s.val == t.val && equals(s.left, t.left) && equals(s.right, t.right);
    }
}

class Solution {
    public boolean isSubtree(TreeNode s, TreeNode t) {
        if (s == null) return t == null;
        if (isSubtree(s.left, t) || isSubtree(s.right, t)) return true;
        return dfs(s, t);
    }
    public boolean dfs(TreeNode s, TreeNode t) {
        if (s == null || t == null) return s == null && t == null;
        if (s.val != t.val) return false;
        return dfs(s.left, t.left) && dfs (s.right, t.right);
    }
}

class Solution {
    public boolean isSubtree(TreeNode s, TreeNode t) {
        String s1 = serialize(s), s2 = serialize(t);
        return s1.contains(s2);
    }
    String serialize(TreeNode n) {
        StringBuilder sb = new StringBuilder();
        traverse(n, sb);
        return sb.toString();
    }
    void traverse(TreeNode n, StringBuilder sb) {
        if (n == null) {
            sb.append('#');
            return;
        }
        sb.append((char)n.val);
        traverse(n.left, sb);
        traverse(n.right, sb);
    }
}

class Solution {
    public boolean isSubtree(TreeNode s, TreeNode t) {
        String s1 = serialize(s);
        String s2 = serialize(t);
        return s1.contains(s2);
    }
    String serialize(TreeNode n) {
        StringBuilder sb = new StringBuilder();
        traverse(n, sb);
        return sb.toString();
    }
    void traverse(TreeNode n, StringBuilder sb) {
        if (n == null) {
            sb.append('.');
            return;
        }
        sb.append('[').append(n.val).append(']');
        traverse(n.left, sb);
        traverse(n.right, sb);
    }
}
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */