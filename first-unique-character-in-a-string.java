// https://leetcode.com/problems/first-unique-character-in-a-string/description/
// Two paths
// 1. count & save positions
// 2. get min(positions) when count==1
// Time: O(n), Space: O(n)
class Solution {
    public int firstUniqChar(String s) {
        if(s.length()==0) return -1;
    	int[] atozCount = new int[26];
    	int[] atozLastPos = new int[26];
    	for(int i=0; i<s.length(); i++) {
    		int c = s.charAt(i)-'a';
    		atozCount[c]++;
    		atozLastPos[c] = i;
    	}
    	int result = Integer.MAX_VALUE;
    	for(int i=0; i<26; i++) {
    		if(atozCount[i]==1) result = Math.min(result, atozLastPos[i]);
    	}
    	if(result==Integer.MAX_VALUE) return -1;
    	else return result;
    }
}
