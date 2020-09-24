/*
547. Friend Circles
같은 것들을 묶는데 DFS + visited로 해도 되고, union-find로 해도 된다.
1. DFS: 각 점에서 시작해서 모든 친구들을 다니면서 visited 해놓고, 그 다음 점이 이미 visited면 넘어간다.
2. union-find: 친구일때마다 union해서 합쳐지면 cnt를 줄여나간다.
둘 다 Time O(n^2), Space O(n) * union-find는 find()에서 path compression을 해야 amortized O(1)이 됨
*/
class Solution {
  public int findCircleNum(int[][] M) {
    boolean[] visited = new boolean[M.length];
    int cnt = 0;
    for (int i = 0; i < M.length; i++) {
      if (visited[i]) continue;
      dfs(M, visited, i);
      cnt++;
    }
    return cnt;
  }
  void dfs(int[][] M, boolean[] visited, int i) {
    for (int j = 0; j < M.length; j++) {
      if (M[i][j] == 1 && !visited[j]) {
        visited[j] = true;
        dfs(M, visited, j);
      }
    }
  }
}
class Solution {
  public int findCircleNum(int[][] M) {
    int n = M.length;
    int[] par = new int[n];
    for (int i = 0; i < n; i++) par[i] = i;
    
    int cnt = n;
    for (int i = 0; i < n; i++) {
      for (int j = i+1; j < n; j++) {
        if (M[i][j] == 1) {
          cnt -= union(i, j, par);
        }
      }
    }
    return cnt;
  }
  int find(int i, int[] par) {
    if (par[i] == i) return i;
    return par[i] = find(par[i], par);
  }
  int union(int i, int j, int[] par) {
    int pi = find(i, par);
    int pj = find(j, par);
    
    if (pi == pj) return 0;
    
    if (pi > pj) par[pi] = pj;
    else par[pj] = pi;
    return 1;
  }
}