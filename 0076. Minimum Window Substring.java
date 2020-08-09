// 76. Minimum Window Substring
class Solution {
    public String minWindow(String s, String t) {
        int[] cnt = new int[128];
        for (char c : t.toCharArray()) cnt[c]++;
        
        int min = s.length() + 1;
        int minl = 0;
        int rem = t.length();
        for (int l = 0, r = 0; r < s.length(); r++) {
            if (--cnt[s.charAt(r)] >= 0) rem--;
            while (l <= r && rem == 0) {
                if (r - l + 1 < min) {
                    min = r -l + 1;
                    minl = l;
                }
                if (++cnt[s.charAt(l++)] > 0) rem++;
            }
        }
        return min == s.length() + 1 ? "" : s.substring(minl, minl + min);
    }
}