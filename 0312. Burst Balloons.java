/*
312. Burst Balloons
DP로 풀어야 한다. dp(l,r) = max(nums[l]*nums[i]*nums[r] + dp(l,i) + dp(i,r))
Time O(n^3) = memo n^2 * for loop n
Space O(n^2)

계산의 편의를 위해 맨 왼쪽과 오른쪽을 하나씩 padding하고, 1로 set한다.
그리고 한칸씩 전진하면서 재귀해서 최대값을 찾는다.
*/
class Solution {
    int[] nums2;
    int[][] memo;
    public int maxCoins(int[] nums) {
        int len = nums.length;
        nums2 = new int[len+2];
        for (int i = 0; i < len; i++) nums2[i+1] = nums[i];
        nums2[0] = 1;
        nums2[len+1] = 1;
        memo = new int[len+2][len+2];
        return dp(0, len+1);
    }
    int dp(int l, int r){
        if (l+1 == r) return 0;
        if(memo[l][r] > 0) return memo[l][r];
        int ans = 0;
        for(int i = l+1; i < r; i++){
            ans = Math.max(ans, nums2[l]*nums2[i]*nums2[r] + dp(l,i) + dp(i,r));
        }
        return memo[l][r] = ans;
    }
}