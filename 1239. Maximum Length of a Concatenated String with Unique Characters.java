/*
1239. Maximum Length of a Concatenated String with Unique Characters
결국 모든 조합을 다 해봐야 하기 때문에 recursion으로 하는 수밖에 없다. O(2^n)
그나마 bitmap을 쓰면 26 letters 확인을 좀 더 편하게 할 수 있다.

a & b == 0 하나도 겹치는 게 없다
a | b == a와 b의 비트들을 합친다
단, && || 와 달리 & | 는 연산자 우선순위가 < > == 보다 낮기 때문에 괄호로 감싸야 한다.
*/
class Solution {
    List<Integer> bits;
    List<Integer> lens;
    public int maxLength(List<String> arr) {
        bits = new ArrayList<>();
        lens = new ArrayList<>();
        for (String a : arr) {
            int bitmap = 0;
            for (char c : a.toCharArray()) {
                int bit = 1 << (int)(c - 'a');
                if ((bitmap & bit) != 0) { bitmap = 0; break; }
                bitmap |= bit;
            }
            if (bitmap == 0) continue;
            bits.add(bitmap);
            lens.add(a.length());
        }
        return dfs(0, 0);
    }
    int dfs(int i, int bitmap) {
        if (i == bits.size()) return 0;
        int maxLen = 0;
        for (int j = i; j < bits.size(); j++) {
            if ((bitmap & bits.get(j)) != 0) continue;
            maxLen = Math.max(maxLen, dfs(j+1, (bitmap | bits.get(j))) + lens.get(j));
        }
        return maxLen;
    }
}
class Solution {
    int maxLen;
    List<Node> nodes;
    public int maxLength(List<String> arr) {
        nodes = new ArrayList<>();
        maxLen = 0;
        for (String a : arr) {
            Node n = new Node(a);
            if (n.unique) {
                maxLen = Math.max(maxLen, n.len);
                nodes.add(n);
            }
        }
        helper(0, new Node());
        return maxLen;
    }
    void helper(int i, Node n) {
        maxLen = Math.max(maxLen, n.len);
        if (i == nodes.size()) return;
        for (int j = i; j < nodes.size(); j++) {
            Node prev = n.clone();
            if (n.add(nodes.get(j))) helper(j+1, n);
            n = prev;
        }
    }
    class Node {
        boolean[] letters;
        int len;
        boolean unique;
        Node() {
            letters = new boolean[26];
            unique = true;
        }
        Node(String s) {
            letters = new boolean[26];
            unique = true;
            len = s.length();
            for (char c : s.toCharArray()) {
                int i = (int)(c - 'a');
                if (letters[i]) {
                    unique = false;
                    break;
                }
                letters[i] = true;
            }
        }
        public Node clone() {
            Node n = new Node();
            n.len = len;
            for (int i = 0; i < 26; i++) {
                n.letters[i] = letters[i];
            }
            return n;
        }
        boolean add(Node n) {
            for (int i = 0; i < 26; i++) {
                if (letters[i] && n.letters[i]) return false;
                if (!letters[i] && n.letters[i]) {
                    len++;
                    letters[i] = true;
                }
            }
            return true;
        }
    }
}