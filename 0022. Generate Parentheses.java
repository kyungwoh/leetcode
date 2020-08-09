/*
22. Generate Parentheses
이것도 모든 combination을 따져봐야 한다. time & space = catalan number asymptotic(4^n / nlogn *n = 4^n / logn)
*/
class Solution {
    List<String> ans;
    public List<String> generateParenthesis(int n) {
        ans = new ArrayList<>();
        dfs(n, 0, 0, new StringBuilder());
        return ans;
    }
    void dfs(int n, int open, int close, StringBuilder sb) {
        if (open == n && close == n) {
            if (sb.length() > 0) ans.add(sb.toString());
            return;
        }
        if (open < n) {
            sb.append('(');
            dfs(n, open+1, close, sb);
            sb.setLength(sb.length()-1);
        }
        if (open > close) {
            sb.append(')');
            dfs(n, open, close+1, sb);
            sb.setLength(sb.length()-1);
        }
    }
}
class Solution {
    List<String> ans;
    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(0, 0, ""));
        while (!q.isEmpty()) {
            Node node = q.poll();
            if (node.open == n && node.close == n) {
                if (!node.s.isEmpty()) ans.add(node.s);
                continue;
            }
            if (node.open < n) q.add(new Node(node.open+1, node.close, node.s + '('));
            if (node.open > node.close) q.add(new Node(node.open, node.close+1, node.s + ')'));
        }
        return ans;
    }
    class Node {
        int open, close;
        String s;
        Node(int open, int close, String s) {
            this.open = open;
            this.close = close;
            this.s = s;
        }
    }
}