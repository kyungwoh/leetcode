/*
152. Maximum Product Subarray
곱하는 거다보니 0도 있고 마이너스도 있는데, 음수는 두번 곱하면 다시 양수가 되니까 문제다.
그러니까 max, min 2개를 들고 다니면서 계속 곱해나가면 greedy하게 풀 수 있다.

n, max * n, min * n 이 3개 중에 max, min 값을 찾아서 계속 덮어쓰면 된다.
다만 덮어쓰면 값이 변하니까, temp로 저장해놨다가 덮어쓴다.
*/
class Solution {
    public int maxProduct(int[] nums) {
        if (nums.length == 0) return 0;
        
        int max = nums[0], min = nums[0], ans = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int n = nums[i];
            int newMax = Math.max(n, Math.max(max * n, min * n));
            int newMin = Math.min(n, Math.min(max * n, min * n));
            max = newMax;
            min = newMin;
            ans = Math.max(ans, max);
        }
        return ans;
    }
}
class Solution {
    public int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        
        int pSumPos = nums[0] > 0 ? nums[0] : 0;
        int pSumNeg = nums[0] < 0 ? nums[0] : 0;
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int pSumPosNew = (nums[i] >= 0 ? pSumPos : pSumNeg) * nums[i];
            int pSumNegNew = (nums[i] >= 0 ? pSumNeg : pSumPos) * nums[i];
            pSumPos = pSumPosNew > nums[i] ? pSumPosNew : nums[i];
            pSumNeg = pSumNegNew < nums[i] ? pSumNegNew : nums[i];
            if (max < pSumPos) max = pSumPos;
        }
        return max;
    }
}