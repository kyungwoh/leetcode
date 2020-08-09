/*
381. Insert Delete GetRandom O(1) - Duplicates allowed
380. Insert Delete GetRandom O(1) 문제의 변형.
같은 아이디어인데 중복 처리를 해주면 된다. 한 val이 여러 index를 가질 수 있으므로, set으로 관리한다.
새로운 index가 추가되면 add()하고, 기존 것을 삭제해야 하면 remove() 한다.
아무거나 가져와야 할 때는 for문을 돌다가 맨 처음에 값을 가져오고 끝내버린다.

list.size() == 1 일때 지우는 순서가 tricky하다.
set에 add하고 remove하는 index가 중간에 변하지 않도록 저장해놓거나, list.size()가 변하지 않을때 해버린다.
*/
class RandomizedCollection {
    Map<Integer, Set<Integer>> map;
    List<Integer> list;
    Random r;

    /** Initialize your data structure here. */
    public RandomizedCollection() {
        map = new HashMap<>();
        list = new ArrayList<>();
        r = new Random();
    }
    
    /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
    public boolean insert(int val) {
        if (!map.containsKey(val)) {
            map.put(val, new HashSet<>());
        }
        boolean noDup = map.get(val).isEmpty();
        list.add(val);
        map.get(val).add(list.size()-1);
        return noDup;
    }
    
    /** Removes a value from the collection. Returns true if the collection contained the specified element. */
    public boolean remove(int val) {
        boolean hasVal = false;
        if (map.containsKey(val) && !map.get(val).isEmpty()) {
            hasVal = true;
            int index = 0;
            for (int i : map.get(val)) {
                index = i;
                break;
            }
            int oldVal = list.get(list.size()-1);
            map.get(val).remove(index);
            map.get(oldVal).add(index);
            map.get(oldVal).remove(list.size()-1);
            list.set(index, oldVal);
            list.remove(list.size()-1);
        }
        return hasVal;
    }
    
    /** Get a random element from the collection. */
    public int getRandom() {
        return list.get(r.nextInt(list.size()));
    }
}

/**
 * Your RandomizedCollection object will be instantiated and called as such:
 * RandomizedCollection obj = new RandomizedCollection();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */