/*
309. Best Time to Buy and Sell Stock with Cooldown
Greedy 이긴 한데, 3가지 상태를 갖고 다녀야 한다.
1. 산 후: 새로 사거나, 산 걸 들고 있거나
2. 판 후: 산 걸 팔거나, 판 걸 들고 있거나
3. 대기: 대기하거나, 산 후 cool down 한 후나
이렇게 매 단계마다 앞의 것으로 Max값을 갱신해간 다음에,
맨 마지막에 2번과 3번 중에 큰 걸 return하면 된다. (1번은 아직 안 팔았기 때문에 해당이 안됨)
*/
class Solution {
    public int maxProfit(int[] prices) {
        int bought = Integer.MIN_VALUE, sold = Integer.MIN_VALUE, waiting = 0;
        for (int p : prices) {
            int bought0 = bought, sold0 = sold, waiting0 = waiting;
            bought = Math.max(bought0, waiting0 - p); // buy
            sold = Math.max(sold0, bought0 + p); // sell
            waiting = Math.max(waiting0, sold0); // do nothing or cool down
        }
        return Math.max(sold, waiting);
    }
}