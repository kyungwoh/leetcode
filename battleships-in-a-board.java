// https://leetcode.com/problems/battleships-in-a-board/description/
class Solution {
    int ans;
    // Each battleship has 2 top-left spaces. Just count them.
    // Time: O(n), Space: O(1)
    public int countBattleships(char[][] board) {
        int l1 = board.length; if(l1==0) return 0;
        int l2 = board[0].length; if(l2==0) return 0;
        ans = 0;
        for(int i=0; i<l1; i++){
            for(int j=0; j<l2; j++){
                if(board[i][j]=='X'){
                    if((i==0 || (i>0 && board[i-1][j]=='.'))
                       && (j==0 || (j>0 && board[i][j-1]=='.'))){
                        ans++;
                    }
                }
            }
        }
        return ans;
    }
    // Union & Find (+Path compression)
    // Time: O(n), Space: O(n)
    public int countBattleships2(char[][] board) {
        int l1 = board.length; if(l1==0) return 0;
        int l2 = board[0].length; if(l2==0) return 0;
        int[] b = new int[l1*l2+1];
        Arrays.fill(b,-1);
        ans = 0;
        for(int i=0; i<l1; i++){
            for(int j=0; j<l2; j++){
                if(board[i][j]=='X'){
                    ans++;
                    b[i*l2+j] = i*l2+j;
                }
            }
        }
        for(int i=0; i<l1; i++){
            for(int j=0; j<l2; j++){
                if(board[i][j]=='X'){
                    if(i>0 && board[i-1][j]=='X') union(i*l2+j, (i-1)*l2+j, b);
                    if(j>0 && board[i][j-1]=='X') union(i*l2+j, i*l2+(j-1), b);
                }
            }
        }
        return ans;
    }
    private void union(int b1, int b2, int[] b){
        int r1 = find(b1, b);
        int r2 = find(b2, b);
        if(r1!=r2){
            b[b1] = r2;
            ans--;
        }
    }
    private int find(int b1, int[] b){
        int r1 = b[b1];
        while(r1!=b[r1]){
            r1 = b[r1];
            b[b1] = r1;
        }
        return r1;
    }
}
