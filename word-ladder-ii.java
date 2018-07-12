// https://leetcode.com/problems/word-ladder-ii/description/
// string length = s, wordList size = n
// 1. Build path -- Time: O(sn^2), Space: O(n^2)
// 2. Search with Dijkstra -- Time: O(nlogn), Space: O(n)
// 3. Track the shortest paths -- Time: O(n^2), Space: O(n^2)
class Solution {
    List<Node> nList;
    List<List<String>> rList;
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        rList = new ArrayList<List<String>>();
        if(beginWord.length()==0 || wordList.size()==0) return rList;
        
        nList = new ArrayList<Node>();
        nList.add(new Node(beginWord));
        boolean hasEnd = false;
        for(String s : wordList){
            if(s.equals(beginWord)) continue;
            else if(s.equals(endWord)){ hasEnd = true; continue; }
            else nList.add(new Node(s));
        }
        if(!hasEnd) return rList;
        nList.add(new Node(endWord));
        
        int l = nList.size();
        for(int i=0; i<l; i++){
            for(int j=i+1; j<l; j++){
                if(compareStrings(nList.get(i).s, nList.get(j).s)){
                    if(i==0 //beginWord : only out-path
                       || j==l-1 //endWord : only in-path
                      ) nList.get(i).next.add(j);
                    else{
                        nList.get(i).next.add(j);
                        nList.get(j).next.add(i);
                    }
                }
            }
        }
        
        PriorityQueue<Node> pq = new PriorityQueue<Node>();
        nList.get(0).d = 0;
        nList.get(0).n = 0;
        pq.offer(nList.get(0));
        while(!pq.isEmpty()){
            Node m = pq.poll();
            Node n = nList.get(m.n);
            if(n.isVisited) continue;
            n.isVisited = true;
            for(int i : n.next){
                Node nn = nList.get(i);
                if(nn.d >= m.d+1){
                    nn.d = m.d+1;
                    nn.prev.add(m.n);
                    nn.n = i;
                    pq.offer(nn);
                }
            }
        }
        LinkedList<Node> listN = new LinkedList<Node>();        
        LinkedList<String> listS = new LinkedList<String>();
        listN.addLast(nList.get(l-1));
        listS.addLast(nList.get(l-1).s);
        addPrev(0, l-1, listN, listS);
        return rList;
    }
    private boolean compareStrings(String s1, String s2){
        int diff = 0;
        for(int i=0; i<s1.length(); i++){
            if(s1.charAt(i)!=s2.charAt(i)) diff++;
        }
        return diff==1;
    }
    private void addPrev(int begin, int end, LinkedList<Node> listN, LinkedList<String> listS){
        if(begin==end){
            rList.add((List<String>)listS.clone());
        }else{
            for(int i : nList.get(end).prev){
                listN.addFirst(nList.get(i));
                listS.addFirst(nList.get(i).s);
                addPrev(begin, i, listN, listS);
                listN.removeFirst();
                listS.removeFirst();
            }
        }
    }
}
class Node implements Comparable<Node> {
    int d = Integer.MAX_VALUE, n;
    String s;
    List<Integer> prev = new ArrayList<Integer>();
    List<Integer> next = new ArrayList<Integer>();
    boolean isVisited = false;
    Node(String s){ this.s = s; }
    public int compareTo(Node o){ return Integer.compare(d, o.d); }
}
