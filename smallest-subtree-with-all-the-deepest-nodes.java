// https://leetcode.com/problems/smallest-subtree-with-all-the-deepest-nodes/description/
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
    // Search the deepest nodes by BFS & save every node's root history
    // Then find the deepest nodes' root with the history
    // Time: O(n^2), Space: O(n)
    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        ArrayDeque<TreeNode2> que = new ArrayDeque<TreeNode2>();
        ArrayDeque<ArrayDeque<TreeNode2>> queHist = new ArrayDeque<ArrayDeque<TreeNode2>>();
        que.offer(new TreeNode2(root,null));
        queHist.push(que.clone());
        while(true){
            ArrayDeque<TreeNode2> lastQue = que.clone();
            ArrayDeque<TreeNode2> newQue = new ArrayDeque<TreeNode2>();
            while(!que.isEmpty()){
                TreeNode curr = que.poll().curr;
                //System.out.println(curr.val);
                if(curr.left!=null) newQue.offer(new TreeNode2(curr.left, curr));
                if(curr.right!=null) newQue.offer(new TreeNode2(curr.right, curr));
            }
            //System.out.println(newQue.toString());
            if(newQue.isEmpty()) break;
            else{
                queHist.push(newQue.clone());
                que = newQue;
            }
        }
        //System.out.println(queHist.size());
        if(queHist.size()<=1) return root;
        que = queHist.pop();
        //System.out.println(que.toString());
        while(que.size()>1){
            ArrayDeque<TreeNode2> newQue = new ArrayDeque<TreeNode2>();
            HashSet<Integer> set = new HashSet<Integer>();
            ArrayDeque<TreeNode2> lastQue = queHist.pop();
            //System.out.println(lastQue.toString());
            while(!que.isEmpty()){
                TreeNode parent = que.poll().parent;
                if(!set.contains(parent.val)){
                    set.add(parent.val);
                }
            }
            
            for(int s : set){
                Iterator<TreeNode2> iter = lastQue.iterator();
                while(iter.hasNext()){
                    TreeNode2 curr2 = iter.next();
                    if(s==curr2.curr.val) newQue.offer(curr2);
                }
            }
            que = newQue;
            //System.out.println(que.toString());
            if(newQue.size()<=1) break;
        }
        return que.poll().curr;
    }
}
class TreeNode2{
    TreeNode curr, parent;
    TreeNode2(TreeNode curr, TreeNode parent){
        this.curr = curr;
        this.parent = parent;
    }
    //public String toString(){ return "("+curr.val+")"; }
}
