/*
225. Implement Stack using Queues
232. Implement Queue using Stacks 와 대칭 문제.

1. push() 할때 정렬했다가, pop(), top() 할때 그냥 쓰기 - 한 큐로도 가능
2. push() 할때 그냥 했다가, pop() 할때 맨 끝에서 찾아서 쓰기 - top을 미리 저장해놓으면 top()도 O(1)
*/
class MyStack {
    Queue<Integer> q1, q2;

    /** Initialize your data structure here. */
    public MyStack() {
        q1 = new LinkedList<>();
        q2 = new LinkedList<>();
    }
    
    /** Push element x onto stack. */
    public void push(int x) {
        while (!q1.isEmpty()) q2.offer(q1.poll());
        q1.offer(x);
        while (!q2.isEmpty()) q1.offer(q2.poll());
    }
    
    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        return q1.isEmpty() ? -1 : q1.poll();
    }
    
    /** Get the top element. */
    public int top() {
        return q1.peek();
    }
    
    /** Returns whether the stack is empty. */
    public boolean empty() {
        return q1.isEmpty();
    }
}

class MyStack {
    Queue<Integer> q1, q2;
    int top = -1;

    /** Initialize your data structure here. */
    public MyStack() {
        q1 = new LinkedList<>();
        q2 = new LinkedList<>();
    }
    
    /** Push element x onto stack. */
    public void push(int x) {
        q1.offer(x);
        top = x;
    }
    
    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        while (q1.size() > 1) {
            top = q1.poll();
            q2.offer(top);
        }
        int x = q1.poll();
        q1 = q2;
        q2 = new LinkedList<>();
        return x;
    }
    
    /** Get the top element. */
    public int top() {
        return top;
    }
    
    /** Returns whether the stack is empty. */
    public boolean empty() {
        return q1.isEmpty();
    }
}

class MyStack {
    Queue<Integer> q;

    /** Initialize your data structure here. */
    public MyStack() {
        q = new LinkedList<>();
    }
    
    /** Push element x onto stack. */
    public void push(int x) {
        q.offer(x);
        int size = q.size();
        for (int i = 1; i < size; i++) q.offer(q.poll());
    }
    
    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        return empty() ? -1 : q.poll();
    }
    
    /** Get the top element. */
    public int top() {
        return q.peek();
    }
    
    /** Returns whether the stack is empty. */
    public boolean empty() {
        return q.isEmpty();
    }
}

/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */