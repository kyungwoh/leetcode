/*
819. Most Common Word
banned를 cnt = -1 로 할지, 아니면 별도의 set으로 가져갈지? 하나의 map으로 가져가면 코드가 조금 단순해지는데, map의 크기가 커져 조금 비효율적이 된다. 인터뷰어에게 물어볼 포인트가 된다. 제일 중요한 건 paragraph의 마지막이 아무런 delimiter가 없이 끝날 경우까지 다뤄야 하기 때문에, for문 끝난 다음에 한번 더 체크하거나, 아니면 for문을 <= length() 까지 돌아서 포함해야 한다.(이게 더 나은 것 같다) ++cnt 해서 map.put 다시 해야 한다. getOrDefault(, 0) 쓰면 편리함.
*/
class Solution {
    public String mostCommonWord(String paragraph, String[] banned) {
        Set<String> banset = new HashSet<>();
        for (String b : banned) banset.add(b);
        
        Map<String, Integer> map = new HashMap<>();
        int maxCnt = 0;
        String maxWord = null;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= paragraph.length(); i++) {
            Character c = i < paragraph.length() ? paragraph.charAt(i) : null;
            if (c != null && Character.isLetter(c)) {
                sb.append(Character.toLowerCase(c));
            } else if (sb.length() > 0) {
                String s = sb.toString();
                sb.setLength(0);
                
                if (banset.contains(s)) continue;
                
                int cnt = map.getOrDefault(s, 0);
                map.put(s, ++cnt);
                if (cnt > maxCnt) {
                    maxCnt = cnt;
                    maxWord = s;
                }
            }
        }
        return maxWord;
    }
}