/*
973. K Closest Points to Origin
quick select로 Kth smallest를 구한다.  i^2 + j^2로 비교하면 된다. 먼저 (0, length-1)로 시작해서 pivot을 저장해놓고, swap(m,r); int p = l, i = l; i<=r 까지 i < pivot이면 swap(i, p++); swap(p,r); if (k==p) return; else if(k<p) left; else right; 아니면 단순히 정렬해도 된다.
*/
class Solution {
    public int[][] kClosest(int[][] points, int K) {
        Arrays.sort(points, (a,b) -> Integer.compare(a[0]*a[0] + a[1]*a[1], b[0]*b[0] + b[1]*b[1]));
        return Arrays.copyOfRange(points, 0, K);
    }
}
class Solution {
    int[][] points;
    int k;
    public int[][] kClosest(int[][] points, int K) {
        this.points = points;
        this.k = K;
        quick(0, points.length-1);
        return Arrays.copyOfRange(points, 0, k);
    }
    void quick(int l, int r) {
        if (l == r) return;
        
        int m = l + (r-l)/2;
        int[] pivot = points[m];
        swap(m, r);
        int p = l;
        for (int i = l; i <= r; i++) {
            if (compare(points[i], pivot) < 0) swap(i, p++);
        }
        swap(p, r);
        
        if (k == p) return;
        else if (k < p) quick(l, p-1);
        else quick(p+1, r);
    }
    void swap(int i, int j) {
        int[] temp = points[i];
        points[i] = points[j];
        points[j] = temp;
    }
    int compare(int[] i, int[] j) {
        return Integer.compare(i[0]*i[0] + i[1]*i[1], j[0]*j[0] + j[1]*j[1]);
    }
}