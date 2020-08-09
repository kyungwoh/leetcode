/*
12. Integer to Roman
당황하지 말고 모든 경우를 적어본다. 우선순위가 있으니 큰 것부터 적어본다. 좀 귀찮더라도 꼼꼼히 적어 내려간다. 그 다음에 규칙을 찾는다. 잘 보면 900~ 400~ 90~ 40~ 구간들을 보면 빠짐없이 앞에서 걸러져서 뒤에는 작은 수만 남는다. 그래서 그냥 차례로 내려가도록 하면 된다. 조금 더 나아가, 경우의 수가 각 자리당 [1-3] [4] [5-8] [9] 일때밖에 없으므로, 1-9 까지 모두 hard code해도 상수 시간이 나온다.
*/
class Solution {
    public String intToRoman(int num) {
        String s = "";
        while (num > 0) {
            if (num >= 1000) { s += "M"; num -= 1000; }
            else if (num >= 900) { s += "CM"; num -= 900; }
            else if (num >= 500) { s += "D"; num -= 500; }
            else if (num >= 400) { s += "CD"; num -= 400; }
            else if (num >= 100) { s += "C"; num -= 100; }
            else if (num >= 90) {s += "XC"; num -= 90; }
            else if (num >= 50) { s += "L"; num -= 50; }
            else if (num >= 40) { s += "XL"; num -= 40; }
            else if (num >= 10) { s += "X"; num -= 10; }
            else if (num >= 9) { s += "IX"; num -= 9; }
            else if (num >= 5) { s += "V"; num -= 5; }
            else if (num >= 4) { s += "IV"; num -= 4; }
            else { s += "I"; num -= 1; }
        }
        return s;
    }
}