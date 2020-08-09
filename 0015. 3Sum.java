/*
15. 3Sum
two sum에서 loop를 한번 바깥으로 더 도는 식으로 하면 되는데, 문제는 중복 체크다.
Set, Map으로 풀 경우 Pair<Integer, Integer> 등의 클래스로 중복 체크를 해줘야 한다.

아니면 일단 정렬해놓고 풀면 중복 케이스를 피해갈 수 있다. 그래도 결국 O(n^2)를 돌아야 하기 때문에 O(nlogn)은 감수할 만 하다.
Int i를 고정시키고, 그 뒤로 Two pointer를 돌린다. 그 이유는 i의 앞의 것은 앞의 루프에서 찾았던 것과 같은 것이기 때문이다.
그리고 i도 sum보다 큰 것은 돌지 않는데 이것도 그 뒤가 앞과 대칭이기 때문이다.

근데 여기서 array에 같은 값이 들어갈 경우 더 중복 체크를 해줘야 한다.
정렬 했을때 nums[i-1] == nums[i] 인 경우 건너 뛰고, two pointer 할 때도 nums[r] == nums[r+1] r--; nums[l] == nums[l-1] l++;
이렇게 아까 같은 값이었으면 계속 건너뛴다.
그리고 값을 찾았을때도 멈추지 말고 l++; r--; 해서 더 찾아봐야 한다. 다른 답이 더 안 쪽에 있을 수 있기 때문이다.
*/
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) break;
            if (i > 0 && nums[i-1] == nums[i]) continue;
            
            for (int l = i+1, r = nums.length - 1; l < r;) {
                int sum = nums[i] + nums[l] + nums[r];
                if (sum > 0 || (r < nums.length - 1 && nums[r] == nums[r+1])) r--;
                else if (sum < 0 || (l > i+1 && nums[l] == nums[l-1])) l++;
                else ans.add(List.of(nums[i], nums[l++], nums[r--]));
            }
        }
        return ans;
    }
}