/*
124. Binary Tree Maximum Path Sum
subtree의 local maximum과 total tree의 global maximum이 다를 수 있으므로, 각 root node마다 매번 local maximum을 찾아서 max를 업데이트한다. 그리고 리턴할 때, 한 줄로 가는 path이므로 root.val + max(leftMax, rightMax)를 한다. 이때 null = 0 으로 처리해도 된다. leftMax, rightMax가 0이어도, local maximum sum을 구할때도 root.val이 항상 들어가고, return value에도 root.val이 항상 들어가므로 root.val이 마이너스일때도 0을 더해주는 것이므로 영향이 가지 않는다.
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
    int max;
    public int maxPathSum(TreeNode root) {
        max = Integer.MIN_VALUE;
        dfs(root);
        return max;
    }
    int dfs(TreeNode root) {
        if (root == null) return 0;
        int leftMax = Math.max(0, dfs(root.left));
        int rightMax = Math.max(0, dfs(root.right));
        max = Math.max(max, leftMax + root.val + rightMax);
        return root.val + Math.max(leftMax, rightMax);
    }
}