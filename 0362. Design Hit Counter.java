/*
362. Design Hit Counter
hit()이던 getHits()던 timestamp가 granually increasing 하다면 Queue로 풀면 간단하다.
edge case를 실제 숫자 (300, 301) 가지고 <=인지 <인지를 판단한다.

만약 history를 남겨서 과거 것도 구해야 한다면 binarySearch로 lowerBound() upperBound()를 구해서 그 사이
*/
class HitCounter {
    Queue<Integer> q;

    /** Initialize your data structure here. */
    public HitCounter() {
        q = new LinkedList<>();
    }
    
    /** Record a hit.
        @param timestamp - The current timestamp (in seconds granularity). */
    public void hit(int timestamp) {
        remove(timestamp);
        q.offer(timestamp);
    }
    
    /** Return the number of hits in the past 5 minutes.
        @param timestamp - The current timestamp (in seconds granularity). */
    public int getHits(int timestamp) {
        remove(timestamp);
        return q.size();
    }
    
    private void remove(int timestamp) {
        while (!q.isEmpty() && q.peek() <= timestamp - 300) q.poll();
    }
}

/**
 * Your HitCounter object will be instantiated and called as such:
 * HitCounter obj = new HitCounter();
 * obj.hit(timestamp);
 * int param_2 = obj.getHits(timestamp);
 */

 class HitCounter {
    List<Integer> list;

    /** Initialize your data structure here. */
    public HitCounter() {
        list = new ArrayList<>();
    }
    
    /** Record a hit.
        @param timestamp - The current timestamp (in seconds granularity). */
    public void hit(int timestamp) {
        list.add(timestamp);
    }
    
    /** Return the number of hits in the past 5 minutes.
        @param timestamp - The current timestamp (in seconds granularity). */
    public int getHits(int timestamp) {
        if (list.size() == 0) return 0;
        
        int rightmost = upperBound(0, list.size()-1, timestamp);
        int leftmost = lowerBound(0, list.size()-1, timestamp - 299);
        return rightmost - leftmost + 1;
    }
    
    private int lowerBound(int l, int r, int t) {
        while (l <= r) {
            int m = (l + r) >>> 1;
            if (list.get(m) >= t) r = m - 1;
            else l = m + 1;
        }
        return l;
    }

    private int upperBound(int l, int r, int t) {
        while (l < r) {
            int m = (l + r) >>> 1;
            if (list.get(m) > t) r = m;
            else l = m + 1;
        }
        return l;
    }
    
    private int binarySearch(int l, int r, int t) {
        int m = l;
        while (l <= r) {
            m = (l + r) >>> 1;
            if (list.get(m) > t) r = m - 1;
            else if (list.get(m) < t) l = m + 1;
            else return m;
        }
        return l;
    }
}

/**
 * Your HitCounter object will be instantiated and called as such:
 * HitCounter obj = new HitCounter();
 * obj.hit(timestamp);
 * int param_2 = obj.getHits(timestamp);
 */