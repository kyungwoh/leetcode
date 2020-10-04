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

// https://leetcode.com/problems/product-of-array-except-self/description/
class Solution {
    // Without division (With only multiplication)
    // Use rangeProduct (1, 1*2, 1*2*3, 1*2*3*4) left and right (4, 4*3, 4*3*2, 4*3*2*1)
    // Time: O(n), Space: O(1) w/o output
    public int[] productExceptSelf(int[] nums) {
        int[] ans = new int[nums.length];
        ans[0] = 1;
        int leftAll = 1;
        for(int i=1; i<nums.length; i++){
            leftAll *= nums[i-1];
            ans[i] = leftAll;
        }
        int rightAll = 1;
        for(int i=(nums.length-2); i>=0; i--){
            rightAll *= nums[i+1];
            ans[i] *= rightAll;
        }
        return ans;
    }
    // With division
    // Just multiply all & handle zero cases
    // Time: O(n), Space: O(1) w/o output
    public int[] productExceptSelf2(int[] nums) {
        long all = 1;
        int zeroCnt = 0, zeroI = -1;
        for(int i=0; i<nums.length; i++){
            if(nums[i]!=0) all *= nums[i];
            else{
                zeroCnt++;
                zeroI = i;
            }
        }
        for(int i=0; i<nums.length; i++){
            if(zeroCnt > 1) nums[i] = 0;
            else if(zeroCnt==1){
                if(i!=zeroI) nums[i] = 0;
                else nums[i] = (int)all;
            }else nums[i] = (int)(all/nums[i]);
        }
        return nums;
    }
}
