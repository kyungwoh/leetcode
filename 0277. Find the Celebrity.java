/*
277. Find the Celebrity
indegree == n-1, outdegree == 0 인걸 찾는 거니까 Time O(n^2), Space O(n)

그런데 생각해보면 in, out을 다 셀 필요가 없이, 각 vertex마다 in이 하나라도 없는지, out이 하나라도 있는지 체크하면 됨.
이러면 중간에 break할 수 있으니까 Time O(n^2)은 같지만 좀 더 빠르고, Space O(1)으로 줄어든다.

조금 더 나아가먄,
i --> j 가 없다 == i는 답일 수 있는데, j는 답일 수 없다. 그러므로 j는 건너뛴다.
i --> k 가 있다 == i는 답일 수 없으니 건너뛰고, k가 답일 수 있으니 k부터 다시 시작한다.
이렇게 0부터 끝까지 순서대로 건너뛰면 맨 마지막에 답일 수 있는 후보가 남을 것이다.
이 후보가 정말 답인지 in, out을 한번 쭉 훑어서, 맞으면 그걸 return하고 아니면 답이 없는 거다. Time O(n), Space O(1)

그럼 이렇게 한 방향으로만 훑는게 맞냐? 반대 방향은 볼 필요가 없나? 그럴 필요가 없다.
워낙 조건이 극단적이라 하나만 삐끗해도 아니기 때문에, 한쪽만 이상해도 반대쪽은 볼 필요가 없다.
이 조건을 만족하는게 전체에서 딱 하나밖에 안되기 때문에, 후보가 여러개인지 볼 필요도 없다. 그냥 하나만 들고 다니면 된다.
*/
public class Solution extends Relation {
    public int findCelebrity(int n) {
        int[] in = new int[n];
        int[] out = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) continue;
                if (knows(i, j)) {
                    in[j]++;
                    out[i]++;
                }
            }
        }
        for (int i = 0; i < n; i++) {
            if (in[i] == n-1 && out[i] == 0) return i;
        }
        return -1;
    }
}
public class Solution extends Relation {
    public int findCelebrity(int n) {
        for (int i = 0; i < n; i++) {
            boolean valid = true;
            for (int j = 0; j < n; j++) {
                if (i == j) continue;
                if (knows(i, j) || !knows(j, i)) {
                    valid = false;
                    break;
                }
            }
            if (valid) return i;
        }
        return -1;
    }
}
public class Solution extends Relation {
    public int findCelebrity(int n) {
        int candidate = 0;
        for (int i = 0; i < n; i++) {
            if (candidate == i) continue;
            if (knows(candidate, i)) candidate = i;
        }
        for (int i = 0; i < n; i++) {
            if (candidate == i) continue;
            if (knows(candidate, i) || !knows(i, candidate)) return -1;
        }
        return candidate;
    }
}