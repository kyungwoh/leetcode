/*
111. Minimum Depth of Binary Tree

BFS나 DFS로 풀면서, leaf node를 찾아서 그때 depth로 판단한다.
BFS가 좀 더 빠를 수 있는게, 각 depth별로 가니까 아무거나 제일 먼저 나오는 leaf node에서 return하면 된다.
반면 DFS는 leaf node마다 min 값을 갱신해서, 맨 마지막에 최종 min 값을 return해야 한다.

어느 경우나 시간, 공간 복잡도는 같다.
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
    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        int depth = 1;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                TreeNode n = q.poll();
                if (n.left == null && n.right == null) return depth;
                if (n.left != null) q.offer(n.left);
                if (n.right != null) q.offer(n.right);
            }
            depth++;
        }
        return depth;
    }
}