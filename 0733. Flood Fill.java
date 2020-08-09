/*
733. Flood Fill
4방향으로 DFS or BFS 하면서 뻗어나간다. visitied 체크하고, oldc 일 경우만 newc로 바꾼다.
*/
class Solution {
    boolean[][] visited;
    int oldc, newc;
    int[][] image;
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        if (image.length == 0 || image[0].length == 0) return null;
        visited = new boolean[image.length][image[0].length];
        oldc = image[sr][sc];
        newc = newColor;
        this.image = image;
        dfs(sr, sc);
        return image;
    }
    void dfs(int i, int j) {
        if (i < 0 || i >= image.length || j < 0 || j >= image[i].length) return;
        if (visited[i][j]) return;
        visited[i][j] = true;
        if (image[i][j] != oldc) return;
        image[i][j] = newc;
        dfs(i+1, j);
        dfs(i, j+1);
        dfs(i-1, j);
        dfs(i, j-1);
    }
}
class Solution {
    boolean[][] visited;
    int oldc, newc;
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        if (image.length == 0 || image[0].length == 0) return null;
        visited = new boolean[image.length][image[0].length];
        oldc = image[sr][sc];
        newc = newColor;
        
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{sr, sc});
        while(!q.isEmpty()) {
            int[] p = q.poll();
            int i = p[0], j = p[1];
            
            if (visited[i][j]) continue;
            visited[i][j] = true;
            
            if (image[i][j] != oldc) continue;
            image[i][j] = newc;
            
            if (i > 0) q.add(new int[]{i-1, j});
            if (j > 0) q.add(new int[]{i, j-1});
            if (i < image.length - 1) q.add(new int[]{i+1, j});
            if (j < image[i].length - 1) q.add(new int[]{i, j+1});
        }
        
        return image;
    }
}