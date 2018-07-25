// https://leetcode.com/problems/binary-tree-level-order-traversal/description/
// BFS
// Time: O(n), Space: O(n)
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
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> rList = new ArrayList<List<Integer>>();
        if(root!=null){
            ArrayDeque<TreeNode> que = new ArrayDeque<TreeNode>();
            que.offer(root);
            while(!que.isEmpty()){
                List<Integer> curL = new ArrayList<Integer>();
                int size = que.size();
                for(int i=0; i<size; i++){
                    TreeNode curN = que.poll();
                    curL.add(curN.val);
                    if(curN.left!=null) que.offer(curN.left);
                    if(curN.right!=null) que.offer(curN.right);
                }
                rList.add(curL);
            }
        }
        return rList;
    }
}
