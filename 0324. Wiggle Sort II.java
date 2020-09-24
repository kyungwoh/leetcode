/*
324. Wiggle Sort II
가장 쉬운 방법은 일단 sort한 다음에 홀수 인덱스에 쭉, 짝수 인덱스에 쭉 크기 순으로 넣는 것이다. Time O(nlogn)

그런데 생각해보면 굳이 sort를 할 필요가 없다. 전체를 반으로 잘라서 작은 쪽, 큰 쪽을 홀수 짝수 인덱스에 순서대로 넣으면 된다.
그러면 전체를 어떻게 반으로 가를 것인가? median을 구해서 median보다 작은지 큰지를 판단하면 된다.
median은 findKthLargest()에서 썼던 quickselect를 하면 Time avg O(n), worst O(n^2) 이다.

여기서 반을 가를 때 별도의 array를 쓰면 Space O(n)이니까, in-place로 하려면 매번 swap해야 한다.
median보다 크면 왼쪽과 swap, 작으면 오른쪽과 swap하면서 둘이 가운데서 만날때까지 진행한다.
근데 그냥 그렇게 하면 현재 array가 가운데 median을 기준으로 큰쪽 작은쪽이 갈리는 것 뿐이니까,
인덱스를 아예 처음부터 wiggle해준다. i -> (i*2 + 1) % (len|1) 하면 된다.
*/
class Solution {
    int len;
    public void wiggleSort(int[] nums) {
        len = nums.length;
        if (len < 2) return;
        int median = findKthLargest(nums, 0, len-1, (len+1)/2);
        for(int i = 0, l = 0, r = len - 1; i<=r;){
            if (nums[wig(i)] > median){
                swap(nums, wig(l++), wig(i++));
            } else if (nums[wig(i)] < median){
                swap(nums, wig(r--), wig(i));
            } else {
                i++;
            }
        }
    }
    int wig(int i){ return (1+2*i)%(len|1); }
    void swap(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    int findKthLargest(int[] nums, int s, int e, int k){
        if (s > e) return -1;
        
        int pivot = nums[e];
        int left = s;
        for (int i = s; i<e; i++) {
            if (nums[i]<=pivot) swap(nums, left++, i);
        }
        swap(nums, left, e);
        
        if (left == k) return nums[left];
        else if (left < k) return findKthLargest(nums, left+1, e, k);
        else return findKthLargest(nums, s, left-1, k);
    }
}