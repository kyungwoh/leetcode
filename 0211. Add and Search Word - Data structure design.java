/*
211. Add and Search Word - Data structure design
If # of characters = 26, Time: O(26^n), Space: O(26^max(n))

Set에 '.' 인 경우를 다 미리 넣어놔도 되지만, 이러면 Set의 크기가 너무 커진다.
Trie로 만들어놓고 '.'인 경우는 모든 경우를 다 가보도록 하면 좀 낫다.
*/
class WordDictionary {
    class Trie {
        Trie[] next;
        boolean isEnd;
        Trie() {
            next = new Trie[26];
        }
    }
    Trie head;

    /** Initialize your data structure here. */
    public WordDictionary() {
        head = new Trie();
    }
    
    /** Adds a word into the data structure. */
    public void addWord(String word) {
        Trie curr = head;
        for (char c : word.toCharArray()) {
            int i = (int)(c - 'a');
            if (curr.next[i] == null) curr.next[i] = new Trie();
            curr = curr.next[i];
        }
        curr.isEnd = true;
    }
    
    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        return search(word, 0, head);
    }
    boolean search(String word, int i, Trie curr) {
        if (i == word.length()) return curr.isEnd;
        char c = word.charAt(i);
        if (c == '.') {
            for (Trie next : curr.next) {
                if (next != null && search(word, i+1, next)) return true;
            }
        } else {
            int j = (int)(c - 'a');
            if (curr.next[j] != null && search(word, i+1, curr.next[j])) return true;
        }
        return false;
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */

 class WordDictionary:

    def __init__(self):
        self._root = TrieNode()        

    def addWord(self, word):
        curr = self._root
        for c in word:
            if not c in curr.next:
                curr.next[c] = TrieNode()
            curr = curr.next[c]
        curr.isEnd = True

    def search(self, word):
        return self._search(word, 0, self._root)
    
    def _search(self, word, i, curr):
        #print(word, i, curr.isEnd)
        if i==len(word):
            return curr.isEnd;
        else:
            if word[i]=='.':
                if curr.next:
                    for k,v in curr.next.items():
                        if self._search(word, i+1, v):
                            return True
            elif word[i] in curr.next:
                return self._search(word, i+1, curr.next[word[i]])
        return False        
        
class TrieNode:
    def __init__(self):
        self.next = {}
        self.isEnd = False

# Your WordDictionary object will be instantiated and called as such:
# obj = WordDictionary()
# obj.addWord(word)
# param_2 = obj.search(word)