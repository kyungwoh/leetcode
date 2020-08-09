/*
269. Alien Dictionary

각 String마다 a->b, b->c 이렇게 순서를 찾아서 다 연결해야 하니까, 의외로 그래프 문제, topological sort 문제다. indegree도 필요.
그래프의 각 node key를 찾아야 하는데, 일단 다 돌면서 map에 char를 넣어놓을수도 있고, a-z라면 그냥 int[26] 해도 된다.
그리고 String을 돌면서 adjacent node들을 붙여가면 된다. 이때 indegree++도 해주고.

문제는 두 String의 길이가 다른 경우 어떡하나? 일단 두 node가 없으니 그래프를 만들 순 없다.
lexicographically 짧은 String이 항상 긴 String보다 precede하다면, invalid하므로 바로 return ""; 한다.

그 다음에 topological sort. indegree가 0인 것부터 차례대로 찾아나가면서 다음 것들의 indegree를 하나씩 줄인다.
이때 모든 가능한 path를 찾으려면 모두 다 가보고, 그 중 하나만 가 보려면 indegree가 0인 것만 따라 가면 됨.
순서를 따질 때는 맨 처음 바뀌기 시작한 것만 쓰고, adjacents를 하나씩 쌓아나감. (그러면서 indegree도 같이 계산)

맨 마지막에 cycle이 있는지 검사해야 한다. 생성한 string의 길이가 node의 갯수와 같은지 (모든 edge를 지웠는지) 확인해서 아니면 return "";
*/
class Solution {
    public String alienOrder(String[] words) {
        Map<Character, Node> map = new HashMap<>();
        for (String w : words) {
            for (char c : w.toCharArray()) {
                if (!map.containsKey(c)) map.put(c, new Node());
            }
        }
        
        for (int i = 1; i < words.length; i++) {
            String w0 = words[i-1], w1 = words[i];
            for (int j = 0; j < Math.max(w0.length(), w1.length()); j++) {
                if (j < w0.length() && j == w1.length()) return "";
                if (j == w0.length() || j == w1.length()) break;
                char c0 = w0.charAt(j), c1 = w1.charAt(j);
                if (c0 != c1) {
                    map.get(c0).adj.add(c1);
                    map.get(c1).in++;
                    break;
                }
            }
        }
        
        StringBuilder sb = new StringBuilder();
        Queue<Character> q = new LinkedList<>();
        for (Map.Entry<Character, Node> e : map.entrySet()) if (e.getValue().in == 0) q.offer(e.getKey());
        while (q.size() > 0) {
            char c = q.poll();
            sb.append(c);
            for (char d : map.get(c).adj) {
                map.get(d).in--;
                if (map.get(d).in == 0) q.offer(d);
            }
        }
        return sb.length() < map.size() ? "" : sb.toString();
    }
}
class Node {
    int in;
    List<Character> adj;
    Node() {
        in = 0;
        adj = new ArrayList<>();
    }
}

class Solution {
    public String alienOrder(String[] words) {
        Map<Integer, List<Integer>> arr = new HashMap<>();
        Map<Integer, Integer> cnt = new HashMap<>();
        for (String w : words) {
            for (char c : w.toCharArray()) {
                int i = c - 'a';
                arr.put(i, new ArrayList<>());
                cnt.put(i, 0);
            }
        }
        
        for (int i = 1; i < words.length; i++) {
            String w0 = words[i-1];
            String w1 = words[i];
            if (w0.length() > w1.length() && w0.startsWith(w1)) return "";
            
            for (int j = 0; j < Math.min(w0.length(), w1.length()); j++) {
                int k0 = w0.charAt(j) - 'a';
                int k1 = w1.charAt(j) - 'a';
                if (k0 != k1) {
                    arr.get(k0).add(k1);
                    cnt.put(k1, cnt.get(k1) + 1);
                    break;
                }
            }
        }
        
        StringBuilder sb = new StringBuilder();
        Queue<Integer> q = new LinkedList<>();
        for (int i : cnt.keySet()) {
            if (cnt.get(i) == 0) q.offer(i);
        }
        while (!q.isEmpty()) {
            int i = q.poll();
            sb.append((char)('a' + i));
            for (int j : arr.get(i)) {
                cnt.put(j, cnt.get(j) - 1);
                if (cnt.get(j) == 0) q.offer(j);
            }
        }
        
        return sb.length() < cnt.size() ? "" : sb.toString();
    }
}