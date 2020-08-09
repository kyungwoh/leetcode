/*
334. Increasing Triplet Subsequence
s1 < s2 < s3 인 것을 찾아나간다. 현재 것이 s2보다 크면 s3가 있는 것이고, s1보다 크면 s2가 있는 것이다. 그도 아니면 s1이 있는 것이다.
큰 것부터 차례차례 찾아나가면 s1, s2, s3가 항상 크기 순으로 들어갈 것이다. 그러면 항상 s1, s2가 최소값이 되어서 뒤에 어떤 s3가 와도 받을 수 있다.
*/

class Solution {
    public boolean increasingTriplet(int[] nums) {
        int s1 = Integer.MAX_VALUE, s2 = Integer.MAX_VALUE;
        for (int n : nums) {
            if (n > s2) return true;
            else if (n > s1) s2 = n;
            else s1 = n;
        }
        return false;
    }
}