/*
121. Best Time to Buy and Sell Stock
Valley - peak detection
DP로 풀기보단 최저점, 최고점을 포인터를 이동해가면서 찾는 것으로 풀어야 함
매번 min값과 (curr-min) 값을 갱신해간다. 산 다음에 파는 것이기 때문에, 사는 시점은 최소값을 찾고, 그 다음에 팔 때 최대 이익을 구해야 한다. (순서가 중요!) 단순히 max값을 찾으면 안됨. Max를 찾았을 때 min값도 전체의 min 이었는지를 알아야 함. 그러므로 차라리 max를 구하기 말고, 그때 그때의 curr-min을 구해야 함.
*/
class Solution {
    public int maxProfit(int[] nums) {
        int max = 0, min = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int n = nums[i];
            if (n < min) min = n;
            else if (n - min > max) max = n - min;
        }
        return max;
    }
}