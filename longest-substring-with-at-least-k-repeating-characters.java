// https://leetcode.com/problems/longest-substring-with-at-least-k-repeating-characters/description/
// Count & Bit-masking
// Time: O(n^2), Space: O(1)
class Solution {
    public int longestSubstring(String s, int k) {
        int maxLength = 0;
    	for(int i=0, maxJ=0; i<=s.length()-k; i=++maxJ) {
    		int atozCount[] = new int[26];
    		int atozMask = 0;
    		for(int j=i; j<s.length(); j++) {
    			int index = s.charAt(j) - 'a';
    			int count = ++atozCount[index];
    			if(count<k) atozMask |= 1<<index;
    			else atozMask &= ~(1<<index);
    			
    			if(atozMask==0) {
    				maxLength = Math.max(maxLength, j-i+1);
    				maxJ = j;
    			}
    		}
    	}
        return maxLength;
    }
}
