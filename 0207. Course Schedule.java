/*
207. Course Schedule
cycle을 찾는 것으로 topological sort를 이용하면 time O(E+V)에 구할 수 있다.
indegree == 0 인 노드를 root로 해서 큐에 넣고, 인접한 indegree들을 하나씩 줄이다가 0이 되는 순간 큐에 집어넣는다.
이걸 0이 되는 순서가 topological sort가 된다. 그리고 이렇게 끝까지 돌았는데 아직도 안 지워진 edge가 있으면 cycle이 있는 거다.
그래서 remove cnt != edge length 면 hasCycle이 됨.

아니면 DFS로 매 V마다 인접한 E로 내려가면서 visited면 hasCycle이다. 이때 내려갔다 올라오면 다시 visited = false해줘야 겹치는 경로를 뺄 수 있다.
이때 hasCycle이 아닌 노드를 memo해놓으면 O(E+V)로 줄일 수 있다. 아니면 다 내려갔다 와야 하니까 O(E+V^2)가 됨.
*/
class Solution {
    public boolean canFinish(int num, int[][] pre) {
        if (pre.length == 0) return true;
        
        Set<Integer>[] edges = new HashSet[num];
        for (int i = 0; i < num; i++) edges[i] = new HashSet<>();
        int[] in = new int[num];
        
        for (int[] p : pre) {
            edges[p[1]].add(p[0]);
            in[p[0]]++;
        }
        
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < num; i++) {
            if (in[i] == 0) q.offer(i);
        }
        
        int cnt = 0;
        while(!q.isEmpty()) {
            int i = q.poll();
            for (int j : edges[i]) {
                cnt++;
                if (--in[j] == 0) q.offer(j);
            }
        }
        
        return cnt == pre.length;
    }
}

class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        Set<Integer>[] edges = new HashSet[numCourses];
        for (int i = 0; i < numCourses; i++) edges[i] = new HashSet<>();
        for (int[] p : prerequisites) edges[p[1]].add(p[0]);
        
        boolean[] visited = new boolean[numCourses];
        boolean[] memo = new boolean[numCourses];
        for (int i = 0; i < numCourses; i++) {
            if (hasCycle(i, edges, visited, memo)) return false;
        }
        return true;
    }
    boolean hasCycle(int i, Set<Integer>[] edges, boolean[] visited, boolean[] memo) {
        if (memo[i]) return false;
        if (visited[i]) return true;
        visited[i] = true;
        for (int j : edges[i]) {
            if (hasCycle(j, edges, visited, memo)) return true;
        }
        visited[i] = false;
        memo[i] = true;
        return false;
    }
}

class Solution {
    public boolean canFinish(int n, int[][] prerequisites) {
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int[] p : prerequisites) {
            if (!map.containsKey(p[1])) map.put(p[1], new HashSet<>());
            map.get(p[1]).add(p[0]);
        }
        boolean[] visited = new boolean[n];
        boolean[] noCycle = new boolean[n];
        for (int i : map.keySet()) {
            if (hasCycle(i, map, visited, noCycle)) return false;
        }
        return true;
    }
    boolean hasCycle(int i, Map<Integer, Set<Integer>> map, boolean[] visited, boolean[] noCycle) {
        if (noCycle[i]) return false;
        if (visited[i]) return true;
        if (!map.containsKey(i)) return false;
        
        visited[i] = true;
        if (map.containsKey(i)) {
            for (int j : map.get(i)) {
                if (hasCycle(j, map, visited, noCycle)) return true;
            }
        }
        visited[i] = false;
        noCycle[i] = true;
        return false;
    }
}