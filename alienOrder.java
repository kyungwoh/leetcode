// http://fisherlei.blogspot.com/2017/07/leetcode-alien-dictionary-solution.html
// https://www.cnblogs.com/jcliBlogger/p/4758761.html
// https://www.geeksforgeeks.org/topological-sorting-indegree-based-solution/
// 1. Build graph
// 2. Topological sort using Kahn's indegree algorithm
// V = # of characters (26 if a-z)
// E = possible ascending order = # of words * max(length of words)
// Time: O(V+E), Space: O(VE)
public class Solution {
	public static void main(String[] args) {
		Solution s = new Solution();
		System.out.println(s.alienOrder(new String[] {"wrt",
				  "wrf",
				  "er",
				  "ett",
				  "rftt"}));
		System.out.println(s.alienOrder(new String[] { "z", "x"}));
		System.out.println(s.alienOrder(new String[] { "z", "x", "z"}));
	}
  public String alienOrder(String[] words) {
		int len = words.length;
		if(len==1) return words[0];
		
		HashMap<Character, HashSet<Character>> graph = new HashMap<Character, HashSet<Character>>();
		for(int i=1; i<len; i++) {
			String w1 = words[i-1], w2 = words[i];
			int w1Len = w1.length(), w2Len = w2.length(), maxLen = Math.max(w1Len, w2Len);
			
			boolean isFound = false;
			for(int j=0; j<maxLen; j++) {
				if(j<w1Len && !graph.containsKey(w1.charAt(j))) graph.put(w1.charAt(j), new HashSet<Character>());
				if(j<w2Len && !graph.containsKey(w2.charAt(j))) graph.put(w2.charAt(j), new HashSet<Character>());
				if(j<w1Len && j<w2Len && w1.charAt(j)!=w2.charAt(j) && !isFound) {
					graph.get(w1.charAt(j)).add(w2.charAt(j));
					isFound = true;
				}
			}
		}
		System.out.println(graph);
		
		HashMap<Character, Boolean> visited = new HashMap<Character, Boolean>();
		HashMap<Character, Integer> inDegree = new HashMap<Character, Integer>();
		for(Entry<Character, HashSet<Character>> e : graph.entrySet()) {
			char in = e.getKey();
			visited.put(in, false);
			inDegree.put(in, inDegree.getOrDefault(in, 0));
			for(char out : e.getValue()) {
				inDegree.put(out, inDegree.getOrDefault(out, 0)+1);
			}
		}
		ArrayDeque<Character> que = new ArrayDeque<Character>();
		for(Entry<Character, Integer> e : inDegree.entrySet()) {
			if(e.getValue()==0) que.add(e.getKey());
		}
		StringBuilder sb = new StringBuilder();
		while(!que.isEmpty()) {
			int size = que.size();
			for(int i=0; i<size; i++) {
				char c = que.poll();
				sb.append(c);
				if(!visited.get(c)) {
					visited.put(c, true);
					for(char out : graph.get(c)) {
						inDegree.put(out, inDegree.getOrDefault(out, 0)-1);
					}
				}				
			}
			for(Entry<Character, Integer> e : inDegree.entrySet()) {
				if(!visited.get(e.getKey()) && e.getValue()==0) {
					que.offer(e.getKey());
				}
			}
		}
		return sb.toString();
	}
}
