/*
3. Longest Substring Without Repeating Characters
반복하지 않는 것이므로 Set에 넣어가면서 중복되는지 확인하면 된다.
중복된다면 빼서 왼쪽을 빼면서 한 칸 전진을 계속.
그러면 two pointers니까 O(n)은 되는데, 더 빠르게 가능하다.

Map에다 기존 index를 넣어놓으면, 왼쪽을 한 칸씩 줄일 필요 없이 바로 점프할 수 있다.
기존에 값이 없었다면 현재 오른쪽에 도달하기까지 점프하면 된다.

캐릭터를 a-z(26), ASCII(128), Extended ASCII(256)으로 제한하면 array로 풀 수도 있다.
처음 array에 값이 0으로 세팅되는데, 이것을 -1로 Arrays.fill()한 다음에 과거 인덱스 +1 할 수도 있고, 매번 과거 인덱스에 +1을 넣어서 써도 된다.
*/
class Solution {
    public int lengthOfLongestSubstring(String s) {
        int max = 0;
        int[] last = new int[128];
        for (int l = 0, r = 0; r < s.length(); r++) {
            int i = s.charAt(r);
            l = Math.max(l, last[i]);
            max = Math.max(max, r - l + 1);
            last[i] = r + 1;
        }
        return max;
    }
}
class Solution {
    public int lengthOfLongestSubstring(String s) {
        int max = 0;
        Set<Character> set = new HashSet<>();
        for (int l = 0, r = 0; l <= r && r < s.length();) {
            if (set.contains(s.charAt(r))) {
                set.remove(s.charAt(l++));
            } else {
                set.add(s.charAt(r++));
                max = Math.max(max, r - l);
            }
        }
        return max;
    }
}