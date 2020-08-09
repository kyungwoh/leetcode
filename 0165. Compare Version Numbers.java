/* 165. Compare Version Numbers
10진수만 나오니까 j = j*10 + i 더해나가면 된다.
둘 다 끝까지 갔는데도 같으면 정말 같은 거다.
혹시 짧은게 먼저라던가 하는 규칙이 있으면 감안하면 된다.
*/
class Solution {
    public int compareVersion(String v1, String v2) {
        int i1 = 0, i2 = 0;
        while (true) {
            int j1 = 0, j2 = 0;
            while (i1 < v1.length()) {
                char c = v1.charAt(i1++);
                if (c == '.') break;
                j1 = j1*10 + (int)(c - '0');
            }
            while (i2 < v2.length()) {
                char c = v2.charAt(i2++);
                if (c == '.') break;
                j2 = j2*10 + (int)(c - '0');
            }
            if (j1 > j2) return 1;
            if (j1 < j2) return -1;
            if (i1 == v1.length() && i2 == v2.length()) return 0;
        }
    }
}