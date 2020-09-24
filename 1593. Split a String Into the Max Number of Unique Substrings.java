/*
1593. Split a String Into the Max Number of Unique Substrings
그냥 recursive하게 다 해보면 Time O(2^n), Space O(n)
memo를 할 수도 없는게, 더 나은 경우가 나중에 나올 수도 있기 때문이다.

아니면 iterative하게 할 수 있다. bitmap으로 string을 나누는 fence를 친다고 생각하자.
그러면 여전히 경우의 수가 2^(n-1)이 되니까 Time O(2^n)인건 같지만,
fence가 작은 것부터 큰 것으로 가니까, fence 갯수가 같은 것들은 prune할 수 있어서 빨라진다.
그 다음에는 fence가 1인 위치들에서 string을 잘라서 set에 넣어가면서 unique한지를 찾는다.
*/
class Solution {
    public int maxUniqueSplit(String s) {
        int maxi = 1;
        int n = s.length();
        for (int fences = 0; fences < 1 << n - 1; fences++) {
            if (Integer.bitCount(fences) < maxi) continue;
            Set<String> set = new HashSet<>();
            StringBuilder sb = new StringBuilder();
            sb.append(s.charAt(0));
            boolean unique = true;
            for (int bit = 0; bit < n - 1; bit++) {
                if ((fences >> bit & 1) > 0) {
                    if (set.contains(sb.toString())) {
                        unique = false;
                        break;
                    }
                    set.add(sb.toString());
                    sb.setLength(0);
                }
                sb.append(s.charAt(bit + 1));
            }
            if (set.contains(sb.toString())) continue;
            set.add(sb.toString());
            if (unique) maxi = Math.max(maxi, set.size());
        }
        return maxi;
    }
}
class Solution {
    public int maxUniqueSplit(String s) {
        return helper(0, s, new HashSet<>());
    }
    int helper(int i, String s, Set<String> set) {
        if (i == s.length()) return set.size();
        
        int maxCnt = 0;
        for (int j = i+1; j <= s.length(); j++) {
            String t = s.substring(i, j);
            if (!set.contains(t)) {
                set.add(t);
                maxCnt = Math.max(maxCnt, helper(j, s, set));
                set.remove(t);
            }
        }
        return maxCnt;
    }
}
class Solution {
    int maxCnt;
    Set<String> set;
    public int maxUniqueSplit(String s) {
        maxCnt = 1;
        set = new HashSet<>();
        helper(0, 0, s);
        return maxCnt;
    }
    void helper(int i, int cnt, String s) {
        if (i == s.length()) {
            maxCnt = Math.max(maxCnt, cnt);
            return;
        }
        for (int j = i+1; j <= s.length(); j++) {
            String t = s.substring(i, j);
            if (!set.contains(t)) {
                set.add(t);
                helper(j, cnt+1, s);
                set.remove(t);
            }
        }
    }
}