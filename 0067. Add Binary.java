/*
67. Add Binary
String input이라 int, long의 범위를 넘어갈 수 있으므로 String인채로 풀어야 한다.
각 자리마다 돌면서 carry를 끌고 가면 된다.
index를 뒤에서부터 세는게 헷갈리면 그냥 reverse()한 다음에 하는 것도 좋다. time O(n+m)에 영향 없음. space는 영향 있지만.
*/
class Solution {
    public String addBinary(String a, String b) {
        StringBuilder sb = new StringBuilder();
        for (int i = a.length() - 1, j = b.length() - 1, c = 0; i >= 0 || j >= 0 || c > 0; i--, j--) {
            int k = i >= 0 ? (a.charAt(i) - '0') : 0;
            int l = j >= 0 ? (b.charAt(j) - '0') : 0;
            int sum = k + l + c;
            sb.append(sum%2);
            c = sum/2;
        }
        return sb.reverse().toString();
    }
}
import java.math.BigInteger;

class Solution {
    public String addBinary(String a, String b) {
        BigInteger i = new BigInteger(a, 2);
        BigInteger j = new BigInteger(b, 2);
        while (!j.equals(BigInteger.ZERO)) {
            BigInteger sumWithoutCarry = i.xor(j);
            BigInteger carry = i.and(j).shiftLeft(1);
            i = sumWithoutCarry;
            j = carry;
        }
        return i.toString(2);
    }
}

class Solution:
    def addBinary(self, a, b):
        """
        :type a: str
        :type b: str
        :rtype: str
        """
        ar = a[::-1]
        br = b[::-1]
        cr, i, c = "", 0, 0
        while i<len(ar) or i<len(br):
            aa = 0 if i>=len(ar) else int(ar[i])
            bb = 0 if i>=len(br) else int(br[i])
            c += aa + bb
            cr += str(c%2)
            c //= 2
            i += 1
        while c>0:
            cr += str(c%2)
            c //= 2
        return cr[::-1]
                