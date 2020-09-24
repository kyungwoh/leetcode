/*
44. Wildcard Matching
재귀적으로 풀 때 문제가 되는게 그냥 true, false만 찾는게 아니라 memo를 하던가 pruning을 해야 빠르다. 그래서 case를 분리해서
0. 확실히 false인 것
1. false이지만 더 해봐야 하는 것
2. 확실히 true인 것
이렇게 구분해서, 0이나 2이면 거기서 바로 return하고, 1이면 더 가보는 것으로 하자.
그래서 마지막에 0,1번은 false, 2번은 true로 리턴.

'*'는 한칸씩 전진하며 재귀하는 대신에, for문으로 모든 경우를 쭉 돌아보도록 한다.
*/
class Solution {
    public boolean isMatch(String s, String p) {
        return dfs(s, p, 0, 0) > 1;
    }
    int dfs(String s, String p, int si, int pi) {
        int sLen = s.length(), pLen = p.length();
        if (si == sLen && pi == pLen) return 2; // matched
        if (si == sLen && p.charAt(pi) != '*') return 0; // unmatched with all s
        if (pi == pLen) return 1; // unmatched without all s
        
        if (p.charAt(pi) == '*') {
            for (int i = 0; i <= sLen - si; i++){
                int r = dfs(s, p, si+i, pi+1);
                if (r == 0 || r == 2) return r;
            }
        }
        if (p.charAt(pi) == '?' || s.charAt(si) == p.charAt(pi)) return dfs(s,p,si+1,pi+1);
        return 1;
    }
}
class Solution {
    public boolean isMatch(String s, String p) {
        int pLen = p.length(), sLen = s.length();
        boolean[][] dp = new boolean[pLen+1][sLen+1];
        dp[0][0] = true;
        for (int pi = 1; pi <= pLen; pi++) {
            char pc = p.charAt(pi-1);
            if (pc == '*') {
                int si = 1;
                while (!dp[pi-1][si-1] && si <= sLen) si++;
                dp[pi][si-1] = dp[pi-1][si-1];
                while (si <= sLen) dp[pi][si++] = true;
            } else if (pc == '?') {
                for (int si = 1; si <= sLen; si++) {
                    dp[pi][si] = dp[pi-1][si-1];
                }
            } else {
                for (int si = 1; si <= sLen; si++) {
                    dp[pi][si] = dp[pi-1][si-1] && pc == s.charAt(si-1);
                }
            }
        }
        return dp[pLen][sLen];
    }
}