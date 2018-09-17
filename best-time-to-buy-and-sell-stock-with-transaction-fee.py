# https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/description/
# DP (with 2 values)
# Time: O(n), Space: O(1)
class Solution:
    def maxProfit(self, prices, fee):
        afterSell = 0
        afterBuy = -prices[0]
        for i in range(1,len(prices)):
            afterSell = max(afterSell, afterBuy+prices[i]-fee)
            afterBuy = max(afterBuy, afterSell-prices[i])
        return afterSell
