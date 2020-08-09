/*
909. Snakes and Ladders
한 스텝씩 가는 거면 BFS + Q를 쓰고, 여러 스텝씩 가는 거면 Dijkstra + PQ를 쓴다. shortest path는 DP로 풀면 안된다. visited 써야 한다.
arr는 square인지 rectangle인지 꼭 물어봐야 한다. len==0인 경우가 있는지도 물어보고 (넘어갈 확률이 많음)
i를 뒤집을 땐 iLen - 1 - i, j를 뒤집을 땐 jLen - 1 - j 다.
2D -> 1D로 바꿀 땐 return i * jLen + j;
1D -> 2D로 바꿀 땐 i = k / jLen; j = k % jLen;
index = 1 부터 시작하면 일단 그대로 쓰고, arr 찾을 때만 -1 해준다. 안 그러면 헷갈린다.
*/

class Solution {
    int[][] board;
    int len;
    public int snakesAndLadders(int[][] board) {
        this.len = board.length;
        this.board = board;
        
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(1, 0));
        
        boolean[] visited = new boolean[len*len + 1];
        
        while(!q.isEmpty()) {
            Pair p = q.poll();
            
            for (int j = p.i + 1; j <= p.i + 6 && j <= len*len; j++) {
                int next = get(j);
                if (next == len*len) return p.step + 1;
                
                if (visited[next]) continue;
                visited[next] = true;
                
                q.add(new Pair(next, p.step + 1));
            }
        }
        return -1;
    }
    private int get(int k) {
        int i = (k - 1) / len;
        int j = (k - 1) % len;
        if (i % 2 == 1) j = len - 1 - j;
        i = len - 1 - i;
        return board[i][j] == -1 ? k : board[i][j];
    }
}
class Pair{
    int i, step;
    Pair(int i, int step) {
        this.i = i;
        this.step = step;
    }
}

class Solution {
    int[][] board;
    int len;
    public int snakesAndLadders(int[][] board) {
        this.len = board.length;
        this.board = board;
        
        Queue<Integer> q = new LinkedList<>();
        q.add(1);
        boolean[] visited = new boolean[len*len + 1];
        
        int step = 1;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int qi = 0; qi < size; qi++) {
                int i = q.poll();

                for (int j = i + 1; j <= i + 6 && j <= len*len; j++) {
                    int next = get(j);
                    if (next == len*len) return step;

                    if (visited[next]) continue;
                    visited[next] = true;

                    q.add(next);
                }
            }
            step++;
        }
        return -1;
    }
    private int get(int k) {
        int i = (k - 1) / len;
        int j = (k - 1) % len;
        if (i % 2 == 1) j = len - 1 - j;
        i = len - 1 - i;
        return board[i][j] == -1 ? k : board[i][j];
    }
}

class Solution {
    int[][] board;
    int len;
    public int snakesAndLadders(int[][] board) {
        this.len = board.length;
        this.board = board;
        
        Queue<Integer> q = new LinkedList<>();
        q.add(0);
        boolean[] visited = new boolean[len*len];
        
        int step = 1;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int qi = 0; qi < size; qi++) {
                int i = q.poll();

                for (int j = i + 1; j <= i + 6 && j < len*len; j++) {
                    int next = get(j);
                    if (next == len*len - 1) return step;

                    if (visited[next]) continue;
                    visited[next] = true;

                    q.add(next);
                }
            }
            step++;
        }
        return -1;
    }
    private int get(int k) {
        int i = k / len;
        int j = k % len;
        if (i % 2 == 1) j = len - 1 - j;
        i = len - 1 - i;
        return board[i][j] == -1 ? k : board[i][j] - 1;
    }
}