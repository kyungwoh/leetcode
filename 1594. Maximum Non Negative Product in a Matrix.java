/*
1594. Maximum Non Negative Product in a Matrix
DP로 풀면 되는데, 양수 음수 바뀌는 것 떄문에 2개를 들고 있어야 한다.
그래서 양수 max, 음수 min (=절대값으로 치면 max) 들고 있으면서 값을 써나간다.
(0,0) (0,j) (i,0) 케이스는 헷갈리니까 별도로 처리하는데, 이것들은 초기값이니까 양수, 음수 상관없이 써놓는다.

Time O(mn) 이건 더 이상 줄일 수 없다.
Space O(mn) 인데 순서대로 해나가면 O(min(m,n))으로 줄일 수 있다. min(m,n) 하려면 길어져서 O(n) 경우만 밑에 썼다.
*/
class Solution {
    public int maxProductPath(int[][] grid) {
        int n = grid.length, m = grid[0].length;
        long[][] p = new long[n][m];
        long[][] q = new long[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                long g = grid[i][j];
                if (i == 0 && j == 0) p[i][j] = q[i][j] = g;
                else if (i == 0) p[i][j] = q[i][j] = p[i][j-1] * g;
                else if (j == 0) p[i][j] = q[i][j] = p[i-1][j] * g;
                else {
                    long p0 = Math.max(p[i][j-1], p[i-1][j]);
                    long q0 = Math.min(q[i][j-1], q[i-1][j]);
                    p[i][j] = (g >= 0 ? p0 : q0) * g;
                    q[i][j] = (g >= 0 ? q0 : p0) * g;
                }
            }
        }
        int ans = (int)(p[n-1][m-1] % 1000000007);
        return ans < 0 ? - 1 : ans;
    }
}
class Solution {
    public int maxProductPath(int[][] grid) {
        int n = grid.length, m = grid[0].length;
        long[][] p = new long[2][m];
        long[][] q = new long[2][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                long g = grid[i][j];
                if (i == 0 && j == 0) p[1][j] = q[1][j] = g;
                else if (i == 0) p[1][j] = q[1][j] = p[1][j-1] * g;
                else if (j == 0) p[1][j] = q[1][j] = p[0][j] * g;
                else {
                    long p0 = Math.max(p[0][j], p[1][j-1]);
                    long q0 = Math.min(q[0][j], q[1][j-1]);
                    p[1][j] = (g >= 0 ? p0 : q0) * g;
                    q[1][j] = (g >= 0 ? q0 : p0) * g;
                }
            }
            p[0] = p[1];
            q[0] = q[1];
        }
        int ans = (int)(p[1][m-1] % 1000000007);
        return ans < 0 ? - 1 : ans;
    }
}