// https://leetcode.com/problems/island-perimeter/description/
class Solution {
    // DFS: find the first node, then start dfs search
    // Time: O(mn), Space: O(mn) <-- prevent re-visit
    int ans = 0;
    public int islandPerimeter(int[][] grid) {
        int l1 = grid.length;
        if(l1==0) return 0;
        int l2 = grid[0].length;
        if(l2==0) return 0;
        boolean isVisited[][] = new boolean[l1][l2];
        for(int i=0; i<l1; i++){
            for(int j=0; j<l2; j++){
                if(grid[i][j]==1){
                    dfs(grid, isVisited, i, j, l1, l2);
                    break;
                }
            }
        }
        return ans;
    }
    private void dfs(int[][] grid, boolean[][] isVisited, int i , int j, int l1, int l2){
        if(isVisited[i][j]) return;
        else{
            isVisited[i][j] = true;
            ans += 4;
            if(i>0 && grid[i-1][j]==1){ ans--; dfs(grid, isVisited, i-1, j, l1, l2); }
            if(j>0 && grid[i][j-1]==1){ ans--; dfs(grid, isVisited, i, j-1, l1, l2); }
            if(i<(l1-1) && grid[i+1][j]==1){ ans--; dfs(grid, isVisited, i+1, j, l1, l2); }
            if(j<(l2-1) && grid[i][j+1]==1){ ans--; dfs(grid, isVisited, i, j+1, l1, l2); }
        }
    }
    // Naive approach: search all nodes
    // Time: O(mn), Space: O(1)
    public int islandPerimeter2(int[][] grid) {
        int l1 = grid.length;
        if(l1==0) return 0;
        int l2 = grid[0].length;
        if(l2==0) return 0;
        int ans = 0;
        for(int i=0; i<l1; i++){
            for(int j=0; j<l2; j++){
                if(grid[i][j]==1){
                    ans += 4;
                    if(i>0 && grid[i-1][j]==1) ans--;
                    if(j>0 && grid[i][j-1]==1) ans--;
                    if(i<(l1-1) && grid[i+1][j]==1) ans--;
                    if(j<(l2-1) && grid[i][j+1]==1) ans--;
                }
            }
        }
        return ans;
    }
}
