// https://leetcode.com/problems/group-anagrams/description/
// Strings only have lowercase characters, so use int[26] as a key
// (Improved) Use int[26].hashCode() as a key + collision detection
// String length = m, Number of strings = n
// Time: O(max(mn,26mn^2), Space: O(mn)
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<Integer, List<Node>> map = new HashMap<Integer, List<Node>>();
        List<List<String>> rList = new ArrayList<List<String>>();
        int index = 0;
        for(String s : strs){
            Node n = new Node(s);
            List<Node> nList = map.get(n.hashCode());
            if(nList==null){
                nList = new ArrayList<Node>();
                map.put(n.hashCode(), nList);
            }
            boolean hasNode = false;
            for(Node m : nList){
                if(m.equals(n)){
                    hasNode = true;
                    n = m;
                }
            }
            if(!hasNode){
                rList.add(new ArrayList<String>());
                n.index = index++;
                nList.add(n);
            }
            rList.get(n.index).add(s);
        }
        return rList;
    }
}
class Node{
    int index;
    int[] cnt = new int[26];
    Node(String s){
        for(char c : s.toCharArray()){
            int i = c - 'a';
            cnt[i]++;
        }
    }
    public boolean equals(Object o){
        Node n = (Node)o;
        for(int i=0; i<26; i++) if(cnt[i]!=n.cnt[i]) return false;
        return true;
    }
    public int hashCode() { return Arrays.hashCode(cnt); }
}
