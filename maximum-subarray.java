// https://leetcode.com/problems/maximum-subarray/description/
// sum = rangeSum[i] - rangeSum[j];
// so, find max rangeSum[i] & min rangeSum[j];
// Time: O(n), Space : O(1)
class Solution {
    public int maxSubArray(int[] nums) {
        int l = nums.length;
        if(l==0) return 0;
        int sum = 0, minSum = 0, ans = Integer.MIN_VALUE;
        for(int n : nums){
            sum += n;
            ans = Math.max(ans, sum-minSum);
            minSum = Math.min(minSum, sum);
        }
        return ans; 
    }
}
