/*
895. Maximum Frequency Stack
Map을 Stack처럼 이용할 수 있다. freq = 1, 2, 3... maxFreq = 3 해서 3이 빠지면 maxFreq--; 해준다. 그래서 Map<freq = 1,2,3... , Stack> 하면 각 freq 별로 Stack을 쌓을 수 있다. 숫자별로 freq도 추적해야 하므로 반대 방향으로 Map<number, freq> 도 만들어준다. 그래서 넣을 때 ++, 뺄 때 -- 한다. (주의) freq = 1 -> 2 로 증가할 때 1에 있는 걸 빼면 안된다. 나중에 2 ->1 로 감소할 때 또 빼야 하니까.
*/
class FreqStack {
    Map<Integer, Stack<Integer>> stacks;
    int maxcnt;
    Map<Integer, Integer> cnts;

    public FreqStack() {
        stacks = new HashMap<>();
        maxcnt = 0;
        cnts = new HashMap<>();
    }
    
    public void push(int x) {
        int cnt = cnts.getOrDefault(x, 0) + 1;
        cnts.put(x, cnt);
        if (!stacks.containsKey(cnt)) stacks.put(cnt, new Stack<>());
        stacks.get(cnt).push(x);
        maxcnt = Math.max(maxcnt, cnt);
    }
    
    public int pop() {
        Stack<Integer> s = stacks.get(maxcnt);
        int x = s.pop();
        int cnt = cnts.get(x) - 1;
        cnts.put(x, cnt);
        if (s.isEmpty()) maxcnt--;
        return x;
    }
}

/**
 * Your FreqStack object will be instantiated and called as such:
 * FreqStack obj = new FreqStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 */