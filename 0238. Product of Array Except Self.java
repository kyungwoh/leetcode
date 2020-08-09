/* 238. Product of Array Except Self
O(n^2) -> O(n)으로 줄이는 방법은 two pass, 더하거나 곱하거나 하여튼 누적하는 값을 이용하면 된다. 앞에서 뒤로도 가보고, 뒤에서 앞으로도 가 본다. 더할 땐 0, 곱할 땐 1을 기본값으로 하면 자기 자신이 나온다.
*/
class Solution {
    public int[] productExceptSelf(int[] nums) {
        int[] ans = new int[nums.length];
        for (int i = 0, temp = 1; i < nums.length; i++) {
            ans[i] = temp;
            temp *= nums[i];
        }
        for (int i = nums.length - 1, temp = 1; i >= 0; i--) {
            ans[i] *= temp;
            temp *= nums[i];
        }
        return ans;
    }
}