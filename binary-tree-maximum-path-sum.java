// https://leetcode.com/problems/binary-tree-maximum-path-sum/description/
// search all cases, and save the max value (assume it's root)
// Time: O(n), Space: O(1)
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
    int max = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        int ans = dfs(root);
        return Math.max(max, ans);
    }
    private int dfs(TreeNode root){
        int left = root.left==null ? 0 : dfs(root.left);        
        int right = root.right==null ? 0 : dfs(root.right);
        int ans = Math.max(root.val, Math.max(left+root.val, right+root.val));
        max = Math.max(max, Math.max(ans, left+root.val+right));
        return ans;
    }
}
