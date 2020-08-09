/*
253. Meeting Rooms II
최소한의 자원만 쓰려면 greedy하게 제일 먼저 사용이 가능한 쪽에 붙여나가고, 정 없으면 새로 파는 식으로 하면 된다. PQ를 쓰면 time O(nlogn), space O(n)에 가능하다. 아니면 start, end를 각각 정렬한 후, 현재 end보다 큰 다음 start를 찾을때까지 room++ 하고 (겹치니까) 그 다음에 end가 한 칸 갈때마다 room-- 해서 비워주면 (겹친 거 빼주면) 맨 마지막에 room이 나온다.
*/
class Solution {
    public int minMeetingRooms(int[][] intervals) {
        Arrays.sort(intervals, (a,b)->Integer.compare(a[0], b[0]));
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int[] i : intervals) {
            if (pq.size() > 0 && pq.peek() <= i[0]) pq.poll();
            pq.offer(i[1]);
        }
        return pq.size();
    }
}
class Solution {
    public int minMeetingRooms(int[][] intervals) {
        int[] left = new int[intervals.length];
        int[] right = new int[intervals.length];
        for (int i = 0; i < intervals.length; i++) {
            left[i] = intervals[i][0];
            right[i] = intervals[i][1];
        }
        Arrays.sort(left);
        Arrays.sort(right);
        int cnt = 0;
        for (int l = 0, r = 0; l < intervals.length; l++, cnt++) {
            if (left[l] >= right[r]) {
                cnt--;
                r++;
            }
        }
        return cnt;
    }
}