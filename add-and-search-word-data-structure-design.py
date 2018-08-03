# https://leetcode.com/problems/add-and-search-word-data-structure-design/description/
# Use Trie
# If # of characters = 26, Time: O(26^n), Space: O(26^max(n))
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
