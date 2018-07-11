// https://leetcode.com/problems/game-of-life/description/
// 1 -> 2
// 0 -> -1
// Time: O(n), Space: N/A (mark on the input array)
class Solution {
    public void gameOfLife(int[][] board) {
        int m = board.length;
        if(m==0) return;
        int n = board[0].length;
        if(n==0) return;
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                int cnt = 0;
                if(i>0 && j>0 && board[i-1][j-1]>0) cnt++;
                if(i>0 && board[i-1][j]>0) cnt++;
                if(i>0 && j<(n-1) && board[i-1][j+1]>0) cnt++;
                if(j>0 && board[i][j-1]>0) cnt++;
                if(j<(n-1) && board[i][j+1]>0) cnt++;
                if(i<(m-1) && j>0 && board[i+1][j-1]>0) cnt++;
                if(i<(m-1) && board[i+1][j]>0) cnt++;
                if(i<(m-1) && j<(n-1) && board[i+1][j+1]>0) cnt++;
                
                if(board[i][j]>0 && (cnt<2 || cnt>3)) board[i][j]=2;
                else if(board[i][j]<1 && cnt==3) board[i][j]=-1;
            }
        }
        //System.out.println(Arrays.deepToString(board));
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){                
                if(board[i][j]==2) board[i][j]=0;
                else if(board[i][j]==-1) board[i][j]=1;
            }
        }
    }
}
