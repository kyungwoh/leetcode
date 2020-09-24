/*
378. Kth Smallest Element in a Sorted Matrix
각 줄마다 PQ에 넣어서 작은 값부터 빼나가고, 그럼 그 다음 값을 집어넣는다. Time O(n + klogn), Space O(max(n, k))
아니면 가장 작은 값과 가장 큰 값부터 점점 좁혀나간다. 매번 n^2만큼 세지만, binary search니까 가장 작은 값과 큰 값의 차이가 적을 경우 유리하다.
Time O(n^2 * log(max-min)), Space O(1)
*/
class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        int len = matrix.length;
        if (len == 0) return 0;
        
        PriorityQueue<Node> pq = new PriorityQueue<>((a,b) -> Integer.compare(a.val, b.val));
        for (int i = 0; i < len; i++) pq.offer(new Node(i, 0, matrix[i][0]));
        for (; k > 1; k--) {
            Node n = pq.poll();
            if (n.j < len - 1) pq.offer(new Node(n.i, n.j+1, matrix[n.i][n.j+1]));
        }
        return pq.poll().val;
    }
    class Node {
        int i, j, val;
        Node(int i, int j, int val) {
            this.i = i;
            this.j = j;
            this.val = val;
        }
    }
}
class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        int len = matrix.length;
        if (len == 0) return 0;
        
        int lo = matrix[0][0], hi = matrix[len-1][len-1];
        while (lo < hi) {
            int mid = (lo + hi) >>> 1;
            int i = 0, j = len - 1, cnt = 0;
            while (i < len) {
                while (j >= 0 && matrix[i][j] > mid) j--;
                cnt += j+1;
                i++;
            }
            if (k > cnt) lo = mid + 1;
            else hi = mid;
        }
        return lo;
    }
}