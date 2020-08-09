/*
674. Longest Continuous Increasing Subsequence
계속 증가하면 len++; 하면서 maxLen을 찾아가고,
그러다가 감소하거나 같으면 다시 len = 1 부터 시작한다.
time O(n), space O(1)
*/
class Solution {
    public int findLengthOfLCIS(int[] nums) {
        int len = 0, max = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i-1] >= nums[i]) len = 1;
            else len++;
            max = Math.max(max, len);
        }
        return max;
    }
}