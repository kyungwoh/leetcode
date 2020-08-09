/*
98. Validate Binary Search Tree
recursive하게 한다면 min, max값을 내려보내서 검증. inorder traversal로 앞 값과 현재 값을 비교해서 순서대로인지 확인. stack을 이용할수도 있음
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
    public boolean isValidBST(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        Integer prev = null;
        while (!stack.empty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (prev != null && prev >= root.val) return false;
            prev = root.val;
            root = root.right;
        }
        return true;
    }
}
class Solution {
    public boolean isValidBST(TreeNode root) {
        return valid(root, null, null);
    }
    boolean valid(TreeNode root, Integer min, Integer max) {
        if (root == null) return true;
        if (min != null && root.val <= min) return false;
        if (max != null && root.val >= max) return false;
        return valid(root.left, min, root.val) && valid(root.right, root.val, max);
    }
}