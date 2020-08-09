/*
278. First Bad Version

binarySearch 할때 l, r에 m을 포함해야 하는가? 오른쪽의 왼쪽 끝을 찾는 것이므로, r에 끝을 남겨놔야 한다.
그러면 while도 l<r로 바꿔야 한다.(무한 루프 방지) 종료하는지 확인하려면 prove by induction으로 2개인 경우를 해본다.

나이브 솔루션도 간단히 언급을 할 것. 너무 느리지도, 너무 빠르지도 않게 말할 것.
*/
/* The isBadVersion API is defined in the parent class VersionControl.
      boolean isBadVersion(int version); */

public class Solution extends VersionControl {
    public int firstBadVersion(int n) {
        int l = 1, r = n, m = 1;
        while (l < r) {
            m = l + (r - l) / 2;
            if (isBadVersion(m)) r = m;
            else l = m + 1;
        }
        return l;
    }
}