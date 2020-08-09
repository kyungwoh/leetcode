/*
16. 3Sum Closest
3Sum과 비슷한데, 이번에는 sum을 정확히 구하는 것이 아니라 답이 없어도 제일 가까운 것을 구하는 것이라 위에서 제약조건들을 바꿔줘야 한다.
1. Nums[i] > target 이면 break를 했는데, 하지 말아야 한다. 답이 아닌 경우에도 돌아야 하기 때문이다.
2. 중복 체크는 해도 되고 안 해도 된다. 결과 리스트에 저장할 게 아니기 때문이다.
3. Two pointer가 동작하는 동안 계속 최소 sum을 체크해야 한다.
Target을 향해 가까워지는 것이긴 하지만, 플러스 마이너스를 왔다갔다 하면서 최종 값이 아니라 중간 값에서 답이 있을 수 있기 때문이다.
Target값과의 차이는 Math.abs()로 하는데 overflow 감안해야 한다.
물론 diff가 절대값인지 물어봐야 한다. 그렇다면 diff의 최대값은 int_max/2 일 것이다. 그러므로 diff를 넉넉히 int_max부터 시작해도 된다.
그게 Integer null부터 시작하는 것보다 코드가 짧고 빠르다.
*/
class Solution {
    public int threeSumClosest(int[] nums, int target) {
        Integer ans = null;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            for (int l = i+1, r = nums.length - 1; l < r;) {
                int sum = nums[i] + nums[l] + nums[r];
                
                if (ans == null) ans = sum;
                else if (Math.abs(target - sum) < Math.abs(target - ans)) ans = sum;
                
                if (sum > target) r--;
                else if (sum < target) l++;
                else return target;
            }
        }
        return ans;
    }
}