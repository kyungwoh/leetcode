// https://leetcode.com/problems/wiggle-sort-ii/description/
// Use Virtual Indexing (wiggle indexing)
// Pivot = median, then swap
// Time: O(n), Space: O(1) w/o output
class Solution {
    int len;
    public void wiggleSort(int[] nums) {
        len = nums.length;
        if(len<2) return;
        //System.out.println(len|1);
        int median = findKthLargest(nums, (len+1)/2);
        int l=0, r=len-1;
        for(int i=0; i<=r;){
            if(nums[wig(i)] > median){
                swap(nums, wig(l), wig(i));
                l++;
                i++;
            }else if(nums[wig(i)] < median){
                swap(nums, wig(r), wig(i));
                r--;
            }else{
                i++;
            }
        }
    }
    private int wig(int i){ return (1+2*i)%(len|1); }
    private void swap(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
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
}
