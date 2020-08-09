/*
937. Reorder Data in Log Files
sort할때 lambda 쓰면 편하다. Arrays.sort(arr, (a,b) -> a.compareTo(b)); collection.sort((a,b) -> a.compareTo(b)); Character.isLetter() isDigit() isLetterOrDigit() 그리고 까다로운 조건들이 많은데, id가 빈 게 있는지, value가 빈 게 있는지, 정렬할때 value로 하는데 같으면 어떡할지를 집요하게 물어봐야 한다. array의 string을 그대로 리턴하면 되므로 바로 Arrays.sort()로 풀어도 되는데, 그러면 매번 string manipulation을 해야 하므로 느려진다. max(string length)만큼 느려진다. 그걸 미리 잘라서 별도의 List로 만들면 매번 안 해도 되므로 조금 더 빠른데, 대신 space를 더 잡아먹고 코드가 길어진다. 어떻게 풀지를 미리 물어보고 시작한다. string.substring(시작점, 끝점+1)이고 끝까지는 (시작점, length())이다. delimiter(' ')을 포함할지 말지를 결정해야 하는데, 어차피 다시 합쳐야 하므로 냅두는게 낫다.
*/
class Solution {
    public String[] reorderLogFiles(String[] logs) {
        Arrays.sort(logs, (l1, l2) -> {
            String[] s1 = l1.split(" ", 2);
            String[] s2 = l2.split(" ", 2);
            boolean isLetter1 = Character.isLetter(s1[1].charAt(0));
            boolean isLetter2 = Character.isLetter(s2[1].charAt(0));
            if (isLetter1 && isLetter2) {
                int comp = s1[1].compareTo(s2[1]);
                return comp == 0 ? s1[0].compareTo(s2[0]) : comp;
            }
            return isLetter1 ? -1 : (isLetter2 ? 1 : 0);
        });
        return logs;
    }
}