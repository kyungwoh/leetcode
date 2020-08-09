/*
242. Valid Anagram

*/
class Solution {
    public boolean isAnagram(String s, String t) {
        int[] cnts = new int[26];
        int cnt = 0;
        for (char c : s.toCharArray()) {
            int i = (int)(c - 'a');
            if (cnts[i] == 0) cnt++;
            cnts[i]++;
        }
        for (char c : t.toCharArray()) {
            int i = (int)(c - 'a');
            cnts[i]--;
            if (cnts[i] == 0) cnt--;
            if (cnts[i] < 0) return false;
        }
        return cnt == 0;
    }
}