/*
42. Trapping Rain Water
물을 가두는 거니까 left bar - ground - right bar 3개가 필요하다. 이것의 면적은 min(l, r) * (r - l +1) 이다. Ground 입장에서 생각해보면 자신의 위로 물이 차는 높이는 min(lMax, rMax)다. 그러므로 각 위치의 lMax, rMax를 two pass로 구해서 min(lMax, rMax) - ground 하면 물의 높이가 나온다. 근데 굳이 lMax, rMax를 다 저장해놓을 필요가 없는게, 중간에 lMax와 rMax가 같아질때까지 왼쪽은 min(lMax, rMax) = lMax, 오른쪽은 min(lMax, rMax) = rMax 다. 그러므로 two pointer로 왼쪽과 오른쪽 중 작은 것을 가운데로 당기면서 왼쪽은 lMax - ground, 오른쪽은 rMax - ground로 구해가면 된다. 아니면 stack을 사용할 수 있는데, 매번 현재 걸 stack에 push했다가 stack에서 left, ground, right를 찾아 꺼내서 더하면 된다. 꺼내는 방법은 ground < right(현재) 일때 ground를 pop하고, 그 다음 걸 left로 peek해서 left - ground - right 영역을 구한다. 그러면 다시 left가 ground가 될 수 있으니, while문으로 만족할때까지 구한다. 이때 left는 계속 증가하는 경우밖에 없으므로 (있었으면 그것이 right가 되어서 이미 구해졌을 것이다) left가 ground가 되면 아까 구한 영역 위에 차곡차곡 쌓이므로 겹치는 영역 없이 전체를 구할 수 있다.
*/
class Solution {
    public int trap(int[] height) {
        int sum = 0;
        for (int l = 0, r = height.length - 1, lMax = 0, rMax = 0; l < r;) {
            if (height[l] < height[r]) {
                if (height[l] < lMax) {
                    sum += lMax - height[l];
                } else {
                    lMax = height[l];
                }
                l++;
            } else {
                if (height[r] < rMax) {
                    sum += rMax - height[r];
                } else {
                    rMax = height[r];
                }
                r--;
            }
        }
        return sum;
    }
}
class Solution {
    public int trap(int[] height) {
        int sum = 0;
        for (int l = 0, r = height.length - 1, lMax = 0, rMax = 0; l < r;) {
            if (height[l] < height[r]) {
                if (height[l] >= lMax) lMax = height[l];
                else sum += lMax - height[l];
                l++;
            } else {
                if (height[r] >= rMax) rMax = height[r];
                else sum += rMax - height[r];
                r--;
            }
        }
        return sum;
    }
}
class Solution {
    public int trap(int[] height) {
        Stack<Integer> stack = new Stack<>();
        int sum = 0;
        for (int i = 0; i < height.length; i++) {
            // leftMax - ground - rightMax
            while(!stack.empty() && height[i] > height[stack.peek()]) {
                int ground = stack.pop();
                if (stack.empty()) break;
                int leftMax = stack.peek();
                
                sum += (i - leftMax - 1) * (Math.min(height[leftMax], height[i]) - height[ground]);
            }
            stack.push(i);
        }
        return sum;
    }
}
class Solution {
    public int trap(int[] height) {
        int[] leftMax = new int[height.length];
        for (int i = 0 ; i < height.length; i++) {
            leftMax[i] = i == 0 ? height[i] : Math.max(leftMax[i-1], height[i]);
        }
        int[] rightMax = new int[height.length];
        for (int i = height.length - 1; i >= 0 ; i--) {
            rightMax[i] = i == height.length - 1 ? height[i] : Math.max(rightMax[i+1], height[i]);
        }
        int sum = 0;
        for (int i = 0; i < height.length; i++) {
            sum += Math.min(leftMax[i], rightMax[i]) - height[i];
        }
        return sum;
    }
}