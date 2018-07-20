// https://leetcode.com/problems/kth-largest-element-in-an-array/description/
// Quick select : pivot + divide & conquer
// Time: O(n^2) worst case, but O(nlogn) avg. Space: the same as time
class Solution {
    public int findKthLargest(int[] nums, int k){
        if(nums==null || nums.length==0) return -1;
        return findKthLargest(nums, 0, nums.length-1, nums.length-k);
    }
    private int findKthLargest(int[] nums, int s, int e, int k){
        if(s>e) return -1;
        
        int pivot = nums[e];
        int left = s;
        for(int i=s; i<e; i++){
            if(nums[i]<=pivot) swap(nums, left++, i);
        }
        swap(nums, left, e);
        
        if(left==k) return nums[left];
        else if(left<k) return findKthLargest(nums, left+1, e, k);
        else return findKthLargest(nums, s, left-1, k);
    }
    private void swap(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
