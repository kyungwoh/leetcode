/*
84. Largest Rectangle in Histogram
O(n)에 푸는 방법은 stack을 쓰거나, left max, right max로 two pass를 하는 것이다.

stack은 계속 쌓아나가다가, 현재 높이보다 스택 맨 위의 높이가 높을때 뺀다.
그러면 현재 높이와 스택 멘 위의 높이 중에 현재 높이가 더 작으니까, 그 높이를 쓰면 된다.
그럼 폭은 현재 위치와 스택 맨 위의 위치가 떨어진 만큼일 것이다. 즉 스택에서 인덱스와 높이 둘 다 가지고 있어야 하니, 인덱스를 저장해놓고 인덱스로 높이를 가져오면 된다.
그렇게 계속 스택을 빼나가면서 max값을 갱신해나가다보면, 더 이상 스택에서 뺄 게 없어질텐데 그때 현재걸 스택에 넣고 계속 한다.

문제는 스택이 빌 경우와 맨 마지막 처리다.
스택이 비는 건 맨 왼쪽 끝이니까 가상으로 -1 인덱스를 주면 편리하다.
맨 마지막에는 for loop를 다 돈 다음에 따로 처리를 해주던가, 아니면 for loop를 하나 더 돌아서 i == length라고 치고 처리를 해주던가 해야 한다.
그렇게 스택이 빌 때까지 게속 돌면서 max 케이스를 갱신해나간다.

아니면 two-pass다. 이것은 Space O(n)이 필요한데, 어차피 stack도 O(n)이 드니까 최악의 경우는 같다.
left -> right로 가면서 현재 높이보다 크거나 같은 가장 왼쪽의 인덱스를 저장한다.
이때 p-- 한칸씩 가는게 아니라, 기존에 저장해놓은 인덱스로 찾아긴디. 이래야 반복이 없어서 compression이 되어 O(n)이 된다.
right -> left도 마찬가지로 한다.
맨 왼쪽은 -1, 맨 오른쪽은 length로 저장해서 끝 처리를 한다.
그런 다음에 각 위치마다 height * width 하는데 width = left ... right 사이의 거리다.
*/
class Solution {
    public int largestRectangleArea(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        int max = 0;
        for (int i = 0; i <= heights.length; i++) {
            while (stack.peek() != -1 && (i == heights.length || heights[stack.peek()] >= heights[i])) {
                int j = stack.pop();
                max = Math.max(max, heights[j] * (i - stack.peek() - 1));
            }
            stack.push(i);
        }
        return max;
    }
}
class Solution {
    public int largestRectangleArea(int[] heights) {
        int l = heights.length;
        if(l==0) return 0;
        
        LinkedList<Integer> stack = new LinkedList<Integer>();
        int ans = 0;
        for(int i=0; i<=l; i++){
            int curHeight = i==l ? 0 : heights[i];
            //System.out.println(i+" "+curHeight+" "+stack);
            while(!stack.isEmpty() && heights[stack.peek()] >= curHeight){
                int height = heights[stack.pop()];
                int width = stack.isEmpty() ? i : i - stack.peek() - 1;
                ans = Math.max(ans, height*width);
            }
            //System.out.println(" "+ans+" "+stack);
            stack.push(i);
        }
        return ans;
    }
    public int largestRectangleArea2(int[] heights) {
        int l = heights.length;
        if(l==0) return 0;
        
        int left[] = new int[l];
        left[0] = -1;
        for(int i=1; i<l; i++){
            int p = i-1;
            while(p>=0 && heights[p]>=heights[i]) p = left[p];
            left[i] = p;
        }
        //System.out.println(Arrays.toString(left));
        int right[] = new int[l];
        right[l-1] = l;
        for(int i=l-2; i>=0; i--){
            int p = i+1;
            while(p<l && heights[i]<=heights[p]) p = right[p];
            right[i] = p;
        }
        //System.out.println(Arrays.toString(right));
        int ans = 0;
        for(int i=0; i<l; i++){
            ans = Math.max(ans, heights[i]*(right[i]-left[i]-1));
        }
        return ans;
    }
}