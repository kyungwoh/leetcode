/*
239. Sliding Window Maximum
max값만 필요하기 때문에 O(n)에 가능하다. DQ에 인덱스 값들을 넣어서, 인덱스와 값으로 비교하면서 필요한 값만 넣고 빼면 된다.
1. 지나간 걸 뺄때는 왼쪽에서부터, 인덱스로 지났는지를 판단
2. 오른쪽에 넣을때는 넣을 값보다 작은 중간 값들은 모두 지움 (필요 없음)
3. 그 다음에 오른쪽에 넣을 값을 넣음
4. 그러고 나면 왼쪽에는 그때까지 안 지워지고 버틴 가장 큰 값이 남아있을 것이다. 이것이 max값이다.
   이게 다음 turn에 1번에서 빠지면 그 다음으로 큰 값이 들어있을 것이다. 그래서 순차적으로 max값을 가져오면 된다.
일종의 sort인데 O(nlogn)이 아닌 이유는 add하거나 remove하거나 둘 중 하나만 하기 때문이다. 중간 위치로 바꾸는 게 없다.
비교해나가는 것도 맨 왼쪽 한번, 오른쪽에서 왼쪽으로 찾아가는데 아닌 건 remove하기 때문에 중복해서 찾지 않는다.
현재까지의 최대치를 순차적으로 남겨놓는 것이라 가능하다.
*/
class Solution {
  public int[] maxSlidingWindow(int[] nums, int k) {
    LinkedList<Integer> dq = new LinkedList<>();
    int[] ans = new int[nums.length - k + 1];
    for (int i = 0; i < nums.length; i++) {
      if (!dq.isEmpty() && dq.getFirst() == i - k) dq.removeFirst();
      while (!dq.isEmpty() && nums[i] > nums[dq.getLast()]) dq.removeLast();
      dq.addLast(i);
      if (i - k + 1 >= 0) ans[i - k + 1] = nums[dq.getFirst()];
    }
    return ans;
  }
}