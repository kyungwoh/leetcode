/*
218. The Skyline Problem
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
            ans.add(Arrays.asList(Integer.MAX_VALUE, 0));
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
