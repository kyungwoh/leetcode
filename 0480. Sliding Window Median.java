/*
480. Sliding Window Median
매번 가운데 값 가져오려면 -> 매번 소팅?
간단한 값 구하는 부분 -> 펑션으로

295. Find Median From Data Stream 의 two heaps 아이디어를 쓰면 된다.
그런데 이번엔 online으로 계속 넣고 빼야 하는데, 넣는 건 괜찮은데 빼는게 문제다. -> Lazy removal

뺄 때 뺀다치고 balance만 ++ --를 해놓자. 그러면 lo, hi heap 균형 맞추는 건 할 수 있다.
그러면 언제 빼나? 중요한 건 peek() 값들이다. 이걸로 median을 구하고, peek()인것만 poll()할 수 있다.

그러므로 balance를 맞춘 다음에, lo, hi peek() 값들이 remove 대상이면 그때가서 뺀다.
뺄 값들은 map에 담아놓는다. 중복값이 있을 수 있으므로 map<val, count>로 담아놓고, 뺄 때 감소시킨다.
*/
class Solution {
    
    public double[] medianSlidingWindow(int[] nums, int k) {
        PriorityQueue<Integer> lo = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> hi = new PriorityQueue<>();
        Map<Integer, Integer> rm = new HashMap<>();
        double[] result = new double[nums.length - k + 1];
        for (int i = 0; i < k; i++) lo.offer(nums[i]);
        for (int i = 0; i < k/2; i++) hi.offer(lo.poll());
        for (int i = 0, j = k; j <= nums.length; i++, j++) {
            result[i] = k % 2 == 1 ? lo.peek() : lo.peek() * 0.5 + hi.peek() * 0.5;
            if (j == nums.length) break;
            
            int out = nums[i], in = nums[j], b = 0;
            
            plus(rm, out);
            if (out <= lo.peek()) b--;
            else b++;
            
            if (!lo.isEmpty() && in <= lo.peek()) { b++; lo.offer(in); }
            else { b--; hi.offer(in); }
            
            if (b < 0) lo.offer(hi.poll());
            if (b > 0) hi.offer(lo.poll());
            
            while (minus(rm, lo.peek())) lo.poll();
            while (!hi.isEmpty() && minus(rm, hi.peek())) hi.poll();
        }
        return result;
    }
    void plus(Map<Integer, Integer> rm, int out) {
        int cnt = rm.getOrDefault(out, 0);
        rm.put(out, ++cnt);
    }
    boolean minus(Map<Integer, Integer> rm, int out) {
        int cnt = rm.getOrDefault(out, 0);
        if (cnt == 0) return false;
        rm.put(out, --cnt);
        return true;
    }
}