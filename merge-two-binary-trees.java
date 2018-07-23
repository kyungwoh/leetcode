// https://leetcode.com/problems/merge-two-binary-trees/description/
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
    // BFS (merge t1+t2 to t1)
    // Time: O(n), Space: O(n)
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if(t1==null) return t2;
        ArrayDeque<TreeNode[]> que = new ArrayDeque<TreeNode[]>();
        que.offer(new TreeNode[]{t1, t2});
        while(!que.isEmpty()){
            TreeNode[] cur = que.poll();
            if(cur[1]!=null){
                cur[0].val += cur[1].val;
                
                if(cur[0].left==null) cur[0].left = cur[1].left;
                else que.offer(new TreeNode[]{cur[0].left, cur[1].left});
                
                if(cur[0].right==null) cur[0].right = cur[1].right;
                else que.offer(new TreeNode[]{cur[0].right, cur[1].right});
            }
        }
        return t1;
    }
    // Recursive (merge t1+t2 to new)
    // Time: O(n), Space: O(n)
    public TreeNode mergeTrees2(TreeNode t1, TreeNode t2) {
        int v1 = t1==null ? 0 : t1.val;
        int v2 = t2==null ? 0 : t2.val;
        TreeNode r = new TreeNode(v1+v2);
        if((t1!=null && t1.left!=null) || (t2!=null && t2.left!=null)){
            r.left = mergeTrees(t1==null ? null : t1.left, t2==null ? null : t2.left);
        }
        if((t1!=null && t1.right!=null) || (t2!=null && t2.right!=null)){
            r.right = mergeTrees(t1==null ? null : t1.right, t2==null ? null : t2.right);
        }
        if(t1==null && t2==null) return null;
        else return r;
    }
}
