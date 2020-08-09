/*
675. Cut Off Trees for Golf Event
일단 시작점에 돌아오는 경우가 있는지 물어봐야 한다. 시작점이 0일 경우 바로 -1을 리턴해야 하는지도 물어본다. 그 외에는 cut off해야 하는 지점들을 넣어서 정렬하고, prev, curr를 잡아서 순서대로 shortest path를 구한다. 가장 간단히는 BFS로 구하고 그 외에 A* search, Hadlock's algorithm이 있다. 어느 경우건 앞에 썼던 shortest path의 데이터를 재활용할 수 없다.
*/
class Solution {
    public int cutOffTree(List<List<Integer>> forest) {
        if (forest.size() == 0 || forest.get(0).size() == 0 || forest.get(0).get(0) == 0) return -1;
        
        LinkedList<int[]> list = new LinkedList<>();
        for (int i = 0; i < forest.size(); i++) {
            for (int j = 0; j <forest.get(i).size(); j++) {
                int k = forest.get(i).get(j);
                if (k > 1) list.add(new int[]{i,j,k});
            }
        }
        Collections.sort(list, (a,b) -> Integer.compare(a[2], b[2]));
        
        int[] prev = new int[]{0,0,0};
        int sum = 0;
        while (!list.isEmpty()) {
            int[] curr = list.poll();
            int len = getLen(prev, curr, forest);
            if (len == -1) return -1;
            sum += len;
            prev = curr;
        }
        return sum;
    }
    int getLen(int[] start, int[] end, List<List<Integer>> forest) {
        boolean[][] visited = new boolean[forest.size()][forest.get(0).size()];
        Queue<int[]> q = new LinkedList<>();
        q.offer(start);
        int cnt = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int a = 0; a < size; a++) {
                int[] curr = q.poll();
                int i = curr[0], j = curr[1];
                if (visited[i][j]) continue;
                visited[i][j] = true;
                if (i == end[0] && j == end[1]) return cnt;
                if (i > 0 && forest.get(i-1).get(j) >= 1) q.offer(new int[]{i-1, j, 0});
                if (j > 0 && forest.get(i).get(j-1) >= 1) q.offer(new int[]{i, j-1, 0});
                if (i < forest.size() - 1 && forest.get(i+1).get(j) >= 1) q.offer(new int[]{i+1, j, 0});
                if (j < forest.get(i).size() - 1 && forest.get(i).get(j+1) >= 1) q.offer(new int[]{i, j+1, 0});
            }
            cnt++;
        }
        return -1;
    }
}