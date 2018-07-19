// https://leetcode.com/problems/single-number/description/
class Solution {
    // Use XOR
    // a XOR a = 0 (same number)
    // Time: O(n), Space: O(1)
    public int singleNumber(int[] nums) {
        int ans = 0;
        for(int n : nums) ans ^= n;
        return ans;
    }
    
    // Use a Set
    // Time: O(n), Space: O(n)
    public int singleNumber2(int[] nums) {
        HashSet<Integer> set = new HashSet<Integer>();
        for(int n : nums){
            if(!set.contains(n)){
                set.add(n);
            }else{
                set.remove(n);
            }
        }
        int ans = 0;
        for(int i : set) ans = i;
        return ans;
    }
}
