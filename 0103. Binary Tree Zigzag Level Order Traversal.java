/*
103. Binary Tree Zigzag Level Order Traversal
순서를 뒤집으려면 DQ를 쓰면 된다. 순서대로 addFirst() 하던가 addLast() 하면 차곡차곡 쌓인다. Q는 그대로 가야 한다. BFS가 아니라 DFS로 해도 평소처럼 level별로 쌓아줄때 addFirst(), addLast() 하면 된다. 대신에 level % 2 == 0 이렇게 처음, 다음, 그 다음마다 뒤집을지 결정한다. level을 0부터 시작한다면 0이 1번째가 되므로 헷갈리지 말자.
*/
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
 class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) return ans;
        
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        
        boolean seq = true;
        while (!q.isEmpty()) {
            LinkedList<Integer> dq = new LinkedList<>();
            int size = q.size();
            for (int i = 0; i < size; i++) {
                TreeNode curr = q.poll();
                if (curr.left != null) q.offer(curr.left);
                if (curr.right != null) q.offer(curr.right);
                
                if (seq) dq.addLast(curr.val);
                else dq.addFirst(curr.val);
            }
            ans.add(dq);
            seq = !seq;
        }
        
        return ans;
    }
}
class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) return ans;
        
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        q.offer(null);
        
        LinkedList<Integer> dq = new LinkedList<>(); 
        boolean seq = true;
        while (!q.isEmpty()) {
            TreeNode curr = q.poll();
            if (curr != null) {
                if (seq) dq.offerLast(curr.val);
                else dq.offerFirst(curr.val);
                
                if (curr.left != null) q.offer(curr.left);
                if (curr.right != null) q.offer(curr.right);
            } else {
                ans.add(dq);
                dq = new LinkedList<>();
                if (!q.isEmpty()) q.offer(null);
                seq = !seq;
            }
        }
        return ans;
    }
}
class Solution {
    List<LinkedList<Integer>> ans;
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        ans = new ArrayList<>();
        dfs(root, 0);
        List<List<Integer>> temp = new ArrayList<>();
        for (LinkedList a : ans) temp.add(a);
        return temp;
    }
    void dfs(TreeNode root, int level) {
        if (root == null) return;
        if (ans.size() == level) ans.add(new LinkedList<>());
        
        if (level % 2 == 0) ans.get(level).addLast(root.val);
        else ans.get(level).addFirst(root.val);
        
        dfs(root.left, level + 1);
        dfs(root.right, level + 1);
    }
}