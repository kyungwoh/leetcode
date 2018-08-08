// https://leetcode.com/problems/regular-expression-matching/description/
// Recursively compare string & pattern on each position
// However, '*' can be repeated (0-*) times. So care about that.
// Time: O(3^(len(s)+len(p)), Space: same as time
class Solution {
    public boolean isMatch(String s, String p) {
        if(p.isEmpty()) return s.isEmpty();
    	boolean firstMatch = (!s.isEmpty() && (s.charAt(0)==p.charAt(0) || p.charAt(0)=='.'));
    	if(p.length()>=2 && p.charAt(1)=='*') {
    		return isMatch(s, p.substring(2)) || (firstMatch && isMatch(s.substring(1), p));
    	}else {
    		return firstMatch && isMatch(s.substring(1), p.substring(1));
    	}
    }
}
