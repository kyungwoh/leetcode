/*
79. Word Search
가능한 모든 방향으로 DFS를 하면 된다. int[][] dir = new int[][]{{-1,0},{0,-1},{1,0},{0,1}} 쓰면 편하다. 순서는 1. i,j가 범위를 벗어나는지? 2. 현재 문자가 일치하는지? 3. 끝에 도달했는지? 4. 아니라면 현재 걸 '#'로 바꾼 다음 4방향으로 갔다와보고, 다시 원래 old char 돌린다. 만약 true라면 바로 return 한다. time O(n*4^len), space O(len)
*/
class Solution {
    char[][] board;
    String word;
    static int[][] dir = new int[][]{{-1,0},{0,-1},{1,0},{0,1}};
    public boolean exist(char[][] board, String word) {
        this.board = board;
        this.word = word;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0 ; j < board[i].length; j++) {
                if (dfs(i, j, 0)) return true;
            }
        }
        return false;
    }
    boolean dfs(int i, int j, int k) {
        if (i<0 || i>=board.length || j<0 || j>=board[i].length) return false;
        if (word.charAt(k) != board[i][j]) return false;
        if (k == word.length()-1) return true;
        
        char old = board[i][j];
        for (int d = 0; d < 4; d++) {
            board[i][j] = '#';
            if (dfs(i + dir[d][0], j + dir[d][1], k+1)) return true;
            board[i][j] = old;
        }
        return false;
    }
}