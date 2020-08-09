/*
322. Coin Change
DP + memo : top-down(dfs), bottom-up(loop)
메모할 때 초기값, skip 조건, overflow, 최종 값 주의!
*/
class Solution {
    public int coinChange(int[] coins, int amount) {
        int[] memo = new int[amount+1];
        memo[0] = 0;
        for (int i = 1; i <= amount; i++) memo[i] = Integer.MAX_VALUE;
        for (int i = 0; i <= amount; i++) {
            if (memo[i] == Integer.MAX_VALUE) continue;
            for (int c : coins) {
                if (c > (amount - i)) continue;
                memo[i+c] = Math.min(memo[i+c], memo[i]+1);
            }
        }
        return memo[amount] == Integer.MAX_VALUE ? -1 : memo[amount];
    }
}