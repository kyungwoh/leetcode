/*
8. String to Integer (atoi)
까다로운 조건이 많아서, 먼저 말로 조건들을 나열한 다음 푼다.
1. 맨 앞에 빈 칸은 건너뛴다.
2. +/- 기호가 나올 수 있다. 1번만 나와야 한다
 3. 그 다음부터 0~9 이외의 것이 나오면 무조건 0으로 리턴한다.
 4. Int_max보다 크면 int_max, int_min보다 작으면 int_min으로 제한한다.
    그러려면 매 자리마다 sum = sum * 10 + digit * sign 해가면서 확인해야 한다.
    
Edge case들이 많으므로 꼭 인터뷰어에게 하나하나 짚어가면서 물어봐야 한다.
+/- 기호가 한번만 나와야 하는지 등.

Overflow, underflow는 어떻게 처리할 건지? 간단히 long으로 처리할 수 있고, 아니라면 * 10 을 계속하는 로직이므로
int_max/10, int_min/10 값에 도달할 때 미리 확인한다.
이때 끝자리가 잘리므로, 끝 자리가 ...7 인지, -...8 인지를 체크해서 overflow인지, underflow인지를 확인한다.
if (sum > max10 || (sum == max10 && digit > 7)) overflow; if (sum < min10 || (sum == min10 && digit > 8)) underflow;
*/
class Solution {
    public int myAtoi(String str) {
        int ans = 0, sign = 1, i = 0;
        
        while (i < str.length() && str.charAt(i) == ' ') i++;
        
        if (i < str.length()) {
            if (str.charAt(i) == '-') { sign = -1; i++; }
            else if (str.charAt(i) == '+') { i++; }
        }
        
        long sum = 0, intmax = Integer.MAX_VALUE, intmin = Integer.MIN_VALUE;
        for (; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c < '0' || c > '9') break;
            long j = c - '0';
            sum = sum * 10 + j * sign;
            if (sum >= intmax) {
                sum = intmax;
                break;
            }
            if (sum <= intmin) {
                sum = intmin;
                break;
            }
        }
        return (int) sum;
    }
}