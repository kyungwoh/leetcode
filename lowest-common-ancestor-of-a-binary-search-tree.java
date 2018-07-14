// https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/description/
// This is BST, so compare values & go deep (DFS)
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
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(p.val>q.val){
            TreeNode temp = p;
            p = q;
            q = temp;
        }
        if(q.val<root.val && root.left!=null) return lowestCommonAncestor(root.left, p, q);
        if(p.val>root.val && root.right!=null) return lowestCommonAncestor(root.right, p, q);
        return root;
    }
}
