// https://leetcode.com/problems/two-sum/description/
class Solution {
    // Use Hash
    // Time: O(n), Space: O(n)
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer,Integer> set = new HashMap<Integer,Integer>();
        for(int i=0; i<nums.length; i++){            
            int other = target - nums[i];
            if(set.containsKey(other) && set.get(other)!=i){
                return new int[]{set.get(other), i};
            }
            set.put(nums[i],i);
        }
        return null;
    }
    // Brute force
    // Time: O(n^2), Space: O(1)
    public int[] twoSum2(int[] nums, int target) {
        int length = nums.length;
        int[] result = new int[2];
        for(int i=0; i<length; i++) {
        	for(int j=i; j<length; j++) {
        		if(i==j) continue;
        		else {
        			if(nums[i]+nums[j]==target) {
        				result[0]=i;
        				result[1]=j;
        				break;
        			}
        		}
        	}
        }
        return result;
    }
    // Sort, then use two pointers(left, right)
    // Time: O(nlogn) ~O(n):quick sort, Space: O(1)
    public int[] twoSum3(int[] nums, int target) {
        int len = nums.length;
        int[] nums2 = Arrays.copyOf(nums, len);
        Arrays.sort(nums2);
        int[] result = new int[2];
        int l = 0, r = len-1;
        while(l<r){
            if((nums2[l]+nums2[r])==target){
                break;
            }else if((nums2[l]+nums2[r]) > target) r--;
            else l++;
        }
        for(int i=0; i<len; i++){
            if(nums[i]==nums2[l]) result[0]=i;
        }
        for(int i=0; i<len; i++){
            if(i!=result[0] && nums[i]==nums2[r]) result[1]=i;
        }
        return result;
    }
}
