/*
347. Top K Frequent Elements
quick select을 이용해서 Kth smallest를 구한다. 먼저 num별로 cnt를 구한 뒤, unique num들만으로 array를 만들고 그걸 quick select한다. 이때 비교하는 값을 모두 cnt.get(key[i])로 해서 count로 비교를 해야 한다. pivot을 맨 뒤로 빼서 그 앞까지 전체를 swap한 다음에 다시 pivot을 원래 자리로 돌려놓는다. 그리고 k == p 면 찾은거고, k < p 면 left, 아니면 right로 간다. 아니면 위와 마찬가지로 PQ를 써서 min heap으로 작은 것들을 poll() 해가면 맨 마지막에 제일 큰 것만 남을 것이다. 마찬가지로 cnt.get(i) 해서 정렬한다.
*/
class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int n : nums) cnt.put(n, cnt.getOrDefault(n, 0) + 1);
        
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b)->Integer.compare(cnt.get(a), cnt.get(b)));
        for (Integer n : cnt.keySet()) {
            pq.offer(n);
            if (pq.size() > k) pq.poll();
        }
        
        int[] ans = new int[k];
        for (int i = 0; i < k; i++) {
            ans[i] = pq.poll();
        }
        return ans;
    }
}
class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int n : nums) cnt.put(n, cnt.getOrDefault(n, 0) + 1);

        int[] key = new int[cnt.size()];
        int i = 0;
        for (int c : cnt.keySet()) key[i++] = c;
        
        quick(0, key.length - 1, key.length - k, cnt, key);
        return Arrays.copyOfRange(key, key.length - k, key.length);
    }
    void quick(int l, int r, int k, Map<Integer, Integer> cnt, int[] key) {
        if (l == r) return;
        
        int m = l + (r-l)/2;
        int pivot = cnt.get(key[m]);
        swap(key, m, r);
        int p = l;
        for (int i = l; i <= r; i++) {
            if (cnt.get(key[i]) < pivot) swap(key, i, p++);
        }
        swap(key, p, r);
        
        if (k == p) return;
        else if (k < p) quick(l, p-1, k, cnt, key);
        else quick(p+1, r, k, cnt, key);
    }
    void swap(int[] key, int i, int j) {
        int temp = key[i];
        key[i] = key[j];
        key[j] = temp;
    }
}