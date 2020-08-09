/*
37. Sudoku Solver
Brute force는 각 칸에 1~9를 넣어서 모든 조합 가능한 스도쿠 판을 만든 후(9^81개), 현재 board와 비교해서 match하는 걸 찾는 것이다.
이것보다 나은 방법은, 각 칸에 1~9를 넣어보긴 하되, 불가능한 숫자는 빼고 가는 것이다.
1. 각 row, col, box당 나온 숫자를 기록하기 위한 배열을 만들고, 여기에 나왔던 숫자들을 기록한다.
2. (0,0)부터 ...->(0,8)->(1,0)->...(8,8) 까지 backtrack() 한다. (8,8)에서 종료한다.
3. 빈 칸이 나오면, 거기서 [1-9] 중에 현재 가능한 숫자를 넣었다 치고 backtrack() 해보고, 아니면 그냥 다음 칸으로 backtrack() 한다.
   여기에서 가능한 숫자를 넣어보는 것은 아닐수도 있으므로, backtrack() 이후에는 삭제해서 원래대로 돌린다.
*/

class Solution {
    boolean[][] rows = new boolean[9][10];
    boolean[][] cols = new boolean[9][10];
    boolean[][] boxs = new boolean[9][10];
    char[][] board;
    boolean solved = false;
    public void solveSudoku(char[][] board) {
        this.board = board;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char c = board[i][j];
                if (c != '.') {
                    placeNumber((int)(c - '0'), i, j);
                }
            }
        }
        backtrack(0, 0);
    }
    int idx(int i, int j) { return (i/3)*3 + (j/3); }
    void placeNumber(int d, int i, int j) {
        rows[i][d] = true;
        cols[j][d] = true;
        boxs[idx(i,j)][d] = true;
        board[i][j] = (char)(d + '0');
    }
    void backtrack(int i, int j) {
        if (board[i][j] == '.') {
            for (int d = 1; d <= 9; d++) {
                if (couldPlace(d, i, j)) {
                    placeNumber(d, i, j);
                    placeNextNumbers(i, j);
                    if (!solved) removeNumber(d, i, j);
                }
            }
        } else {
            placeNextNumbers(i, j);
        }
    }
    boolean couldPlace(int d, int i, int j) {
        return !rows[i][d] && !cols[j][d] && !boxs[idx(i,j)][d];
    }
    void placeNextNumbers(int i, int j) {
        if (i == 8 && j == 8) solved = true;
        else {
            if (j == 8) backtrack(i+1, 0);
            else backtrack(i, j+1);
        }
    }
    void removeNumber(int d, int i, int j) {
        rows[i][d] = false;
        cols[j][d] = false;
        boxs[idx(i,j)][d] = false;
        board[i][j] = '.';
    }
}

class Solution {
    int[][] rows = new int[9][10];
    int[][] cols = new int[9][10];
    int[][] boxs = new int[9][10];
    char[][] board;
    boolean solved = false;
    public void solveSudoku(char[][] board) {
        this.board = board;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char c = board[i][j];
                if (c != '.') {
                    placeNumber((int)(c - '0'), i, j);
                }
            }
        }
        backtrack(0, 0);
    }
    void placeNumber(int d, int i, int j) {
        rows[i][d]++;
        cols[j][d]++;
        boxs[(i/3)*3 + (j/3)][d]++;
        board[i][j] = (char)(d + '0');
    }
    void backtrack(int i, int j) {
        if (board[i][j] == '.') {
            for (int d = 1; d <= 9; d++) {
                if (couldPlace(d, i, j)) {
                    placeNumber(d, i, j);
                    placeNextNumbers(i, j);
                    if (!solved) removeNumber(d, i, j);
                }
            }
        } else {
            placeNextNumbers(i, j);
        }
    }
    boolean couldPlace(int d, int i, int j) {
        return rows[i][d] + cols[j][d] + boxs[(i/3)*3 + (j/3)][d] == 0;
    }
    void placeNextNumbers(int i, int j) {
        if (i == 8 && j == 8) solved = true;
        else {
            if (j == 8) backtrack(i+1, 0);
            else backtrack(i, j+1);
        }
    }
    void removeNumber(int d, int i, int j) {
        rows[i][d]--;
        cols[j][d]--;
        boxs[(i/3)*3 + (j/3)][d]--;
        board[i][j] = '.';
    }
}

class Solution {
    Set<Character>[] rows = new HashSet[9];
    Set<Character>[] cols = new HashSet[9];
    Set<Character>[] boxs = new HashSet[9];
    char[][] board;
    boolean solved = false;
    public void solveSudoku(char[][] board) {
        this.board = board;
        for (int i = 0; i < 9; i++) {
            rows[i] = new HashSet<>();
            cols[i] = new HashSet<>();
            boxs[i] = new HashSet<>();
        }
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char c = board[i][j];
                if (c != '.') {
                    placeNumber(c, i, j);
                }
            }
        }
        backtrack(0, 0);
    }
    int idx(int i, int j) { return (i/3)*3 + (j/3); }
    void placeNumber(char c, int i, int j) {
        rows[i].add(c);
        cols[j].add(c);
        boxs[idx(i,j)].add(c);
        board[i][j] = c;
    }
    void backtrack(int i, int j) {
        if (board[i][j] == '.') {
            for (char c = '1'; c <= '9'; c++) {
                if (couldPlace(c, i, j)) {
                    placeNumber(c, i, j);
                    placeNextNumbers(i, j);
                    if (!solved) removeNumber(c, i, j);
                }
            }
        } else {
            placeNextNumbers(i, j);
        }
    }
    boolean couldPlace(char c, int i, int j) {
        return !rows[i].contains(c) &&
            !cols[j].contains(c) &&
            !boxs[idx(i,j)].contains(c);
    }
    void placeNextNumbers(int i, int j) {
        if (i == 8 && j == 8) solved = true;
        else {
            if (j == 8) backtrack(i+1, 0);
            else backtrack(i, j+1);
        }
    }
    void removeNumber(char c, int i, int j) {
        rows[i].remove(c);
        cols[j].remove(c);
        boxs[idx(i,j)].remove(c);
        board[i][j] = '.';
    }
}