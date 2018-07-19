// https://leetcode.com/problems/burst-balloons/description/

class Solution {
    // Divide & Conquer + memo
    // Time: O(n^2), Space: O(n^2)
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
    // Dynamic Programming
    // Time: O(n^3), Space: O(n^2)
    public int maxCoins2(int[] nums) {
        int len = nums.length;
        if(len==0) return 0;
        
        int[][] dp = new int[len+2][len+2];
        for(int range=2; range<(len+2); range++){
            for(int left=0; left<(len+2-range); left++){
                int leftNum = 1;
                if(left>0) leftNum = nums[left-1];
                int right = left + range;
                int rightNum = 1;
                if(right<(len+1)) rightNum = nums[right-1];
                for(int mid=left+1; mid<right; mid++){
                    int midNum = nums[mid-1];
                    dp[left][right] = Math.max(dp[left][right], leftNum*midNum*rightNum + dp[left][mid] + dp[mid][right]);
                }
            }
        }
        return dp[0][len+1];
    }
}
