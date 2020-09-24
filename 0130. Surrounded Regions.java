/*
130. Surrounded Regions
모서리가 터지는 경우(escaped)는 테두리(borders)밖에 없으므로, 테두리부터 시작해서 escaped인 경우를 표시해놓는다.
그러면 'O'인 것이 'O'로 남아있는 것과 'E'로 바뀐 것 2가지로 나뉘는데,
'O'로 남아있는 건 모서리가 다 닫혀있는 경우니까 'X'로 바꾸고, 'E'인건 터진 거니까 원래대로 'O'로 돌린다.

복잡도는 그냥 Time O(nm), Space O(nm) 또는 in-place라면 O(1)
*/
class Solution {
  char[][] board;
  int iLen, jLen;
  public void solve(char[][] board) {
    if (board.length == 0 || board[0].length == 0) return;
    this.board = board;
    iLen = board.length;
    jLen = board[0].length;
    
    List<int[]> borders = new ArrayList<>();
    for (int i = 0; i < iLen; i++) {
      borders.add(new int[]{i, 0});
      borders.add(new int[]{i, jLen-1});
    }
    for (int j = 0; j < jLen; j++) {
      borders.add(new int[]{0, j});
      borders.add(new int[]{iLen-1, j});
    }
    for (int[] b : borders) {
      dfs(b[0], b[1]);
    }
    for (int i = 0; i < iLen; i++) {
      for (int j = 0; j < jLen; j++) {
        if (board[i][j] == 'O') board[i][j] = 'X';
        if (board[i][j] == 'E') board[i][j] = 'O';
      }
    }
  }
  void dfs(int i, int j) {
    if (i < 0 || i >= iLen || j < 0 || j >= jLen) return;
    if (board[i][j] != 'O') return;
    
    board[i][j] = 'E';
    dfs(i-1, j);
    dfs(i, j-1);
    dfs(i+1, j);
    dfs(i, j+1);
  }
}