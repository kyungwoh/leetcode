/*
259. 3Sum Smaller
다른 3Sum 문제들과 마찬가지로 정렬 후 two pointer로 푸는데, 이번에는 작은 경우의 수를 찾는 것이므로 cnt를 잘 증가시켜야 한다.
length = 2라면 2가지 경우, 3이라면 3가지 경우이므로 길이만큼 cnt에 더해주면 된다.
*/
class Solution {
    public int threeSumSmaller(int[] nums, int target) {
        Arrays.sort(nums);
        int cnt = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int l = i+1, r = nums.length-1; l < r;) {
                int sum = nums[l] + nums[i] + nums[r];
                if (sum < target) {
                    cnt += r - l;
                    l++;
                } else {
                    r--;
                }
            }
        }
        return cnt;
    }
}