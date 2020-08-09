/*
212. Word Search II
이번에도 마찬가지로 DFS이지만, word가 여러개이기 때문에 Trie를 쓰는게 낫다. isEnd에 도달했을 때, 지금까지의 경로를 하나씩 더해나가면 비효율적이니까 Trie에 s를 저장해놓는게 낫다. 처음 dummy head부터 시작해서, "a" 이렇게 1자인 경우도 잘 되도록 해야 한다. head -> "a" -> end 인 걸 처음에 찾아야 하니까, curr = curr.next 먼저 한 다음에 end인걸 검사해야 한다. 결과가 중복일 수 있으니 Set을 써야 할지 물어본다.
*/
class Solution {
    Set<String> ans;
    char[][] board;
    public List<String> findWords(char[][] board, String[] words) {
        Trie head = new Trie();
        for (String w : words) {
            Trie curr = head;
            for (char c : w.toCharArray()) {
                int i = (int)(c - 'a');
                if (curr.next[i] == null) curr.next[i] = new Trie();
                curr = curr.next[i];
            }
            curr.s = w;
        }
        ans = new HashSet<>();
        this.board =board; 
        for (int i =0; i<board.length; i++) {
            for (int j =0; j<board[i].length; j++) {
                dfs(i,j,head);
            }
        }
        return new ArrayList<>(ans);
    }
    static int[][] dir = new int[][]{{-1,0},{0,-1},{1,0},{0,1}};
    void dfs(int i, int j, Trie curr) {
        if (i<0 || i>=board.length || j<0 || j>=board[i].length) return;
        if (board[i][j] == '#') return;
        
        curr = curr.next[(int)(board[i][j] - 'a')];
        if (curr == null) return;
        if (curr.s != null) ans.add(curr.s);
        
        char old = board[i][j];
        for (int d = 0; d<4; d++) {
            board[i][j] = '#';
            dfs(i + dir[d][0], j + dir[d][1], curr);
            board[i][j] = old;
        }
    }
    
    class Trie {
        Trie[] next;
        String s;
        Trie() {
            next = new Trie[26];
            s = null;
        }
    }
}