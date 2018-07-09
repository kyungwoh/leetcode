// https://leetcode.com/problems/find-all-numbers-disappeared-in-an-array/description/
class Solution {
    // Naive approach : just use HashSet
    // Time: O(n), Space: O(n)
    public List<Integer> findDisappearedNumbers(int[] nums) {
        HashSet<Integer> set = new HashSet<Integer>();
        for(int i=1; i<=nums.length; i++) set.add(i);
        for(int i : nums) set.remove(i);
        return new ArrayList<Integer>(set);
    }
    // All numbers are positive, so mark their indices as negative
    // Time: O(n), Space: O(1)
    public List<Integer> findDisappearedNumbers2(int[] nums) {
        for(int i=0; i<nums.length; i++){
            int j = Math.abs(nums[i])-1; //Use as an index
            if(nums[j]>0) nums[j] = -nums[j]; //If meets first, mark as negative
        }
        ArrayList<Integer> rList = new ArrayList<Integer>();
        for(int i=0; i<nums.length; i++){
            if(nums[i]>0) rList.add(i+1); //Only add indices not marked as negative
        }
        return rList;
    }

}
