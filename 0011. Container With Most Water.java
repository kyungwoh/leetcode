/*
11. Container With Most Water
Two pointer 또는 (height, index)를 height로 정렬하여 풀 수 있다.
Two pointer는 leftmost, rightmost에서 시작해서 둘의 height를 비교해서 짧은 걸 가운데로 옮긴다.
왜냐하면 긴 걸 움직여봤자 짧은 height에 제한되고, 짧은 걸 움직이면 긴 height에 제한되니까,
둘 중에 짧은 걸 움직이는 편이 항상 낫다. 그렇게 항상 나은 쪽으로 움직이면서 최대값을 찾아나간다.
반면에 (height, index)를 height가 큰 순으로 정렬해 찾는 방법은 더 복잡하다.
이번에는 height가 큰 것부터 작은 걸로 가면서, 그 인덱스들의 가장 작은 값(left)과 가장 큰 값(right)을 찾아나간다.
그렇게 (r - l) * height로 최대 면적을 찾아나간다. (주의) 가로가 (r-l+1)이 아니라 (r-l)이다.
*/
class Solution {
    public int maxArea(int[] height) {
        int l = 0, r = height.length - 1, max = 0;
        while (l < r) {
            int area = (r - l) * Math.min(height[l], height[r]);
            max = Math.max(max, area);
            if (height[l] < height[r]) l++;
            else r--;
        }
        return max;
    }
}