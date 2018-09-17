# https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/description/
# DP: make 4 states (after first buy -> after first sell -> after second buy -> after second sell)
# Time: O(n), Space: O(1)
class Solution:
    def maxProfit(self, prices):
        afterBuy1,afterSell1,afterBuy2,afterSell2=-sys.maxsize-1,0,-sys.maxsize-1,0
        for p in prices:
            afterSell2=max(afterSell2,afterBuy2+p)
            afterBuy2=max(afterBuy2,afterSell1-p)
            afterSell1=max(afterSell1,afterBuy1+p)
            afterBuy1=max(afterBuy1,-p)
        return afterSell2
