/*
340. Longest Substring with At Most K Distinct Characters

l,r two pointers로 풀면 된다. (r은 한칸씩 전진, l은 while문으로 필요한만큼 전진)
문제는 k를 어떻게 세느냔데, cnts가 0보다 크냐 작으냐로 판단하면 굳이 cnts를 다 돌 필요가 없다.

증가하는 시점에 0이었으면 그 캐릭터가 처음 나타난 곳이니 k++하고,
감소한 다음 시점에 0이면 그 캐릭터가 모두 없어진 곳이니 k--하면 된다.
(증가하기 전에 세고, 감소한 다음 센다. 순서 헷갈림)
*/
class Solution {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        int[] cnts = new int[128];
        int maxLen = 0;
        for (int l = 0, r = 0; r < s.length();) {
            if (cnts[s.charAt(r++)]++ == 0) k--;
            while (k < 0) if (--cnts[s.charAt(l++)] == 0) k++;
            if ((r-l) > maxLen) maxLen = r - l;
        }
        return maxLen;
    }
}

class Solution {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        Map<Character, Integer> cnts = new HashMap<>();
        int maxLen = 0; char c;
        for (int l = 0, r = 0, cnt = 0; r < s.length();) {
            c = s.charAt(r++);
            cnt = cnts.getOrDefault(c, 0);
            if (cnt++ == 0) k--;
            cnts.put(c, cnt);
            
            while (k < 0) {
                c = s.charAt(l++);
                cnt = cnts.getOrDefault(c, 0);
                if (--cnt == 0) k++;
                cnts.put(c, cnt);
            }
            
            if ((r-l) > maxLen) maxLen = r - l;
        }
        return maxLen;
    }
}