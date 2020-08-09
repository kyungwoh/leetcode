/*
344. Reverse String
s.reverse()
Two pointers O(1) space
DFS of 2. O(n)
*/
class Solution {
    public void reverseString(char[] s) {
        for (int l = 0, r = (s.length - 1); l < r; l++, r--) {
            char temp = s[l];
            s[l] = s[r];
            s[r] = temp;
        }
    }
}
class Solution {
    public void reverseString(char[] s) {
        dfs(s, 0, s.length-1);
    }
    void dfs(char[] s, int l, int r) {
        if (l>=r) return;
        char temp = s[l];
        s[l] = s[r];
        s[r] = temp;
        dfs (s, l+1, r-1);
    }
}