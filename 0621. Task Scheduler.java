/*
621. Task Scheduler
brute force로 푼다면, stall을 최대 n개까지 넣어봐서 모든 경우를 돌면 된다.
그런데 잘 생각해보면 pattern이 있다. 일단 같은 것들끼리 count한 다음에, 제일 많은 걸 n만큼 떨어트려 놓는다.
여기서 char가 무엇인지는 중요하지 않다. 오로지 count만 보면 된다.


그러면 그 사이의 stall은 총 (maxCnt - 1) * n 이다. 이것은 더 이상 줄일 수 없는 minimum이다.
그런데 그 사이의 공간에 다른 것들을 집어넣을 수 있다. 그러면 그만큼 stall이 줄어들어서 짧아질 것이다.

근데 그걸 그냥 count만큼 빼는게 아니라, maxCnt인 경우에는 maxCnt - 1로 줄인다.
왜냐하면 maxCnt인 경우, 즉 최대값과 같은 경우 나머지는 맨 뒤에 붙기 때문에 중간 stall을 줄이지 못한다.
그래서 하나를 빼줘야 한다.

물론 이 값은 0보다는 크거나 같아야 하니까 이를 감안한다.
그리고 이 stall값은 중간에 빈 칸이니까, 전체 length + stall 해야 전체 길이가 나온다.
*/
class Solution {
    public int leastInterval(char[] tasks, int n) {
        Map<Character, Integer> map = new HashMap<>();
        for (char t : tasks) map.put(t, map.getOrDefault(t, 0) + 1);
        
        List<Integer> list = new ArrayList<>(map.values());
        list.sort((a,b) -> Integer.compare(b,a));
        
        int maxCnt = list.get(0);
        int idle = (maxCnt - 1) * n;
        for (int i = 1; i < list.size() && idle > 0; i++) {
            idle -= Math.min(maxCnt - 1, list.get(i));
        }
        idle = Math.max(0, idle);
        return idle + tasks.length;
    }
}