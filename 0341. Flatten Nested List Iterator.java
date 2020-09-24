/*
341. Flatten Nested List Iterator
nested list인 경우에 recursive하게 깊이 들어가야 하는데, 이게 매번 현재 단계를 저장하고 들어가야 해서 문제다.
이것을 그냥 Iterator를 stack에 집어넣으면, 현재 위치가 저장되서 알아서 해준다. (이게 안된다면, 현재 index와 list를 pair로 저장)
그래서 iterator가 끝에 다다를때마다 계속 pop해서 원래까지 돌아온다.
*/
public class NestedIterator implements Iterator<Integer> {
    Stack<Pair> stack = new Stack<>();
    public NestedIterator(List<NestedInteger> list) { stack.push(new Pair(list, 0)); }
    void prepare() {
        while (!stack.isEmpty()) {
            Pair p = stack.peek();
            if (!p.hasNext()) { stack.pop(); continue; }
            if (p.isInteger()) break;
            stack.push(new Pair(p.getNextList(), 0));
        }
    }
    @Override public Integer next() { prepare(); return stack.peek().getNextInteger(); }
    @Override public boolean hasNext() { prepare(); return !stack.isEmpty(); }
    class Pair {
        List<NestedInteger> list; int i;
        Pair(List<NestedInteger> list, int i) { this.list = list; this.i = i; }
        boolean hasNext() { return i < list.size(); }
        boolean isInteger() { return list.get(i).isInteger(); }
        Integer getInteger() { return list.get(i).getInteger(); }
        List<NestedInteger> getList() { return list.get(i).getList(); }
        Integer getNextInteger() { return list.get(i++).getInteger(); }
        List<NestedInteger> getNextList() { return list.get(i++).getList(); }
    }
}

public class NestedIterator implements Iterator<Integer> {
    Stack<ListIterator<NestedInteger>> stack = new Stack<>();
    Integer peeked = null;
    public NestedIterator(List<NestedInteger> nestedList) {
        stack.push(nestedList.listIterator());
    }
    void setPeeked() {
        if (peeked != null) return;
        while (!stack.isEmpty()) {
            if (!stack.peek().hasNext()) {
                stack.pop();
                continue;
            }
            NestedInteger next = stack.peek().next();
            if (next.isInteger()) {
                peeked = next.getInteger();
                return;
            }
            stack.push(next.getList().listIterator());
        }
    }
    @Override
    public Integer next() {
        setPeeked();
        Integer next = peeked;
        peeked = null;
        return next;
    }
    @Override
    public boolean hasNext() {
        setPeeked();
        return peeked != null;
    }
}
public class NestedIterator implements Iterator<Integer> {
    Stack<List<NestedInteger>> lists = new Stack<>();
    Stack<Integer> idxs = new Stack<>();
    Integer peeked = null;
    public NestedIterator(List<NestedInteger> nestedList) {
        lists.push(nestedList);
        idxs.push(0);
    }
    void prepare() {
        while (!idxs.isEmpty()) {
            int iTop = idxs.peek();
            List<NestedInteger> lTop = lists.peek();
            if (iTop >= lTop.size()) {
                lists.pop();
                idxs.pop();
                continue;
            }
            if (lTop.get(iTop).isInteger()) break;
            lists.push(lTop.get(iTop).getList());
            idxs.push(idxs.pop() + 1);
            idxs.push(0);
        }
    }
    @Override
    public Integer next() {
        prepare();
        int iTop = idxs.pop();
        idxs.push(iTop + 1);
        return lists.peek().get(iTop).getInteger();
    }
    @Override
    public boolean hasNext() {
        prepare();
        return !idxs.isEmpty();
    }
}
/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return null if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */