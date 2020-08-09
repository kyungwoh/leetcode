/*
214. Shortest Palindrome
*/

class Solution {
    public String shortestPalindrome(String s) {
        if (s == null) return null;
        int len = s.length();
        
        String r = new StringBuilder(s).reverse().toString();
        for (int i = 0; i < len; i++) {
            if (s.substring(0, len - i).equals(r.substring(i, len))) {
                return r.substring(0, i) + s;
            }
        }
        return "";
    }
}

class Solution {
    public String shortestPalindrome(String s) {
        if (s == null) return null;
        int len = s.length();
        if (len == 0 || len == 1) return s;
        
        String s2 = new StringBuilder(s).reverse().toString();
        int i = len, i2 = 0;
        for (; i >= 2; i--, i2++) {
            if (s.substring(0, i).equals(s2.substring(i2, len))) {
                break;
            }
        }
        return s2.substring(0, i2) + s;
    }
}
