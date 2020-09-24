/*
279. Perfect Squares
모든 경우를 다 해보면 느리니까 DP로 푼다.
1. bottom-up: 각 i 마다 dp[i] = min(dp[i], dp[i - 제곱]) 로 덮어쓴다. Time O(n * sqrt(n)), Space O(n)
2. top-down: 각 cnt 마다 cnt = cnt(n - 제곱) + 1, 제곱 cnt = 1 로 내려가서 되는게 있는지 찾는다.
             call stack depth가 sqrt(n) 라서 Time O(n * sqrt(n) * sqrt(n)) = O(n^2) 로 1번보다 느리긴 한데,
             cnt가 작을때는 early return 해서 빠른 경우도 많다.
             Space O(sqrt(n) + sqrt(n)) = O(sqrt(n)) 로 1번보다 적게 쓴다.
*/
class Solution {
    public int numSquares(int n) {
        int[] dp = new int[n+1];
        for (int i = 0; i <= n; i++) dp[i] = i;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j*j <= i; j++) {
                dp[i] = Math.min(dp[i], dp[i - j*j] + 1);
            }
        }
        return dp[n];
    }
}
class Solution {
    public int numSquares(int n) {
        Set<Integer> set = new HashSet<>();
        for (int i = 1; i * i <= n; i++) set.add(i * i);
        
        int cnt = 1;
        for (; cnt <= n; cnt++) {
            if (isSquares(n, cnt, set)) break;
        }
        return cnt;
    }
    boolean isSquares(int n, int cnt, Set<Integer> set) {
        if (cnt == 1) return set.contains(n);
        for (int s : set) {
            if (n > s && isSquares(n - s, cnt - 1, set)) return true;
        }
        return false;
    }
}