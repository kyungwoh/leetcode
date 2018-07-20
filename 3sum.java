// https://leetcode.com/problems/3sum/description/
// 1. Sort first
// 2. Fix 1 pointer O(n), then use 2 pointers (left, right) O(n)
// 3. skips when duplicates
// Time: O(n^2), Space: O(n^3)
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        int len = nums.length;
        List<List<Integer>> rList = new ArrayList<List<Integer>>();
        Arrays.sort(nums);
        for(int i=0; i<(len-2);i++){
            if(i==0 || (nums[i]!=nums[i-1])){
                int l = i+1, r = len-1, sum = 0 - nums[i];
                while(l<r){
                    if(nums[l]+nums[r] == sum){
                        rList.add(Arrays.asList(nums[i],nums[l],nums[r]));
                        while(l<r && nums[l]==nums[l+1]) l++;
                        while(l<r && nums[r]==nums[r-1]) r--;
                        l++;
                        r--;
                    }else if(nums[l]+nums[r] < sum ) l++;
                    else r--;
                }
            }
        }
        return rList;
    }
}
