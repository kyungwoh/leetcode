// https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/description/
// DFS with middle point (like inorder traversal)
// Time: O(n), Space: O(n)
public class Solution {
    public TreeNode SortedArrayToBST(int[] nums) {
        if(nums.Length==0) return null;
        else return dfs(nums, 0, nums.Length-1);
    }
    private TreeNode dfs(int[] nums, int s, int e){
        int m = (s+e)/2;
        TreeNode curr = new TreeNode(nums[m]);
        if(m-s>0) curr.left = dfs(nums, s, m-1);
        if(e-m>0) curr.right = dfs(nums, m+1, e);
        return curr;
    }
}
