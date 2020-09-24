/*
329. Longest Increasing Path in a Matrix
작은 숫자에서 큰 숫자로 하나씩 증가하는 경로를 찾으면 되니까, 그래프로 풀었다.
1. 각 칸마다 DFS + memo -> Time O(mn * mn), Space O(mn)
2. 그래프 만들고, topological sort해서 root nodes 찾은 다음 BFS -> Time O(mn), Space O(mn)
매 칸마다 주위 4칸에 자기보다 하나 큰 수가 있으면 그걸 연결해나간다.
그러면서 indegree도 센 다음, indegree == 0 인 root node들부터 BFS를 시작해서 순회한다.
그래서 총 몇 level이나 갔는지, 그 level이 제일 긴 경로일 것이다.
Time, Space O(V+E) = O(nm)
*/
class Solution {
    int[][] dir = new int[][]{{-1,0},{0,-1},{1,0},{0,1}};
    public int longestIncreasingPath(int[][] matrix) {
        Map<Integer, Integer> ins = new HashMap<>();
        Map<Integer, Set<Integer>> nexts = new HashMap<>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                int x = matrix[i][j];
                int ij = i * matrix[i].length + j;
                if (!ins.containsKey(ij)) ins.put(ij, 0);
                if (!nexts.containsKey(ij)) nexts.put(ij, new HashSet<>());
                for (int[] d : dir) {
                    int i2  = i + d[0], j2 = j + d[1];
                    if (i2 < 0 || i2 >= matrix.length || j2 < 0 || j2 >= matrix[i].length) continue;
                    int y = matrix[i2][j2];
                    int ij2 = i2 * matrix[i].length + j2;
                    if (x < y) {
                        if (!ins.containsKey(ij2)) ins.put(ij2, 0);
                        if (!nexts.containsKey(ij2)) nexts.put(ij2, new HashSet<>());
                        ins.put(ij2, ins.get(ij2)+1);
                        nexts.get(ij).add(ij2);
                    }
                }
            }
        }
        Set<Integer> curr = new HashSet<>();
        for (Map.Entry<Integer, Integer> e : ins.entrySet()) {
            if (e.getValue() == 0) curr.add(e.getKey());
        }
        int level = 0;
        while (curr.size() > 0) {
            level++;
            Set<Integer> next = new HashSet<>();
            for (int c : curr) {
                for (int adj : nexts.get(c)) {
                    next.add(adj);
                }
            }
            curr = next;
        }
        return level;
    }
}