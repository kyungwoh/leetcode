/*
763. Partition Labels
range merge와 같은 문제인데, sort 없이 two-pass greedy로 풀 수 있다. 각 letter별 last index를 저장해놓고, end = max(last[i])를 쌓아나가다가 i == end 면 거기서 끊는다. 그러면 현재까지 나왔던 모든 letter들의 last가 모인 곳이기 때문이다. 거기서 start = i + 1 로 다시 시작한다. 그리고 맨 마지막 letter는 항상 last[i]와 같기 때문에, 마지막에도 무조건 끊긴다.
*/
class Solution {
    public List<Integer> partitionLabels(String s) {
        int[] min = new int[26], max = new int[26];
        Arrays.fill(min, s.length());
        Arrays.fill(max, -1);
        for (int i = 0; i < s.length(); i++) {
            int j = (s.charAt(i) - 'a');
            min[j] = Math.min(min[j], i);
            max[j] = Math.max(max[j], i);
        }
        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            if (max[i] != -1) {
                list.add(new int[]{min[i], max[i]});
            }
        }
        if (list.size() == 0) return Collections.emptyList();
        list.sort((a, b) -> Integer.compare(a[0], b[0]));
        List<Integer> ans = new ArrayList<>();
        int[] prev = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            int[] curr = list.get(i);
            if (prev[1] >= curr[0]) {
                prev[1] = Math.max(prev[1], curr[1]);
            } else {
                ans.add(prev[1] - prev[0] + 1);
                prev = curr;
            }
        }
        ans.add(prev[1] - prev[0] + 1);
        return ans;
    }
}
class Solution {
    public List<Integer> partitionLabels(String s) {
        int[] last = new int[26];
        for (int i = 0; i < s.length(); i++) {
            last[s.charAt(i) - 'a'] = i;
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = 0, start = 0, end = 0; i < s.length(); i++) {
            end = Math.max(end, last[s.charAt(i) - 'a']);
            if (i == end) {
                ans.add(end - start + 1);
                start = i + 1;
            }
        }
        return ans;
    }
}