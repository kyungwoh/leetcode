/*
218. The Skyline Problem
input [width의 왼쪽, 오른쪽, height] [0]으로 정렬되어 있음 --> 이것은 힌트이기도 하다. 하나씩 전진해나가면 된다.
문제는 height를 제일 높은 걸 찾아야 하는데, 그 영역을 벗어날때 다음 높은 걸 찾아나가야 하니까 PQ를 쓴다. 높이 순, 그 다음은 오른쪽.

prev, curr 2개를 비교해서 curr가 더 높으면 ans에 기록하고, prev가 curr보다 오른쪽이면 PQ에 넣어놓는다. 나중에 꺼내서 써야 한다.
만약 prev, curr가 왼쪽이 겹칠 수 있으니까, 이걸 별도 flag로 처리할수도 있지만 단순히 앞에걸 remove()해도 된다. Time O(1) 이다.
그런 다음에 prev = curr 해서 다음으로 넘어간다.

prev, curr 높이가 같거나 낮은 경우는 prev 갱신이 까다롭다.
일단 curr가 prev보다 오른쪽일 경우에만 prev를 갱신한다. 높이가 같으면 prev의 오른쪽만 curr의 오른쪽으로 연장하고, 아니면 단순히 curr를 PQ에 넣어서 다음을 기약한다.

그리고 이제 PQ에서 빼서 기록하는게 남았다. 일단 PQ에서 꺼내야 할 때는 curr보다 prev가 왼쪽이어서 중간이 붕 뜨거나, 맨 끝까지 가서 마무리를 해야 할 때다.
그러면 이제 그동안 PQ에 쌓아놨던 걸 하나씩 꺼내면서, 이게 ans에 쓸 가치가 있는지를 판단한다. 이미 왼쪽으로 지나간거는 skip하고, 나머지만 쓰고 prev에 기록한다.
쓰는 내용은 오른쪽과 height다.

그리고 맨 처음에 prev를 임의로 -1부터 INT_MAX인 것으로 넉넉하게 시작했는데, 아주 특이한 케이스로 오른쪽이 INT_MAX인 경우엔 감안이 안되니까,
이런 경우 맨 처음에 써놨던 -1이 남아있는지, 남아있지 않다면 새로운 값이 들어온 것이므로 그걸 감안해서 맨 끝에 높이를 0으로 만들어줘야 한다.
*/
class Solution {
    public List<List<Integer>> getSkyline(int[][] buildings) {
        List<List<Integer>> ans = new ArrayList<>();
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> a[2] == b[2] ? Integer.compare(a[1], b[1]) : Integer.compare(b[2], a[2]));
        int[] prev = new int[]{-1, Integer.MAX_VALUE, 0};
        for (int[] curr : buildings) {
            while(!pq.isEmpty() && curr[0] > prev[1]) {
                int[] temp = pq.poll();
                if (temp[1] <= prev[1]) continue;
                ans.add(Arrays.asList(prev[1], temp[2]));
                prev = temp;
            }
            if (curr[2] > prev[2]) {
                if (prev[0] == curr[0]) ans.remove(ans.size() - 1);
                ans.add(Arrays.asList(curr[0], curr[2]));
                if (prev[1] > curr[1]) pq.offer(prev);
                prev = curr;
            } else if (curr[1] > prev[1]) {
                if (prev[2] == curr[2]) prev[1] = curr[1];
                else pq.offer(curr);
            }
        }
        while (!pq.isEmpty()) {
            int[] temp = pq.poll();
            if (temp[1] <= prev[1]) continue;
            ans.add(Arrays.asList(prev[1], temp[2]));
            prev = temp;
        }
        if (prev[2] == Integer.MAX_VALUE) {
            ans.add(Arrays.asList(prev[1], 0));
        }
        return ans;
    }
}
class Solution:
    def getSkyline(self, buildings):
        dic = {}
        for b in buildings:
            if b[0] not in dic: dic[b[0]] = []
            dic[b[0]].append(b[2])
            if b[1] not in dic: dic[b[1]] = []
            dic[b[1]].append(-b[2])
        ans, hDic, lastH = [], {}, 0
        for k in sorted(dic.keys()):
            for v in dic[k]:
                if v>0:
                    if v not in hDic: hDic[v] = 1
                    else: hDic[v] += 1
                else:
                    hDic[-v] -= 1
                    if hDic[-v]==0: hDic.pop(-v)
            currH = max(hDic) if hDic else 0
            if lastH!=currH: ans.append([k, currH])
            lastH = currH
        return ans

class Solution:
    def getSkyline(self, buildings):
        dic = {}
        for b in buildings:
            if b[0] not in dic: dic[b[0]] = []
            dic[b[0]].append(b[2])
            if b[1] not in dic: dic[b[1]] = []
            dic[b[1]].append(-b[2])
        ans, h, lastH = [], [], 0
        for k in sorted(dic.keys()):
            for v in dic[k]:
                if v>0: h.append(v)
                else: h.remove(-v)
            curH = max(h) if len(h)>0 else 0
            if lastH!=curH: ans.append([k, curH])
            lastH = curH
        return ans

class Solution {
    public List<int[]> getSkyline(int[][] buildings) {
    	ArrayList<Height> hList = new ArrayList<Height>();
    	for(int[] b : buildings) {
    		hList.add(new Height(b[0],b[2],true));
    		hList.add(new Height(b[1],b[2],false));
    	}
    	Collections.sort(hList);
    	//System.out.println(hList.toString());
    	PriorityQueue<Integer> hQ = new PriorityQueue<Integer>((a,b)->(b-a));
    	hQ.offer(0);
    	ArrayList<int[]> rList = new ArrayList<int[]>();
    	int lastH = 0, currH;
    	for(Height h : hList) {
    		if(h.isStart) hQ.offer(h.h);
    		else hQ.remove(h.h);
    		currH = hQ.peek();
    		//System.out.println(hQ.toString()+","+currH+","+lastH);
			if(currH != lastH) {
				rList.add(new int[] {h.x, currH});
				lastH = currH;
			}
    	}
    	//for(int[] i : rList) System.out.println(Arrays.toString(i));
        return rList;
    }
}

class Height implements Comparable<Height>{
	int x,h;
	boolean isStart;
	public Height(int x, int h, boolean isStart) {
		this.x = x;
		this.h = h;
		this.isStart = isStart;
	}
    public int h() {
		if(isStart) return -h;
		else return h;
	}
	@Override
	public int compareTo(Height o) {
		if(x==o.x) return h()-o.h();
		else return x-o.x;
	}
	public String toString() {
		return "("+x+","+h+","+isStart+")";
	}
}

class Solution {
    public List<int[]> getSkyline(int[][] buildings) {
    	ArrayList<Height> hList = new ArrayList<Height>();
    	for(int[] b : buildings) {
    		hList.add(new Height(b[0],b[2],true));
    		hList.add(new Height(b[1],b[2],false));
    	}
    	Collections.sort(hList);
    	//System.out.println(hList.toString());
    	PriorityQueue<Integer> hQ = new PriorityQueue<Integer>((a,b)->(b-a));
    	hQ.offer(0);
    	ArrayList<int[]> rList = new ArrayList<int[]>();
    	int lastH = 0, currH;
    	for(Height h : hList) {
    		if(h.isStart) hQ.offer(h.h);
    		else hQ.remove(h.h);
    		currH = hQ.peek();
    		//System.out.println(hQ.toString()+","+currH+","+lastH);
			if(currH != lastH) {
				rList.add(new int[] {h.x, currH});
				lastH = currH;
			}
    	}
    	//for(int[] i : rList) System.out.println(Arrays.toString(i));
        return rList;
    }
}

class Height implements Comparable<Height>{
	int x,h;
	boolean isStart;
	public Height(int x, int h, boolean isStart) {
		this.x = x;
		this.h = h;
		this.isStart = isStart;
	}
    public int h() {
		if(isStart) return -h;
		else return h;
	}
	@Override
	public int compareTo(Height o) {
		if(x==o.x) return h()-o.h();
		else return x-o.x;
	}
	public String toString() {
		return "("+x+","+h+","+isStart+")";
	}
}
