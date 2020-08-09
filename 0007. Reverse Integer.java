/*
7. Reverse Integer
decimal로 한 자리씩 shift 해가면서 현재 digit을 구해서 더해주면 된다. 모듈러 연산은 음수는 음수를 리턴하고 동일하게 동작한다. overflow, underflow는 long을 써서 계산 후에 MAX, MIN과 비교하거나, int를 써야 한다면 MAX, MIN의 10진수 마지막 자리를 구해서 MAX/10, MIN/10과 비교하고 == 일 경우 digit > 7 or digit < -8 인지를 체크한다.
*/
class Solution {
    public int reverse(int x) {
        long i = (long) x;
        long sum = 0;
        while (i != 0) {
            long digit = i % 10;
            i /= 10;
            sum = sum*10 + digit;
            if (sum > Integer.MAX_VALUE || sum < Integer.MIN_VALUE) return 0;
        }
        return (int) sum;
    }
}
class Solution {
    public int reverse(int x) {
        int i = x;
        int sum = 0;
        int MAX = Integer.MAX_VALUE / 10, MIN = Integer.MIN_VALUE / 10;
        while (i != 0) {
            int digit = i % 10;
            i /= 10;
            if (sum > MAX || sum < MIN ||
               (sum == MAX && digit > 7) || (sum == MIN && digit < -8)) return 0;
            sum = sum*10 + digit;
        }
        return (int) sum;
    }
}