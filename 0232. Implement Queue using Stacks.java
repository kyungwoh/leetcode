/*
232. Implement Queue using Stacks
225. Implement Stack using Queues 와 대칭 문제
두 개의 스택으로 큐를 구현하세요.
스택과 큐는 무엇입니까? 일반적인 컴퓨터의 스택과 큐의 자료구조입니다.
스택과 큐는 어떤 기능을 가집니까? Add, remove, size 3개입니다. + 만약 get(index)를 추가해야 한다면? 이것을 O(1)로 만들려면?
어떤 값을 가집니까? 정수 값을 가집니다.
Add, remove는 몇 회나 불립니까? 제한 없습니다.
큐의 길이가 메모리에 들어갑니까? 네.
큐의 원소가 0개일 때 remove하면 어떻게 합니까? null을 리턴하세요.
시간과 공간의 제약이 있습니까? 없지만 최소한으로 사용하세요.
(주의사항)
순서가 중요! 넣고 빼고 할때 실수하면 안됨.
큐 내부의 데이터에 제약사항이 있는지? 항상 정렬되어 있어야 하는지?

1. push() 할때 정렬하기, pop(), peek() 할땐 그냥 쓰기
2. push() 그냥 했다가, pop(), peek() 할때 정렬해서 쓰기
*/
class MyQueue {
    Stack<Integer> s1, s2;

    /** Initialize your data structure here. */
    public MyQueue() {
        s1 = new Stack<>();
        s2 = new Stack<>();
    }
    
    /** Push element x to the back of queue. */
    public void push(int x) {
        s1.push(x);
    }
    
    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        helper();
        return s2.isEmpty() ? -1 : s2.pop();
    }
    
    /** Get the front element. */
    public int peek() {
        helper();
        return s2.peek();
    }
    
    private void helper() {
        if (s2.isEmpty()) {
            while (!s1.isEmpty()) s2.push(s1.pop());
        }
    }
    
    /** Returns whether the queue is empty. */
    public boolean empty() {
        return s1.isEmpty() && s2.isEmpty();
    }
}

class MyQueue {
    Stack<Integer> q1, q2;

    /** Initialize your data structure here. */
    public MyQueue() {
        q1 = new Stack<>();
        q2 = new Stack<>();
    }
    
    /** Push element x to the back of queue. */
    public void push(int x) {
        while (!q1.isEmpty()) q2.push(q1.pop());
        q1.push(x);
        while(!q2.isEmpty()) q1.push(q2.pop());
    }
    
    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        return q1.isEmpty() ? -1 : q1.pop();
    }
    
    /** Get the front element. */
    public int peek() {
        return q1.peek();
    }
    
    /** Returns whether the queue is empty. */
    public boolean empty() {
        return q1.isEmpty();
    }
}

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */