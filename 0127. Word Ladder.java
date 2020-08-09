/*
127. Word Ladder
위와 달리 길이만 찾으면 되서 간단하지만 원리는 같다. beginWord부터 인접한 것들을 따라가고, 자기 자신으로 돌아오는 cycle은 없을테니 전체에서 인접 것들을 remove하고 next 후보에 넣는다. 그리고 그 next에 endWord가 있으면 찾은 것이니 끝난 거다. 만약 next 후보에 아무것도 없거나, 계속 전체에서 지워나갔는데 그게 비었으면 못 찾은 거니 0을 리턴. 이러면 Set을 쓰기 때문에 "dog" -> "dot" 이렇게 찾는게 O(len)이 걸리고(Set을 안 쓰면 매번 길이만큼 찾아야 하니까 O(len^2)가 걸린다), 이것을 최악의 경우 wordList의 O(n)만큼 돌테니 (왜냐하면 찾은 건 지우니까 겹치게 찾는 게 없다) 전체는 O(n*max(len)) 이 된다.
*/
class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<>(wordList);
        wordSet.remove(beginWord);
        Set<String> currSet = new HashSet<>();
        currSet.add(beginWord);
        int cnt = 1;
        while (!currSet.isEmpty() && !wordSet.isEmpty()) {
            cnt++;
            Set<String> nextSet = new HashSet<>();
            for (String curr : currSet) {
                Set<String> adj = getAdj(curr, wordSet);
                wordSet.removeAll(adj);
                nextSet.addAll(adj);
            }
            if (nextSet.contains(endWord)) return cnt;
            currSet = nextSet;
        }
        return 0;
    }
    Set<String> getAdj(String curr, Set<String> wordSet) {
        Set<String> adj = new HashSet<>();
        char[] carr = curr.toCharArray();
        for (int i = 0; i < carr.length; i++) {
            for (char cnew = 'a'; cnew <= 'z'; cnew++) {
                char cold = carr[i];
                carr[i] = cnew;
                String s = new String(carr);
                if (wordSet.contains(s)) adj.add(s);
                carr[i] = cold;
            }
        }
        return adj;
    }
}