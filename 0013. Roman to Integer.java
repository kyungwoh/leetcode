/*
13. Roman to Integer
방금 전의 대칭 문제다. 2개 문제를 콤보로 낼 수 있다. 마찬가지로 모든 경우를 적어보고 규칙을 찾아낸다.

푸는 방법은 여러 가지가 있는데, IX(9), IV(4) 등의 경우를 잘 다루면 된다. I가 V, X 앞에 올 경우 빼주는 경우다.
이것을 “IV”, “IX” 문자열을 찾을 수도 있고, IX인지 XI인지를 인접한 두개를 비교하는 방법도 있고,
아니면 I를 지났을 경우 metI = true로 표시해서 X를 지날 때 metI 이면 -2 해주는 방법도 있다.

이걸 반대로 뒤에서부터 해나갈 수도 있다.
*/
class Solution {
    public int romanToInt(String s) {
        int sum = 0;
        boolean metI = false, metX = false, metC = false;
        for (char c : s.toCharArray()) {
            switch(c) {
                case 'M':
                    sum += 1000;
                    if (metC) sum -= 200;
                    break;
                case 'D':
                    sum += 500;
                    if (metC) sum -= 200;
                    break;
                case 'C':
                    sum += 100;
                    metC = true;
                    if (metX) sum -= 20;
                    break;
                case 'L':
                    sum += 50;
                    if (metX) sum -= 20;
                    break;
                case 'X':
                    sum += 10;
                    metX = true;
                    if (metI) sum -= 2;
                    break;
                case 'V':
                    sum += 5;
                    if (metI) sum -= 2;
                    break;
                case 'I':
                    sum += 1;
                    metI = true;
                default:
                    break;
            }
        }
        return sum;
    }
}
class Solution {
    public int romanToInt(String s) {
        Map<Character,Integer> map = new HashMap<>();
        map.put('I',1);
        map.put('V',5);
        map.put('X',10);
        map.put('L',50);
        map.put('C',100);
        map.put('D',500);
        map.put('M',1000);
        int sum = 0, prev = 5000;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int j = map.get(c);
            sum += j;
            if (prev < j) sum -= prev * 2;
            prev = j;
        }
        return sum;
    }
}
