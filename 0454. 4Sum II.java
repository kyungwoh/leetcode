/*
454. 4Sum II
0018. 4Sum 의 확장

4Sum의 푸는 방법 2가지
1. 4Sum -> 3Sum -> 2Sum 재귀적으로 푸는 방법
2. (i+j) 저장 -> (k+l)로 찾기
이 중에서 1번은 안 된다. sort를 전제로 해야 빠르기 때문이다. 각기 다른 배열이기 때문이다.
다행인 점은 다른 배열이니까 index 중복 처리를 안 해도 되고, count만 하면 되니까 hash를 쓰면 빠르다. --> 2번
Time O(n^2) + O(n^2) = O(n^2)

여기서 좀 더 확장해서 kSum으로 하려면 (a+b+c...) (x+y+z...) 이렇게 반으로 쪼개서 똑같이 하면 된다.
(a+b+c...) 이것의 합을 구하는 것은 recursive하게 한다.
Time O(n^(k/2)) + O(n^(k/2)) = O(n^(k/2))
*/
class Solution {
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        int cnt = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int a : A) {
            for (int b : B) {
                map.put(a+b, map.getOrDefault(a+b, 0) + 1);
            }
        }
        for (int c : C) {
            for (int d : D) {
                cnt += map.getOrDefault(-c-d, 0);
            }
        }
        return cnt;
    }
}
class Solution {
    int[][] lists;
    int halfLen;
    Map<Integer, Integer> map;
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        lists = new int[][]{A,B,C,D};
        halfLen = lists.length / 2;
        map = new HashMap<>();
        
        setMap(0, 0); // 0 to halfLen
        return count(halfLen, 0); // halfLen + 1 to length
    }
    void setMap(int i, int sum) {
        if (i == halfLen) {
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        } else {
            for (int l : lists[i]) {
                setMap(i+1, sum + l);
            }
        }
    }
    int count(int i, int complement) {
        if (i == lists.length) {
            return map.getOrDefault(complement, 0);
        } else {
            int cnt = 0;
            for (int l : lists[i]) {
                cnt += count(i+1, complement - l);
            }
            return cnt;
        }
    }
}