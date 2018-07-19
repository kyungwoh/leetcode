// https://leetcode.com/problems/burst-balloons/description/
// Divide & Conquer + memo
// Time: O(n^2), Space: O(n^2)
class Solution {
    int[] nums2;
    int[][] memo;
    public int maxCoins(int[] nums) {
        int len = nums.length;
        if(len==0) return 0;
        nums2 = new int[len+2];
        for(int i=0; i<len; i++) nums2[i+1] = nums[i];
        nums2[0] = 1;
        nums2[len+1] = 1;
        memo = new int[len+2][len+2];
        return dfs(0, len+1);
    }
    private int dfs(int l, int r){
        if(l+1==r) return 0;
        if(memo[l][r]>0) return memo[l][r];
        int ans = 0;
        for(int i=l+1; i<r; i++){
            ans = Math.max(ans, nums2[l]*nums2[i]*nums2[r] + dfs(l,i) + dfs(i,r) );
        }
        memo[l][r] = ans;
        return ans;
    }
}
