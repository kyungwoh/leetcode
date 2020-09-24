/*
210. Course Schedule II
topological sort를 하면 된다. Time = Space O(V+E)
next node들을 쌓아가면서 indegree를 세고, 그래서 indegree == 0 인 지점들을 큐에 넣고 시작.
그래서 큐에서 pop한걸 ans에 넣고, 다음 노드들의 indegree를 하나씩 깍고 그게 0이면 큐에 넣음.
끝까지 갔을때 모든 indgree가 0이거나 모든 노드를 돌았으면 no cycle.
*/
class Solution {
    public int[] findOrder(int n, int[][] prerequisites) {
        int[] ins = new int[n];
        List<Integer>[] nexts = new ArrayList[n];
        for (int i = 0; i < n; i++) nexts[i] = new ArrayList<>();
        for (int[] p : prerequisites) {
            ins[p[0]]++;
            nexts[p[1]].add(p[0]);
        }
        int[] ans = new int[n];
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (ins[i] == 0) q.add(i);
        }
        int j = 0;
        while (!q.isEmpty()) {
            int i = q.poll();
            ans[j++] = i;
            for (int next : nexts[i]) {
                if (--ins[next] == 0) q.add(next);
            }
        }
        return j == n ? ans : new int[]{};
    }
}