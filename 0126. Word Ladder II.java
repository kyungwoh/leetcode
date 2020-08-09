/*
126. Word Ladder II
String array이지만 그래프로 풀어야 한다. 인접한 스트링인지 판단해서, BFS (+ Dijkstra)로 풀어서 shortest path를 찾는다. beginWord부터 인접한 것들을 따라가서 endWord에
도달했을 때 ans에 추가해주면 된다. String 비교할때 앞에서부터 차근차근 비교하기보다는 아예 한 자리를 a-z로 바꾼 String을 새로 만들어서 equals()로 비교하는게 더 빠르다. Set, Map을 이용하면 좋다.
*/
class Solution {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        HashMap<String, List<String>> parent = new HashMap<>();
        HashSet<String> wordSet = new HashSet<>(wordList); // wordList set so that we can verify existence with in O(1)
        List<List<String>> res = new ArrayList<>();
        if(!wordSet.contains(endWord)) return res;
        HashSet<String> startSet = new HashSet<>();
        startSet.add(beginWord);
        HashSet<String> endSet = new HashSet<>();
        endSet.add(endWord);
        bfs(parent, wordSet, startSet, endSet, false);
        
        // DFS
        List<String> path = new ArrayList<>();
        path.add(beginWord);
        searchPath(parent, beginWord, endWord, res, path);
        return res;
    }
    
    private void bfs(HashMap<String, List<String>> parent, HashSet<String> wordSet, HashSet<String> startSet, HashSet<String> endSet, boolean opposite){
        if(startSet.size() == 0) return;
        if(startSet.size() > endSet.size()){
            bfs(parent, wordSet, endSet, startSet, !opposite);
            return;   
        }
        HashSet<String> nextLevelSet = new HashSet<>(); 
        wordSet.removeAll(startSet);
        boolean found = false;
        for(String word : startSet){
            char[] chs = word.toCharArray();
            for(int m = 0; m < chs.length; m++){
                char old = chs[m];
                for(char j = 'a'; j <= 'z'; j++){
                    if(j == old) continue;
                    chs[m] = j;
                    String str = new String(chs);
                    if(wordSet.contains(str)){
                        if(endSet.contains(str)){  // !!!!!
                            found = true;
                        } else{
                            nextLevelSet.add(str);
                        }
                        String key = opposite? str: word;
                        String val = opposite? word: str;
                        if(!parent.containsKey(key)){
                            parent.put(key, new ArrayList<String>());
                        }
                        parent.get(key).add(val);
                    }
                }
                chs[m] = old;
            }
        }
        
        if(!found){
            bfs(parent, wordSet, nextLevelSet, endSet, opposite);
        }
    }
    
    private void searchPath(HashMap<String, List<String>> parent, String beginWord, String endWord, List<List<String>> res, List<String> path){
        if(beginWord.equals(endWord)){
            res.add(new ArrayList<String>(path));
        } else {
            if(!parent.containsKey(beginWord)) return;
            for(String str : parent.get(beginWord)){
                path.add(str);
                searchPath(parent, str, endWord, res, path);
                path.remove(path.size() -1);
            }
        }
    }
}