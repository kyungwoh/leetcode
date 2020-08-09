/*
53. Maximum Subarray
consecutive하니까 기존거+현재 아니면 기존거 끊고 현재부터 새로 시작하는 2가지 경우밖에 없다. 각 점마다 max(prev+curr, curr)로 구해가면 되는데, 이게 중간에 max가 있을 수 있으므로 각 점마다 중간에 max를 갱신해나간다. 아니면 divide and conquer
*/
class Solution {
    public int maxSubArray(int[] nums) {
        int curr = nums[0], max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            curr = Math.max(nums[i], curr + nums[i]);
            max = Math.max(max, curr);
        }
        return max;
    }
}