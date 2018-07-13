// https://leetcode.com/problems/move-zeroes/description/
class Solution {
    // Use slow pointer(j)
    // Time: O(n), Space: O(1)
    public void moveZeroes(int[] nums) {
        int l = nums.length;
        for(int i=0, j=0; i<l; i++){
            if(nums[i]!=0) swap(nums, j++, i);
        }
    }
    // Bubble Sort
    // Time: O(n^2), Space: O(1)
    public void moveZeroes2(int[] nums) {
        int l = nums.length;
        for(int i=0; i<l; i++){
            for(int j=i+1; j<l; j++){
                if(nums[i]==0 && nums[j]!=0) swap(nums, i, j);
            }
        }
    }
    private void swap(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
