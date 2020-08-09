/*
642. Design Search Autocomplete System
Trie를 이용하거나 Map을 이용한다. 아니면 String.indexOf()로 String에 해당 prefix가 포함되는지를 확인한다.
문제가 길기 때문에 빨리 풀어야 한다. 다 구현할 수 없을 것 같다면 function을 만들어서 그렇다 치고 넘어간다. Trie를 만들어서 모든 sentence들을 넣어놓고, 하나 입력받을 때마다 curr를 한 칸씩 전진한다. curr String도 쌓아놓는다. 복잡도는 time O(n), space O(n) 이다.
그 다음 curr에 해당하는 List<String> 을 return 해야 하는데, 이걸 매번 root부터 curr String 가지고 traverse해서 찾을지, 아니면 각 Trie별로 미리 넣어놓을지 결정한다. 앞에 껀 time O(n), space O(n) 이고, 뒤에 껀 time O(1), space O(n) 이다. 여기서 space O(n) 으로 하려면 각 Trie별로 deep copy를 하면 안되고, String을 pointer로 연결해야 한다. 그래서 ans list를 만들면, 그것을 sort해서 상위 3개만 return한다.
여기서 sort를 미리 해 놓을지, 아니면 input 시점에 sort를 해 놓을지를 결정해야 하는데, 그러면 cnt++ 될때마다 전체 Trie를 돌면서 다 update해줘야 하기 때문에, #을 받아서 끝낼 때마다 time O(n) 이 든다. 대신 일반 input은 time O(1)이 걸릴 것이다. 대신에 sort를 안 해놓으면 time O(mlogm) m=max(cnt) 가 들 것이고, # 시점에는 time O(len)이 걸릴 것이다. update를 자주 안 한다면 앞의 방식이 나을 것이다.
String 별 cnt를 매번 update해야 해서 class로 같이 가지고 있을지, 아니면 Map<String, Integer> 로 가지고 있을지 결정한다.
*/
class AutocompleteSystem {
    Node head, current;
    StringBuilder cursor;
    Map<String, Str> map;

    public AutocompleteSystem(String[] sentences, int[] times) {
        head = new Node();
        map = new HashMap<>();
        for (int i = 0; i < times.length; i++) {
            Str str = new Str(sentences[i], times[i]);
            map.put(sentences[i], str);
            add(str);
        }
        cursor = new StringBuilder();
        current = head;
    }
    void add(Str str) {
        Node curr = head;
        for (char c : str.s.toCharArray()) {
            int j = c == ' ' ? 26 : (int)(c - 'a');
            if (curr.next[j] == null) curr.next[j] = new Node();
            curr = curr.next[j];
            curr.add(str);
        }
    }
    
    public List<String> input(char c) {
        if (c == '#') {
            List<String> ans;
            if (current == null) ans = new ArrayList<>();
            else ans = current.get();
            
            if (map.containsKey(cursor.toString())) {
                map.get(cursor.toString()).cnt++;
            } else {
                Str str = new Str(cursor.toString(), 1);
                map.put(cursor.toString(), str);
                add(str);
            }
            
            cursor = new StringBuilder();
            current = head;
            return new ArrayList<>();
        } else {
            cursor.append(c);
            return get(c);
        }
    }
    
    List<String> get(char c) {
        if (current == null) return new ArrayList<>();
        else {
            int j = c == ' ' ? 26 : (int)(c - 'a');
            current = current.next[j];
            if (current == null) return new ArrayList<>();
            else return current.get();
        }
    }
    
    class Node {
        Node[] next;
        LinkedList<Str> list;
        Node() {
            next = new Node[27];
            list = new LinkedList<>();
        }
        List<String> get() {
            if (!list.isEmpty()) {
                list.sort((a,b)-> a.cnt == b.cnt ? a.s.compareTo(b.s) : Integer.compare(b.cnt, a.cnt));
            }
            List<String> sList = new ArrayList<>();
            for (int i = 0; i < 3 && i < list.size(); i++) {
                sList.add(list.get(i).s);
            }
            return sList;
        }
        void add(Str s) {
            list.add(s);
            list.sort((a,b)-> a.cnt == b.cnt ? a.s.compareTo(b.s) : Integer.compare(b.cnt, a.cnt));
        }
    }
    class Str {
        String s;
        int cnt;
        Str(String s, int cnt) {
            this.s = s;
            this.cnt = cnt;
        }
    }
}
class AutocompleteSystem {
    private class Node {
        String s;
        int cnt;
        Node(String s, int cnt) {
            this.s = s;
            this.cnt = cnt;
        }
    }
    private class Trie {
        int cnt;
        Trie[] next = new Trie[27];
    }
    private Trie root;
    private String curr = "";
    public AutocompleteSystem(String[] sentences, int[] times) {
        root = new Trie();
        for (int i = 0; i < sentences.length; i++) {
            insert(root, sentences[i], times[i]);
        }
    }
    private int toInt(char c) { return c == ' ' ? 26 : c - 'a'; }
    private char toChar(int i) { return i == 26 ? ' ' : (char)('a' + i); }
    private void insert(Trie t, String s, int cnt) {
        for (char c : s.toCharArray()) {
            int i = toInt(c);
            if (t.next[i] == null) t.next[i] = new Trie();
            t = t.next[i];
        }
        t.cnt += cnt;
    }
    private List<Node> lookup(Trie t, String s) {
        List<Node> list = new ArrayList<>();
        for (char c : s.toCharArray()) {
            int i = toInt(c);
            if (t.next[i] ==  null) return Collections.emptyList();
            t = t.next[i];
        }
        traverse(s, t, list);
        return list;
    }
    private void traverse(String s, Trie t, List<Node> list) {
        if (t.cnt > 0) list.add(new Node(s, t.cnt));
        for (int i = 0; i < 27; i++) {
            if (t.next[i] != null) traverse(s + toChar(i), t.next[i], list);
        }
    }
    public List<String> input(char c) {
        List<String> ans = new ArrayList<>();
        if (c == '#') {
            insert(root, curr, 1);
            curr = "";
        } else {
            curr += c;
            List<Node> list = lookup(root, curr);
            Collections.sort(list, (a,b)-> a.cnt == b.cnt ? a.s.compareTo(b.s) : Integer.compare(b.cnt, a.cnt));
            for (int i = 0; i < 3 && i < list.size(); i++) ans.add(list.get(i).s);
        }
        return ans;
    }
}

/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
 * List<String> param_1 = obj.input(c);
 */

 class AutocompleteSystem {
  class Trie {
    private Trie[] next;
    private List<String> list;
    
    public Trie() {
      next = new Trie[27];
      list = new ArrayList<>();
    }
    
    public void update(String s) {
      Trie curr = root;
      for (int i = 0; i < s.length(); i++) {
        int j = s.charAt(i) == ' ' ? 26 : s.charAt(i) - 'a';
        if(curr.next[j] == null) curr.next[j] = new Trie();
        curr = curr.next[j];
        insert(curr.list, s);
      }
    }
    
    private void insert(List<String> list, String s) {
      for(int i = 0; i < list.size(); i++) {
        if (list.get(i).equals(s)) {
          list.remove(i);
          break;
        }
      }
      int i = 0;
      for(; i < list.size(); i++) {
        if (compare(s, list.get(i)) < 0) {
          list.add(i, s);
          break;
        }
      }
      if (i == list.size()) list.add(s);
      if (list.size() > 3) list.remove(list.size() - 1);
    }
    
    private int compare(String a, String b) {
      int i = cnt.get(a);
      int j = cnt.get(b);
      return i == j ? a.compareTo(b) : Integer.compare(j, i);
    }
  }

  private Trie root;
  private Trie curr;
  private StringBuilder sb;
  private HashMap<String, Integer> cnt;

  public AutocompleteSystem(String[] sentences, int[] times) {
    root = new Trie();
    curr = root;
    sb = new StringBuilder();
    cnt = new HashMap<String, Integer>();
    
    for (int i=0; i < sentences.length; i++) {
      cnt.put(sentences[i], times[i]);
      root.update(sentences[i]);
    }
  }

  public List<String> input(char c) {
    if(c == '#') {
      curr = root;
      String s = sb.toString();
      sb.setLength(0);
      cnt.put(s, cnt.getOrDefault(s, 0) + 1);
      root.update(s);
      return Collections.emptyList();
    } else {
      sb.append(c);
      if(curr == null) return Collections.emptyList();
      int i = c == ' ' ? 26 : c - 'a';
      curr = curr.next[i];
      if (curr == null) return Collections.emptyList();
      return curr.list;
    }
  }
}