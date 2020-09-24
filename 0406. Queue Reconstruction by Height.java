/*
406. Queue Reconstruction by Height
의외로 O(nlogn)이 안된다. sort는 일단 해야 해서 O(nlogn)은 일단 드는데,
315. Count of Smaller Numbers After Self 처럼 segment tree를 써도 값이 여러개이기 때문에, 각 노드별로 최대 O(n)이 걸려서 느리다.
결국 insertion sort로 들어갈 자리를 찾아서 넣어야 하는데, 들어갈 자리까지는 index로 바로 찾으면 되지만 그러니까 매번 O(n)이 든다.

결국 어떻게 해도 매번 O(n)이 걸리기 때문에 그냥 평범하게 LinkedList.add(index, ...)로 했다.
앞에 자기보다 작거나 같은 것의 갯수를 찾는 거니까, 큰 수부터 정렬해서 add(갯수=위치, ...)로 해나가면 된다.
같은 위치일때는 기존 값이 더 클 것이므로 뒤로 미뤄도 영향이 없다.
값이 같은 경우에는 갯수가 적은 쪽이 먼저 나와야 하니까 그걸 감안해서 정렬한다.
*/
class Solution {
    public int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, (a,b)->(a[0] == b[0] ? Integer.compare(a[1], b[1]) : Integer.compare(b[0], a[0])));
        List<int[]> list = new LinkedList<>();
        for (int[] p : people) list.add(p[1], p);
        return list.toArray(new int[people.length][2]);
    }
}