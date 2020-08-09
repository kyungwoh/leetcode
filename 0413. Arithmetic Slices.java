/*
413. Arithmetic Slices

다행히도 consecutive seq이기 때문에 for문을 쭉 돌면 된다.
2-pointers로 (l, r) 가장 긴 것을 찾고, 아니면 거기서부터 다시 새 걸 찾아간다.

그러면 긴 것만 찾으면 끝인가? 아니다. 긴 것 안에서도 조합이 있을 수 있다.
다행히도 긴 것의 길이에서 combination의 갯수를 gauss formula로 한 번에 찾을 수 있다.
time O(n), space O(1)
*/
class Solution {
    public int numberOfArithmeticSlices(int[] A) {
        int cnt = 0;
        for (int l = 0, r = 1; r < A.length;) {
            while (r+1 < A.length && diff(A, r-1, r) == diff(A, r, r+1)) r++;
            if (r-l >= 2) cnt += (r-l-1)*(r-l)/2;
            l = r;
            r++;
        }
        return cnt;
    }
    int diff(int[] A, int i, int j) {
        return A[i] - A[j];
    }
}

/*
3 = 1
4 = 1+2
5 = 1+2+3
6 = 1+2+3+4 = (n-2)(n-1)/2
*/