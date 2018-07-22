// https://leetcode.com/problems/min-cost-climbing-stairs/description/
class Solution {
    // Dynamic Programming
    // Time: O(n), Space: O(1)
    public int minCostClimbingStairs(int[] cost) {
        if(cost==null) return -1;
        int len = cost.length;
        int ans = 0, last1 = 0, last2 = 0;
        for(int i=2; i<=len; i++){
            ans = Math.min(last2+cost[i-2], last1+cost[i-1]);
            last2 = last1;
            last1 = ans;
        }
        return ans;        
    }
    // Dynamic Programming
    // Time: O(n), Space: O(n)
    public int minCostClimbingStairs2(int[] cost) {
        if(cost==null) return -1;
        int len = cost.length;
        int[] dp = new int[len+1];
        dp[0] = 0;
        if(len>0) dp[1] = 0;
        for(int i=2; i<=len; i++){
            dp[i] = Math.min(dp[i-2]+cost[i-2],dp[i-1]+cost[i-1]);
        }
        return dp[len];        
    }
}
