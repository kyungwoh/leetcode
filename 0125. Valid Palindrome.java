/*
125. Valid Palindrome
leftmost, rightmost에서부터 시작해서 two pointers로 가운데로 접근해간다.
각각 while문으로 letter or digit [a-z0-9]을 만날때까지 접근하고, 그 다음에 비교한다.
이때 l < r 조건을 항상 넣어서 교차하지 않도록 한다.

while문 대신 if문으로 할 수도 있지만, while문이 더 간단하다. two pointers는 대체로 그렇다.
*/

class Solution {
    public boolean isPalindrome(String s) {
        for (int l = 0, r = s.length() - 1; l < r; l++, r--) {
            while (l < r && !Character.isLetterOrDigit(s.charAt(l))) l++;
            while (l < r && !Character.isLetterOrDigit(s.charAt(r))) r--;
            if (l < r && Character.toLowerCase(s.charAt(l)) != Character.toLowerCase(s.charAt(r))) return false;
        }
        return true;
    }
}

class Solution {
    public boolean isPalindrome(String s) {
        for (int l = 0, r = s.length() - 1; l < r;) {
            char c1 = Character.toLowerCase(s.charAt(l));
            if (!(c1 >= 'a' && c1 <= 'z') && !(c1 >= '0' && c1 <= '9')) { l++; continue; }
            
            char c2 = Character.toLowerCase(s.charAt(r));
            if (!(c2 >= 'a' && c2 <= 'z') && !(c2 >= '0' && c2 <= '9')) { r--; continue; }
            
            if (c1 != c2) return false;
            l++;
            r--;
        }
        return true;
    }
}

