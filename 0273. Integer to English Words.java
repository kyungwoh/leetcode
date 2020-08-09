/*
273. Integer to English Words
잘 생각해보면 Integer라면 2 billion까지니까 웬만큼 hard coding으로 커버가 된다. 겁먹지 말고 찬찬히 따라가보면 된다. Billion, Million, Thousand 가 있고 그 밑에 Hundred, 그 밑에 Twenty, Thirty…. 그 밑에 One, Two, …. Nineteen이 있다. 이렇게 큰 것부터 작은 것 순으로 겹치지 않게 내려가면 빠지는 경우가 안 생긴다. Zero를 까먹으면 안된다. Static String[] 이용하면 편리하다. 그리고 문자열 자체에 space를 포함시키는게 편하고, 이걸 다 처리하기보다는 무조건 붙인 다음에 trim()하는게 낫다. 어차피 맨 마지막에만 스페이스가 들어갈 거라 그렇게 느리지 않고, 정 필요하다면 뒤에서부터 loop 돌면 된다.
*/
class Solution {
    static String[] s1 = new String[]{"", "One ", "Two ", "Three ", "Four ", "Five ", "Six ", "Seven ", "Eight ", "Nine ", "Ten ", "Eleven ", "Twelve ", "Thirteen ", "Fourteen ", "Fifteen ", "Sixteen ", "Seventeen ", "Eighteen ", "Nineteen "};
    static String[] s2 = new String[]{"", "", "Twenty ", "Thirty ", "Forty ", "Fifty ", "Sixty ", "Seventy ", "Eighty ", "Ninety "};
    public String numberToWords(int num) {
        if (num == 0) return "Zero";
        
        StringBuilder sb = new StringBuilder();
        while(num > 0) {
            if (num >= 1_000_000_000) {
                digit2(num / 1_000_000_000, sb);
                sb.append("Billion ");
                num %= 1_000_000_000;
            } else if (num >= 1_000_000) {
                digit3(num / 1_000_000, sb);
                sb.append("Million ");
                num %= 1_000_000;
            } else if (num >= 1_000) {
                digit3(num / 1_000, sb);
                sb.append("Thousand ");
                num %= 1_000;
            } else {
                digit3(num, sb);
                break;
            }
        }
        return sb.toString().trim();
    }
    void digit3(int num, StringBuilder sb) {
        if (num >= 100) {
            digit2(num / 100, sb);
            sb.append("Hundred ");
            num %= 100;
        }
        digit2(num, sb);
    }
    void digit2(int num, StringBuilder sb) {
        if (num >= 20) {
            sb.append(s2[num/10]);
            num %= 10;
        }
        sb.append(s1[num]);
    }
}