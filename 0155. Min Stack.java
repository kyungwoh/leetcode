/*
155. Min Stack
stack의 각 노드에 x, min 둘 다 넣어놓고 매번 비교해가면서 현재까지의 min값을 저장해놓으면 된다. 그러면 pop()을 하면 기존의 min값으로 돌아간다. 이것이 낭비다 싶으면 min값이 갱신이 안되는 동안 (새 값들이 모두 커서) 가만 냅두다가, pop()할 때 min값과 pop 값을 비교해서 min이 이젠 빠져야 된다 싶을때까지 냅두면 된다. 그런데 특이한 경우로 6 7 6 7 6 7 이렇게 같은 min값이 계속 쌓일 경우가 있는데, 이를 절약하기 위해 (6,3) 이렇게 저장할 수도 있다.
*/
class MinStack {
    Stack<int[]> stack = new Stack<>();

    /** initialize your data structure here. */
    public MinStack() {
    }
    
    public void push(int x) {
        int min = stack.empty() ? x : Math.min(stack.peek()[1], x);
        stack.push(new int[]{x, min});
    }
    
    public void pop() {
        stack.pop();
    }
    
    public int top() {
        return stack.peek()[0];
    }
    
    public int getMin() {
        return stack.empty() ? -1 : stack.peek()[1];
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */