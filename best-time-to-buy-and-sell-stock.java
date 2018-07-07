// https://leetcode.com/problems/best-time-to-buy-and-sell-stock/description/
class Solution {
    // 1 Path & save min number, max profit
    // Time: O(n), Space: O(1)
    public int maxProfit(int[] prices) {
        int l = prices.length;
        if(l==0) return 0;
        int min = Integer.MAX_VALUE, profit = 0;
        for(int p : prices){
            min = Math.min(min, p);
            profit = Math.max(profit, p-min);
        }
        return profit;
    }
}
