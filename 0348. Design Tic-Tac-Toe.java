/*
348. Design Tic-Tac-Toe
종료조건을 생각해보면 가로, 세로, 대각선이 n개에 도달할때이므로, 각각의 플레이어별로 몇 개나 놓았는지 세다가 n에 도달하면 종료하면 된다. 이러면 space = n * player + 대각선 2개 * player이므로 O(n)이다. rows, cols, diagonal 2개 = 4개니까 O(1) time이다.
*/
class TicTacToe {
    int[][] rows, cols;
    int[] dia1, dia2;
    int n;

    /** Initialize your data structure here. */
    public TicTacToe(int n) {
        rows = new int[2][n];
        cols = new int[2][n];
        dia1 = new int[2];
        dia2 = new int[2];
        this.n = n;
    }
    
    /** Player {player} makes a move at ({row}, {col}).
        @param row The row of the board.
        @param col The column of the board.
        @param player The player, can be either 1 or 2.
        @return The current winning condition, can be either:
                0: No one wins.
                1: Player 1 wins.
                2: Player 2 wins. */
    public int move(int row, int col, int player) {
        if (++rows[player-1][row] == n) return player;
        if (++cols[player-1][col] == n) return player;
        if (row == col) {
            if (++dia1[player-1] == n) return player;
        }
        if (row + col == n - 1) {
            if (++dia2[player-1] == n) return player;
        }
        return 0;
    }
}

/**
 * Your TicTacToe object will be instantiated and called as such:
 * TicTacToe obj = new TicTacToe(n);
 * int param_1 = obj.move(row,col,player);
 */