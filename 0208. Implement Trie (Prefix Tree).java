/*
208. Implement Trie (Prefix Tree)
단순한 Trie 구현 문제다.
*/
class Trie {
    Node head;

    /** Initialize your data structure here. */
    public Trie() {
        head = new Node();
    }
    
    /** Inserts a word into the trie. */
    public void insert(String word) {
        Node curr = head;
        for (char c : word.toCharArray()) {
            int i = (int)(c - 'a');
            if (curr.next[i] == null) curr.next[i] = new Node();
            curr = curr.next[i];
        }
        curr.isEnd = true;
    }
    
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        Node curr = head;
        for (char c : word.toCharArray()) {
            int i = (int)(c - 'a');
            if (curr.next[i] == null) return false;
            curr = curr.next[i];
        }
        return curr.isEnd;
    }
    
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        Node curr = head;
        for (char c : prefix.toCharArray()) {
            int i = (int)(c - 'a');
            if (curr.next[i] == null) return false;
            curr = curr.next[i];
        }
        return true;
    }
    
    class Node {
        boolean isEnd = false;
        Node[] next = new Node[26];
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */