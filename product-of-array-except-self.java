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
