/*
4. Median of Two Sorted Arrays
결국엔 binary search인데 복잡하다. nums1에서 l,m,r을 움직이면 num2의 m도 계산이 되도록 한다. m1 + m2 = half가 될테니, m2 = half - m1 일 것이다. 그러므로 m1만 찾아도 m2도 찾게 된다. 이때 nums1 < nums2로 만들면 m2 = half - m1이 음수가 안 되므로 edge case가 적어서 계산하기 편리하다. half는 홀수일 때 왼쪽 = 오른쪽+1 이 되도록 len1 + (len2-len1+1)/2 해서 만든다. 짝수일때 어차피 1/2=0이 되므로 왼쪽=오른쪽이 된다.
그래서 target이 고정이 아니라 nums1[m1-1] <= nums2[m2], nums1[m1] >= nums2[m2-1] 인 것이 만족이 안 되는 조건을 찾아서 l,r을 증가하거나 감소시킨다. 이때 l을 더 이상 증가시킬 수 없거나 r을 감소시킬 수 없다면 찾은 거다. 이 조건을 먼저 넣어서 [m1-1] [m2-1] 여기서 index of out bound가 나지 않도록 방지한다.
찾은 다음에는 median을 구해야 하는데, 이것이 nums1에 있을수도 있고 nums2에 있을수도 있다. 이것은 m1-1, m1, m2-1, m2가 array 범위 내에 있는지 [0, len] 확인하면 된다. r= len부터 시작했기 때문에 r==len이 될 수 있다. 홀수라면 왼쪽 끝, 짝수라면 왼쪽 끝 + 오른쪽 끝 / 2 로 찾으면 된다.
*/
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int len1 = nums1.length, len2 = nums2.length;
        if (len1 > len2) return findMedianSortedArrays(nums2, nums1);
        
        for(int l = 0, r = len1, half = len1 + (len2 - len1 + 1)/2; l <= r;) {
            int m1 = l + (r-l)/2;
            int m2 = half - m1;
            if (m1 > l && nums1[m1-1] > nums2[m2]) r = m1 - 1;
            else if (m1 < r && nums1[m1] < nums2[m2-1]) l = m1 + 1;
            else {
                int left = m1 == 0 ? nums2[m2-1] : (m2 == 0 ? nums1[m1-1] : Math.max(nums1[m1-1], nums2[m2-1]));
                if ((len1 + len2) %2 == 1) return left;
                int right = m1 == len1 ? nums2[m2] : (m2 == len2 ? nums1[m1] : Math.min(nums1[m1], nums2[m2]));
                return left + (right-left)/2.0;
            }
        }
        return 0;
    }
}