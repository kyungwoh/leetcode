/*
384. Shuffle an Array

그냥 random하게 하는 것도 쉽지 않다. List를 copy해서 만들고, 거기서 랜덤으로 하나씩 뽑아쓴다. 그걸 기록해서 다음에 거기서부터 다시 시작한다.
근데 Fisher-Yates 알고리듬이라고 하나씩 전진하면서 뒤의 것들 중에 하나를 뽑아서 swap해나가면 결국 위의 결과와 같아진다.
그러면 Time O(n^2) -> O(n), Space O(n) -> O(1) 으로 줄어든다.
*/

class Solution {
    int[] arr, org;
    Random r = new Random();

    public Solution(int[] nums) {
        this.arr = nums;
        this.org = nums.clone();
    }
    
    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
        arr = org;
        org = org.clone();
        return arr;
    }
    
    /** Returns a random shuffling of the array. */
    public int[] shuffle() {
        List<Integer> list = new ArrayList<>();
        for (int a : arr) list.add(a);
        
        for (int i = 0; i < arr.length; i++) {
            int j = r.nextInt(list.size());
            arr[i] = list.get(j);
            list.remove(j);
        }
        return arr;
    }
}

class Solution {
    int[] arr, org;
    Random r = new Random();

    public Solution(int[] nums) {
        this.arr = nums;
        this.org = nums.clone();
    }
    
    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
        arr = org;
        org = org.clone();
        return arr;
    }
    
    /** Returns a random shuffling of the array. */
    public int[] shuffle() {
        for (int i = 0; i < arr.length; i++) {
            int j = r.nextInt(arr.length - i) + i;
            
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
        return arr;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int[] param_1 = obj.reset();
 * int[] param_2 = obj.shuffle();
 */