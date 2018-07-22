// https://leetcode.com/problems/search-in-rotated-sorted-array/description/
// Modified Binary Search : find the ascending part, then decide low or high
// Time: O(logn), Space: O(1)
class Solution {
    public int search(int[] nums, int target) {
        int l=0, h=nums.length-1;
        while(l<=h){
            if(nums[l]==target) return l;
            else if(nums[h]==target) return h;
            else{
                int m = (l+h)/2;
                //System.out.println(l+" "+m+" "+h);
                if(nums[m]==target) return m;
                else if(nums[l]<nums[m]){
                    if(nums[l]<target && target<nums[m]) h=m-1;
                    else l=m+1;
                }else{
                    if(nums[m]<target && target<nums[h]) l=m+1;
                    else h=m-1;
                }
            }
        }
        return -1;
    }
}
