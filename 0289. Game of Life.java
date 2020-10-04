/*
289. Game of Life
겹치지 않게 in-place로 풀 수 있을까? boolean이 아니라 int인 것이 hint.
값을 바꾸되 계산에 영향을 미치지 않게 바꿔놓고, 다 돈 후에 표시한 걸 바꾸면 된다. two-pass.

그럼 좌표가 무한에 가깝다면? 배열이 아니라 (i,j)로 live인 것만 주어질수도 있다. 489. Robot Room Cleaner 방식으로.
그러면 live인 것들을 돌면서 죽는지 체크하고, 그러면서 live 주변의 8칸에 cnt를 더해서 dead가 살아나는지 체크한다.

메모리가 아니라 파일에서 읽어와야 한다면, 한 번에 3x3 만 읽으면 되니까 그만큼씩 buffer에 읽어서 처리하면 된다.
sequential rows로 저장되어있다면 3 rows씩 읽으면서 처리하면 된다.
*/
class Solution {
    public void gameOfLife(int[][] board) {
        int[][] dir = new int[][]{{-1,-1},{-1,0},{-1,1},{0,-1},{0,1},{1,-1},{1,0},{1,1}};
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                int cntLive = 0;
                for (int d = 0; d < dir.length; d++) {
                    int i2 = i + dir[d][0];
                    int j2 = j + dir[d][1];
                    if (i2 >= 0 && i2 < board.length && j2 >= 0 && j2 < board[0].length) {
                        if (board[i2][j2] > 0) cntLive++;
                    }
                }
                if (board[i][j] > 0) {
                    board[i][j] = cntLive == 2 || cntLive == 3 ? 1 : 2;
                } else {
                    board[i][j] = cntLive == 3 ? -1 : 0;
                }
            }
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 2) board[i][j] = 0;
                if (board[i][j] == -1) board[i][j] = 1;
            }
        }
    }
}

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
