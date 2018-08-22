// https://leetcode.com/problems/minimum-path-sum/description/
// DP (with in-place)
// Time: O(nm), Space: O(nm) (without in-place)
public class Solution {
    public int MinPathSum(int[,] grid) {
        for(int i=0; i<grid.GetLength(0); i++){
            for(int j=0; j<grid.GetLength(1); j++){
                int cur=grid[i,j];
                if(i>0 && j>0) cur += Math.Min(grid[i-1,j], grid[i,j-1]);
                else if(i>0) cur += grid[i-1,j];
                else if(j>0) cur += grid[i,j-1];
                grid[i,j]=cur;
            }
        }
        return grid[grid.GetLength(0)-1,grid.GetLength(1)-1];
    }
}
