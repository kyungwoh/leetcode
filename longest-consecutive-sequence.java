// https://leetcode.com/problems/longest-consecutive-sequence/description/
// Add to the HashSet
// Then find seq
// Time: O(n^2) but avg O(n), Space: O(n)
class Solution {
    public int longestConsecutive(int[] nums) {
        if(nums.length==0) return 0;
        
        HashSet<Integer> set = new HashSet<Integer>();
        for(int n : nums) set.add(n);
        
        int ans = 1;
        for(int s : set){
            if(set.contains(s-1)){
                int i = s-1;
                int len = 2;
                while(set.contains(--i)) len++;
                ans = Math.max(ans, len);
            }
        }
        
        return ans;
    }
}
