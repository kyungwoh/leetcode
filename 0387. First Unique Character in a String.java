/*
387. First Unique Character in a String
일단 스트링이니 다 돌아야 해서 time O(n)은 피할 수 없고, 근데 캐릭터를 세는 거면 space O(1)으로 가능하다. Just lowercase면 26, ASCII면 128, 유니코드면 Map<Character, Integer> 해야 하지만 그래도 2^16=65536, 크긴 해도 O(1) 이다. Two pass로 처음에는 캐릭터 갯수를 세고, 그 다음번에는 count == 1 인데서 리턴.
*/
class Solution {
    public int firstUniqChar(String s) {
        int[] cnts = new int[26];
        for (char c : s.toCharArray()) cnts[c - 'a']++;
        for (int i = 0; i < s.length(); i++) {
            if (cnts[s.charAt(i) - 'a'] == 1) return i;
        }
        return -1;
    }
}