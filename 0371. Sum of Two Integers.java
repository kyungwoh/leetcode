/*
371. Sum of Two Integers
+, - 가 안된다면 가능한게 bitwise operator밖에 없다. AND(&) OR(|) XOR(^) SHIFT(>>, <<) NOT(~)
여기서 XOR(^)는 carry 없는 add와 같으므로, 이걸 이용하면 된다.
그럼 carry는 어떻게 구하나? carry는 둘 다 1인 것이므로, AND(&)하면 나온다.

그러면 carry 없이 add한 것과 carry를 << 1 한 걸 더하면 되지 않나? 근데 add를 하면 안된다.
그러면 recursive하게 다시 둘을 XOR로 더한 다음에 또 carry해주고... 그렇게 carry가 0일때까지 반복한다.

문제는 음수일 경우인데, signed int에서는 음수를 complement로 하니까, bitwise 연산도 그대로 동작한다.
그러므로 별도로 감안할 필요 없이 그대로 쓰면 된다.

감안을 해야 한다면 먼저 절대값으로 바꿔준 다음에, 두 숫자의 부호가 같을 경우와 다를 경우로 분리한다.
부호가 같으면 양수 하던대로 계산한 다음에 마지막에 양수/음수 부호만 붙여주면 된다.
부호가 다르면 앞의 값을 ~x 해서 뒤집어서 carry 해나간다.
*/
class Solution {
    public int getSum(int a, int b) {
        while (b != 0) {
            int answer = a ^ b;
            int carry = (a & b) << 1;
            a = answer;
            b = carry;
        }
        return a;
    }
}
class Solution {
    public int getSum(int a, int b) {
        int x = Math.abs(a), y = Math.abs(b);
        if (x < y) return getSum(b, a);
        
        int sign = a > 0 ? 1 : -1;
        if (a * b >= 0) {
            while (y != 0) {
                int answer = x ^ y;
                int carry = (x & y) << 1;
                x = answer;
                y = carry;
            }
        } else {
            while (y != 0) {
                int answer = x ^ y;
                int borrow = ((~x) & y) << 1;
                x = answer;
                y = borrow;
            }
        }
        return x * sign;
    }
}