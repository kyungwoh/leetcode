// https://leetcode.com/problems/longest-continuous-increasing-subsequence/description/
// Time: O(n), Space: O(1)
public class Solution {
    public int FindLengthOfLCIS(int[] nums) {
        if(nums.Length>0){
            int maxLen = 1, len = 1, last = nums[0];
            for(int i=1; i<nums.Length; i++){
                if(last<nums[i]) len++;
                else len = 1;
                
                maxLen = Math.Max(maxLen, len);
                last = nums[i];
            }
            return maxLen;
        }else return 0;
    }
}
