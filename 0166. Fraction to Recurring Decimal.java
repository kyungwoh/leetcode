/*
166. Fraction to Recurring Decimal
겁먹지 말고, 수학 시간에 하던 것처럼 long division으로 차근차근 풀면 된다. Time, Space 생각하지 말고.
1/0, 0/1 같은 edge case 생각하고, 양수 음수 생각해서
답이 0보다 큰 경우까지 풀어놓는다. 그냥 i/j 하면 된다.

이제 어려운 건 0.xxx 이렇게 계속 가는 경우다. remainder를 붙들고 0이 될때까지 계속 밑으로 내려가야 한다.
그럼 무한히 반복되는 건 어떻게 찾을 것인가? remainder가 반복될 때다. 그래서 매번 remainder를 저장한다.
그럼 () 괄호는 어떻게 씌울 건가? index도 같이 저장해놔서 거기다가 insert한다. (느리긴 하겠지만)
*/
class Solution {
    public String fractionToDecimal(int i, int j) {
        if (i == 0) return "0";
        
        StringBuilder sb = new StringBuilder();
        if (i < 0 ^ j < 0) sb.append('-');
        
        long k = (long) i;
        if (k < 0) k = -k;
        long l = (long) j;
        if (l < 0) l = -l;
        
        sb.append(k / l);
        long r = k % l;
        if (r == 0) return sb.toString();
        sb.append('.');
        Map<Long, Integer> map = new HashMap<>();
        while (r > 0) {
            if (map.containsKey(r)) {
                sb.insert(map.get(r), "(");
                sb.append(')');
                break;
            }
            map.put(r, sb.length());
            r *= 10;
            sb.append(r / l);
            r %= l;
        }
        return sb.toString();
    }
}