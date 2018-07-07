// https://leetcode.com/problems/min-stack/description/
// Keeping the last mininum value
// Time: O(1), Space: O(n)
class MinStack {
    LinkedList<Node> list;

    /** initialize your data structure here. */
    public MinStack() {
        list = new LinkedList<Node>();
    }
    
    public void push(int x) {
        if(list.isEmpty()){
            list.addLast(new Node(x,x));
        }else{
            int lastMin = list.getLast().min;
            list.addLast(new Node(x, Math.min(lastMin,x)));
        }
    }
    
    public void pop() {
        list.pollLast();
    }
    
    public int top() {
        return list.getLast().val;
    }
    
    public int getMin() {
        return list.getLast().min;
    }
}

class Node{
    int val, min;
    Node(int val, int min){
        this.val = val;
        this.min = min;
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
