/* 268. Missing Number
n개 숫자를 HashSet에 집어넣고 다 빼면 하나가 남을 것. 아니면 정렬해서 인접한 것끼리 비교해나가면 찾을 수 있을 것. 아니면 전체 sum을 바로 구할 수 있으니까, 가우스 formula. n(n+1)/2 그러니까 전체 sum한 다음에 저기서 빼면 나옴. 물론 overflow가 없다는 전제. 아니면 XOR(^) 연산을 이용하면 같은 숫자는 0이 되니까, 0…...n 이랑 0....n을 모두 XOR하면 0이 된다. 만약 여기서 이빨 빠진 하나가 있다면 그 숫자만 남을 것이다. 이러면 bitwise operation이라 overflow도 없다.
*/
class Solution {
    public int missingNumber(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i <= nums.length; i++) set.add(i);
        for (int n : nums) set.remove(n);
        for (int s : set) return s;
        return -1;
    }
}
class Solution {
    public int missingNumber(int[] nums) {
        int sum = 0;
        for (int n : nums) sum += n;
        return (nums.length + 1) * (nums.length) / 2 - sum;
    }
}
class Solution {
    public int missingNumber(int[] nums) {
        int sum = nums.length;
        for (int i = 0; i < nums.length; i++) sum ^= i ^ nums[i];
        return sum;
    }
}