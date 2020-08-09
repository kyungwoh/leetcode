/*
56. Merge Intervals
둘씩 비교하며 다 합치려면 O(n^2)가 걸리니, 일단 정렬하고 합치면 O(nlogn)으로 준다. min(시작점), max(끝점) 처리를 잘 해야 한다. 온라인으로 한다고 해도 일단 시작점으로 정렬해놓으면 그 다음은 똑같은데, 삽입할 위치를 찾는데 O(logn), 아주 긴 것이 들어올 수 있으므로 계속 merge하느라 O(n)까지 걸릴 수 있다.
*/
class Solution {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a,b) -> Integer.compare(a[0],b[0]));
        LinkedList<int[]> list = new LinkedList<>();
        for (int i = 0; i < intervals.length; i++) {
            int[] curr = intervals[i];
            if (i == 0) { list.add(curr); continue; }
            
            if (list.getLast()[1] >= curr[0]) list.getLast()[1] = Math.max(list.getLast()[1], curr[1]);
            else list.add(curr);
        }
        return list.toArray(new int[list.size()][]);
    }
}