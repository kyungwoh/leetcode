/*
319. Bulb Switcher
그냥 규칙대로 toggle하면서 풀면 time = n + n/2 + n/3 + ... + n/n = O(nlogn)

그런데 생각해보면 각 숫자당 방문횟수가 even이면 꺼지고, odd면 켜지는 것이다.
prime number(소수)를 이용한 건가 싶은데, 이건 1,5=5 1,7=7 1,11=7 이렇게 짝수개만 만족.
그런데 일반 숫자들도 1,2,3,6=6 이렇게 짝수개인 것이 있으므로 안됨.

그럼 perfect square(제곱수)는? 9=1,3,9 4=1,2,4 이렇게 홀수가 되고 나머지는 다 짝수가 된다.

                                12 13 14 15 16 17 18
                             11
                          10
                        9                          9
                     8                       8
                  7                    7
               6                 6                 6
            5              5              5
         4           4           4           4
      3        3        3        3        3        3
   2     2     2     2     2     2     2     2     2
1  1  1  1  1  1  1  1  1  1  1  1  1  1  1  1  1  1
1  2  3  4  5  6  7  8  9 10 11 12 13 14 15 16 17 18
*/

class Solution {
    public int bulbSwitch(int n) {
        int i = 1;
        while (i*i <= n) i++;
        return i-1;
    }
}

class Solution {
    public int bulbSwitch(int n) {
        int cnt = 0;
        boolean[] visited = new boolean[n+1];
        for (int i = 1; i <= n; i++) {
            for (int j = i; j <= n; j+=i) {
                if (visited[j]) {
                    cnt--;
                    visited[j] = false;
                } else {
                    cnt++;
                    visited[j] = true;
                }
            }
        }
        return cnt;
    }
}