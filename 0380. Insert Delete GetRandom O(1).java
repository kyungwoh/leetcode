/*
380. Insert Delete GetRandom O(1)
O(1)이니까 HashSet이던 HashMap이건 써야 한다.
set을 쓰면 insert, delete는 되는데 random이 느리다.
list로 하면 random은 빠른데 insert, delete가 느리다 -> 이걸 빨리 하려면 중간을 바꿔치기한다.

insert는 평범하게 list에 넣고, 그러면서 map<val, index>로 쌓아놓는다.
remove할 때, map으로 index를 찾아서 list의 맨 마지막 것과 swap하고, 맨 마지막 걸 지운다.
그리고 map에서도 지울 건 지운 다음, swap한 것의 index를 update 해준다.
*/
class RandomizedSet {
    List<Integer> list;
    Map<Integer, Integer> map;
    Random r;

    /** Initialize your data structure here. */
    public RandomizedSet() {
        list = new ArrayList<>();
        map = new HashMap<>();
        r = new Random();
    }
    
    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if (map.containsKey(val)) return false;
        list.add(val);
        map.put(val, list.size()-1);
        return true;
    }
    
    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if (!map.containsKey(val)) return false;
        
        int i = map.get(val);
        int lastVal = list.get(list.size()-1);
        map.put(lastVal, i);
        list.set(i, lastVal);
        map.remove(val);
        list.remove(list.size()-1);
        return true;
    }
    
    /** Get a random element from the set. */
    public int getRandom() {
        return list.get(r.nextInt(list.size()));
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */