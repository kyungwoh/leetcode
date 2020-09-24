/*
149. Max Points on a Line
y = ax + b 에서 a,b가 같은 걸 찾으면 된다.

문제는 floating point error가 생기므로, int로 할 방법을 찾아야 한다. Java의 BigDecimal도 infinite decimal은 cover가 안된다.
방법은 기울기 a를 gcd로 나눠서 a1/a2 이렇게 저장하는 것이다. 그래서 "a1,a2" string을 key로 쓰면 이런 문제가 안 생긴다.
* gcd(a,0) == a, gcd(0,b) == b 라서 수직, 수평인 경우도 cover가 된다.
* 자기 자신도 있으니 1도 더해준다.
* 점이 중복인 경우도 count해줘야 한다.

그러면 b는 어떡할 것인가? b를 별도로 둬서 "a1,a2,b" 이렇게 해도 되지만 그보다 간단히 할 수 있다.
여러 점들 중에 하나를 잡고, 그 점과 기울기가 같은 걸 찾는다. 그러면 그것은 a만 같아도 b도 같은 셈이다. 그렇게 한 점씩 이동하며 찾으면 된다.
어차피 2점씩 찍어서 edge를 계산해야 하기 때문에 시간복잡도가 더 증가하지 않는다.

그리고 for문에서 i는 n 전체를 돌고, j는 n 전체를 돌아도 되지만 그냥 j=i+1부터 시작해도 된다.
왜냐하면 그 전 점들이 일치했으면 이미 그 앞에서 결과가 나왔을 것이기 때문이다. 물론 더 좋은건 pruning하는 것이지만 이렇게만 해도 조금은 빨라진다.
물론 이렇게 중복을 제거하건 pruning을 하건 시간복잡도가 더 줄어들진 않는다. O(n^2)

여기에 gcd O(log(a+b))를 추가해야 한다. 이것은 O(log(INT_MAX)) = O(1) 이지만 큰 값이긴 하다.

Map을 저장해야 하니 Space O(n), Time O(n^2)
*/
class Solution {
    public int maxPoints(int[][] points) {
        int totalMax = 0;
        for (int i = 0; i < points.length; i++) {
            Map<String, Integer> slopes = new HashMap<>();
            int maxSlope = 0, dup = 0;
            int x1 = points[i][0], y1 = points[i][1];
            for (int j = i+1; j < points.length; j++) {
                int x2 = points[j][0], y2 = points[j][1];
                int dx = x2 - x1, dy = y2 - y1;
                if (dx == 0 && dy == 0) {
                    dup++;
                    continue;
                }
                int gcd = gcd(dx, dy);
                String key = (dx / gcd) + "," + (dy / gcd);
                int count = slopes.getOrDefault(key, 0) + 1;
                slopes.put(key, count);
                maxSlope = Math.max(maxSlope, count);
            }
            totalMax = Math.max(totalMax, maxSlope + dup + 1);
        }
        return totalMax;
    }
    int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}