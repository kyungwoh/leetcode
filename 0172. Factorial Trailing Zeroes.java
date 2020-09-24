/*
172. Factorial Trailing Zeroes
끝자리의 0 갯수를 구하는 거니까 10*10*10... 의 갯수를 구하면 된다.
처음 등장하는게 5! 인데, 5*2=10 이니까 그렇다. 여기서 2는 짝수만 있으면 되니까 충분할 거고, 5만 세면 된다.
그러면 5, 10, 15, 20, 25... 여기서 25=5*5니까 두번 세야 하고, 125=5*5*5니까 3번 세야 한다.
그럼 25/5=5, 5/5=1 이니까 계속 나눠가서 5+1=6 이렇게 더하면 된다.

5로 나눠가니까 Time O(logn)
*/
class Solution {
    public int trailingZeroes(int n) {
        int cnt = 0;
        while (n > 0) {
            n /= 5;
            cnt += n;
        }
        return cnt;
    }
}