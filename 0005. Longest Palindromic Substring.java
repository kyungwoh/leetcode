/*
5. Longest Palindromic Substring
expand around center로 하면 time O(n^2), space O(1)에 풀 수 있다. 짝수, 홀수 경우를 다 돌아야 한다.
*/
class Solution {
    public String longestPalindrome(String s) {
        int max = 0;
        String ans = "";
        
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; i-j >= 0 && i+j < s.length(); j++) {
                if (s.charAt(i-j) != s.charAt(i+j)) break;
                if (max < j * 2 + 1) {
                    max = j *2 + 1;
                    ans = s.substring(i-j, i+j+1);
                }
            }
        }
        
        for (int i = 0; i < s.length(); i++) {
            for (int j = 1; i-j >= 0 && i+j-1 < s.length(); j++) {
                if (s.charAt(i-j) != s.charAt(i+j-1)) break;
                if (max < j * 2) {
                    max = j *2;
                    ans = s.substring(i-j, i+j);
                }
            }
        }
        
        return ans;
    }
}