/*
69. Sqrt(x)
binary search만 해도 O(logn)으로 줄일 수 있다.
그냥 일치할때 m을 return하면 되지만 아닐때가 tricky하다.
l은 x의 insertion point이기 때문에, 그것보다 하나 작은 l-1을 return해야 한다.
*/
class Solution {
    public int mySqrt(int x) {
        int i = 0, l = 0, r = x;
        while (l <= r) {
            int m = (l+r)>>>1;
            long curr = (long)m*m;
            if (curr > x) r = m-1;
            else if (curr < x) l = m+1;
            else return m;
        }
        return l-1;
    }
}