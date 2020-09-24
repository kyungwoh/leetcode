/*
315. Count of Smaller Numbers After Self
일단 O(n)이 안되고, 그렇다고 O(n^2)을 하자니 그냥 naive한것과 차이가 없다. 결국 O(nlogn)을 찾아야 한다.
그럼 2가지 아이디어가 있는데, 일단 sort를 해서 어떻게 하는 거하고, 매 칸마다 O(logn)이 걸리는 방법일 것이다.

1. segment tree: 각 노드마다 현재보다 작은 수들의 cnt를 저장하는 것이다.
binary search tree로 매번 insert를 하면, 각 노드마다 현재보다 크거나 작은 여부를 판단할 것이다. 그래서
1) 새 숫자가 현재 노드보다 작다면, 현재 노드보다 더 작은 숫자가 하나 더 생기는 거고, 그 숫자는 왼쪽으로 보낸다.
2) 새 숫자가 현재 노드보다 크다면, 현재 노드 + 현재 노드보다 더 작은 숫자들을 세서 오른쪽으로 보낸다.
3) 새 숫자와 현재 노드와 같다면, 중복이니까 현재 노드에 1을 추가한다.
그러면 각 노드마다 a) 중복된 숫자 b) 현재 노드보다 작은 숫자 갯수를 기록할 수 있으니까, 이걸 매번 갱신해서 저장해서 리턴한다.

2. sort of sort
값으로 sort할때 인덱스를 저장해놓는다. 그런 다음에 다시 인덱스로 sort하면서, 인덱스 값이 얼마나 뒤로 움직이는지를 센다.
1) (값, 인덱스) 만들어서 값으로 정렬한다
2) 여기서 인덱스만 뽑아내서, 다시 인덱스로 정렬한다 (값은 없어도 된다. 인덱스만 필요하다)
2-1) 정렬할 때, 인덱스가 후진하는 경우만 다음을 추가한다. (전진하거나 같은 경우는 필요없다)
     인덱스가 후진한다는 얘기는, 그 값보다 작은 값들이 그 사이에 있다는 얘기다. 그러니까 후진하는 만큼이 더 작은 수들의 갯수다.
     왼쪽이 i, 오른쪽의 시작이 m + 1 이니까, m + 1 - i 만큼 뒤로 돌아간다. 이만큼을 더해나가면 된다.

결국 segment tree나 sort of sort나 원리는 같다. 정렬을 하면서 자기보다 작은 숫자들을 세 나가는 것이다.
이것을 모아야 하니까 별도의 저장공간에 cnt를 계속 더해나간다.
*/
class Solution {
    List<Integer> cnts;
    public List<Integer> countSmaller(int[] nums) {
        cnts = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) cnts.add(0);
        
        Node root = null;
        for (int i = nums.length - 1; i >= 0; i--) {
            root = insert(nums[i], root, i, 0);
        }
        return cnts;
    }
    Node insert(int num, Node node, int i, int cnt){
        if (node == null) {
            cnts.set(i, cnt);
            return new Node(num);
        }
        if (node.num == num){
            node.cnt++;
            cnts.set(i, cnt + node.leftCnt);
        } else if (node.num > num) {
            node.leftCnt++;
            node.left = insert(num, node.left, i, cnt);
        } else {
            node.right = insert(num, node.right, i, cnt + node.cnt + node.leftCnt);
        }
        return node;
    }
    class Node {
        Node left, right;
        int num, cnt, leftCnt;
        Node (int num) {
            this.num = num;
            this.cnt = 1;
        }
    }
}
class Solution {
    public List<Integer> countSmaller(int[] nums) {
        int len = nums.length;
        if (len == 0) return Collections.emptyList();
        
        Pair[] pairs = new Pair[len];
        for (int i = 0; i < len; i++) pairs[i] = new Pair(nums[i], i);
        Arrays.sort(pairs, (a, b) -> Integer.compare(a.val, b.val));
        
        int[] idxs = new int[len];
        for (int i = 0; i < len; i++) idxs[i] = pairs[i].i;
        
        List<Integer> cnts = new ArrayList<>();
        for (int i = 0; i < len; i++) cnts.add(0);
        
        mergeSort(idxs, cnts, 0, len-1);
        return cnts;
    }
    void mergeSort(int[] idxs, List<Integer> cnts, int l, int r) {
        if (l == r) return;
        int m = (l + r) >>> 1;
        mergeSort(idxs, cnts, l, m);
        mergeSort(idxs, cnts, m+1, r);
        
        // merge
        List<Integer> temp = new ArrayList<>();
        int i = l, j = m+1;
        while (i <= m && j <= r) {
            if (idxs[i] <= idxs[j]) {
                temp.add(idxs[i++]);
            } else {
                cnts.set(idxs[j], cnts.get(idxs[j]) + m + 1 - i);
                temp.add(idxs[j++]);
            }
        }
        while (i <= m) temp.add(idxs[i++]);
        while (j <= r) temp.add(idxs[j++]);
        for (int i2 = l, k = 0; k < temp.size(); i2++, k++) idxs[i2] = temp.get(k);
    }
    class Pair {
        int val, i;
        Pair(int val, int i) {
            this.val = val;
            this.i = i;
        }
    }
}