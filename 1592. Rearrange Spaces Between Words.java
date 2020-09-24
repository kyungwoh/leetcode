/*
1592. Rearrange Spaces Between Words
어떻게 해도 깔끔하게 코드가 안 나온다. Time O(n), Space O(n) 도 이보다 좋을수 없고, 이보다 나빠지기도 어렵다.
lambda 펑션, split(regex, String functions들을 알면 편리하겠지만, 기억이 안나면 직접 짜야 한다.
그리고 세부적인 디테일이 많아서 실수하기 쉬우니 조심해야 한다.

1. 맨 처음에 space가 나올 경우도 있다. 맨 끝에 space가 나올 때도 있다. 전부 space만 있을수도 있고, space가 없을수도 있다.
2. space가 연속하여 여러 개 나올 수 있기 때문에 .split(" ") 이렇게 하면 안된다.
3. word가 끝날때마다 list.add()하면, 맨 마지막에 빠트릴 수 있으므로 for loop 끝나고 한번 더 확인해서 넣어준다.
4. (words.size() - 1) 로 나눌때 divided by zero가 될 수 있으므로 이를 감안한다.
5. 맨 마지막에 남는 space들도 붙여줘야 한다.
*/
class Solution {
    public String reorderSpaces(String text) {
        int spaceCnt = 0;
        boolean wasWord = false;
        StringBuilder sb = new StringBuilder();
        List<String> words = new ArrayList<>();
        for (char c : text.toCharArray()) {
            if (c == ' ') {
                spaceCnt++;
                if (wasWord) {
                    words.add(sb.toString());
                    sb.setLength(0);
                }
                wasWord = false;
            } else {
                sb.append(c);
                wasWord = true;
            }
        }
        if (sb.length() > 0) words.add(sb.toString());
        if (spaceCnt == 0) return text;
        
        int spaceEach = words.size() <= 1 ? 0 : spaceCnt / (words.size() - 1);
        sb.setLength(0);
        StringBuilder spaces = new StringBuilder();
        for (int i = 0; i < spaceEach; i++) spaces.append(' ');
        for (String w : words) {
            if (sb.length() > 0) sb.append(spaces);
            sb.append(w);
        }
        int spaceRemaining = spaceCnt - spaceEach * (words.size() - 1);
        for (int i = 0; i < spaceRemaining; i++) sb.append(' ');
        
        return sb.toString();
    }
}
class Solution {
    public String reorderSpaces(String text) {
        int wordCnt = 0, blankCnt = 0;
        boolean wordStart = false;
        for (int i = 0; i < text.length();) {
            if (text.charAt(i) == ' ') {
                wordStart = false;
                while (i < text.length() && text.charAt(i) == ' ') { i++; blankCnt++; }
            } else {
                i++;
                if (!wordStart) {
                    wordStart = true;
                    wordCnt++;
                }
            }
        }
        if (blankCnt == 0 || wordCnt == 0) return text;
        int blankEach = wordCnt == 1 ? 0 : blankCnt / (wordCnt - 1);
        StringBuilder sb = new StringBuilder();
        wordStart = false;
        for (int i = 0; i < text.length();) {
            if (text.charAt(i) == ' ') {
                if (wordStart) {
                    for (int j = 0; j < blankEach && blankCnt > 0; j++) {
                        sb.append(' ');
                        blankCnt--;
                    }
                }
                while (i < text.length() && text.charAt(i) == ' ') i++;
            } else {
                wordStart = true;
                sb.append(text.charAt(i++));
            }
        }
        for (int i = 0; i < blankCnt; i++) sb.append(' ');
        return sb.toString();
    }
}