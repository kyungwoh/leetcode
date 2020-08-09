/*
543. Diameter of Binary Tree
diameter도 path니까 마찬가지로, 각 node에서는 left + right 이고 이걸 parent로 return할때는 max(left, right)이다. diameter의 정의가 애매하므로, 다양한 edge case들을 가지고 물어봐야 한다. root == null 일때는 얼마인가? 왼쪽, 오른쪽이 같을 경우, 다를 경우. 그래서 example을 가지고 코드가 맞는지, 아니라면 어디에 +1 을 해주고 -1 을 해줘야 할지를 정한다.
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
    public int diameterOfBinaryTree(TreeNode root) {
        max = 0;
        dfs(root);
        return max;
    }
    int dfs(TreeNode curr) {
        if (curr == null) return 0;
        int left = dfs(curr.left);
        int right = dfs(curr.right);
        max = Math.max(max, left + right);
        return Math.max(left, right) + 1;
    }
}
class Solution {
    int max;
    public int diameterOfBinaryTree(TreeNode root) {
        max = 0;
        dfs(root);
        return max;
    }
    int dfs(TreeNode root) {
        if (root == null) return -1;
        int left = dfs(root.left) + 1;
        int right = dfs(root.right) + 1;
        max = Math.max(max, left + right);
        return Math.max(left, right);
    }
}
