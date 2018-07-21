// https://leetcode.com/problems/maximum-depth-of-binary-tree/description/
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
    // DFS
    // Time: O(n), Space: O(n)
    public int maxDepth(TreeNode root) {
        if(root==null) return 0;
        else return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }
    // BFS
    // Time: O(n), Space: O(n)
    public int maxDepth2(TreeNode root) {
        int degree = 0;
        ArrayDeque<TreeNode> que = new ArrayDeque<TreeNode>();
        if(root!=null) que.offer(root);
        while(!que.isEmpty()){
            degree++;
            int len = que.size();
            for(int i=0; i<len; i++){
                TreeNode curr = que.poll();
                if(curr.left!=null) que.offer(curr.left);
                if(curr.right!=null) que.offer(curr.right);
            }
        }
        return degree;
    }
}
