/*
295. Find Median from Data Stream
항상 정렬된 상태로 만들면 (insertion sort) 들어갈 위치를 찾는 건 O(logn)인데 shift하느라 O(n)이 더 든다.
대신에 two heaps를 쓰면 그냥 O(logn)이 든다. 이때 2개의 힙을 잘 balance해줘야 한다.
왼쪽 값을 기준으로 이것보다 큰 것만 오른쪽에 넣는데, 그러다가 한 쪽이 길어지면 빼서 넣어준다.
대신에 왼쪽을 하나씩 크게 만들어서 홀수 개일때 바로 찾도록 한다.

그래서 balancing 할 때 left == right + 2, left + 1 == right 이렇게 비대칭으로 만든다.
*/
class MedianFinder {
    PriorityQueue<Integer> l = new PriorityQueue<>(Collections.reverseOrder());
    PriorityQueue<Integer> r = new PriorityQueue<>();

    /** initialize your data structure here. */
    public MedianFinder() {
        
    }
    
    public void addNum(int num) {
        if (r.size() == 0 || l.peek() >= num) l.offer(num);
        else r.offer(num);
        
        if (l.size() - 2 == r.size()) r.offer(l.poll());
        else if (l.size() == r.size() - 1) l.offer(r.poll());
    }
    
    public double findMedian() {
        return l.size() == r.size() ? l.peek()/2.0 + r.peek()/2.0 : l.peek();
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */