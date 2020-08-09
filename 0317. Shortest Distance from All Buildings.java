/*
317. Shortest Distance from All Buildings
shortest path니까 BFS or Dijkstra인데, length==1 이니까 BFS로 푼다.

문제는 한 grid 안에서 시작점, 끝점이 다른 multiple shortest paths를 찾아야 하는 건데,
이걸 한번에 합쳐서 풀 수 없다. 각각 풀어서 합쳐야 한다.

1. 시작점은 알지만 끝점을 모른다. 그래서 일단 빌딩을 만나면, 모든 점까지의 최소 lens를 다 구한다.
   이걸 겹쳐서 쓸 거기 때문에, lens에 더해놓는다.
2. lens에 더할 때, cnts[][]도 ++한다. 그러면 각 점에 대해 몇 개의 빌딩이나 지나쳤는지를 알 수 있다.
   예를 들어 빌딩이 3개인데 2개는 서로 도달 가능하지만 1개는 고립되어 있다면 -1을 리턴해야 하는데,
   이렇게 cnts를 구해놓으면 2인 점들, 1인 점들밖에 안 나오니 3인 점이 없어서 -1을 리턴할 수 있다.
3. 다 돈 다음에, 각 점마다 돌면서 빌딩이 아닌 점 중에 cnts==빌딩 갯수인 곳에서의 min(len)을 구한다.
*/
class Solution {
    int iLen, jLen;
    int[][] grid, lens, cnts;
    int[][] dirs = new int[][]{{-1,0},{0,-1},{1,0},{0,1}};
    public int shortestDistance(int[][] grid) {
        this.grid = grid;
        
        iLen = grid.length;
        if (iLen == 0) return -1;
        jLen = grid[0].length;
        if (jLen == 0) return -1;
        
        lens = new int[iLen][jLen];
        cnts = new int[iLen][jLen];
        int cnt = 0;
        for (int i = 0; i < iLen; i++) {
            for (int j = 0; j < jLen; j++) {
                if (grid[i][j] == 1) {
                    cnt++;
                    addLen(new Node(i, j));
                }
            }
        }
        
        int minLen = Integer.MAX_VALUE;
        for (int i = 0; i < iLen; i++) {
            for (int j = 0; j < jLen; j++) {
                if (grid[i][j] == 0 && cnts[i][j] == cnt) {
                    minLen = Math.min(minLen, lens[i][j]);
                }
            }
        }
        
        return minLen == Integer.MAX_VALUE ? -1 : minLen;
    }
    void addLen(Node n) {
        boolean[][] visited = new boolean[iLen][jLen];
        Queue<Node> q = new LinkedList<>();
        q.offer(n);
        for (int i = 1; q.size() > 0; i++) {
            int size = q.size();
            for (int j = 0; j < size; j++) {
                Node m = q.poll();
                for (int k = 0; k < 4; k++) {
                    int i2 = m.i + dirs[k][0];
                    int j2 = m.j + dirs[k][1];
                    if (i2 < 0 || j2 < 0 || i2 == iLen || j2 == jLen) continue;
                    if (grid[i2][j2] != 0) continue;
                    if (visited[i2][j2]) continue;
                    visited[i2][j2] = true;
                    lens[i2][j2] += i;
                    cnts[i2][j2]++;
                    q.offer(new Node(i2, j2));
                }
            }
        }
    }
}
class Node {
    int i, j;
    Node(int i, int j) {
        this.i = i;
        this.j = j;
    }
}
