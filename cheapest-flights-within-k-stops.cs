// https://leetcode.com/problems/cheapest-flights-within-k-stops/description/
// 1. C# doesn't provide Priority Queue, so I made it. (source: https://visualstudiomagazine.com/articles/2012/11/01/priority-queues-with-c.aspx)
// 2. I used Dijkstra, but I couldn't optimize time with Priority Queue, because it had some corner cases.
// 3. Time: O(V^K), Space: O(V)
public class Solution {
    public int FindCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        Dictionary<int,Dictionary<int,int>> dic = new Dictionary<int,Dictionary<int,int>>();
        for(int i=0; i<flights.Length; i++){
            int u = flights[i][0], v = flights[i][1], w = flights[i][2];
            if(!dic.ContainsKey(u)) dic[u] = new Dictionary<int,int>();
            dic[u].Add(v, w);
        }
        PriorityQueue<Node> pq = new PriorityQueue<Node>();
        pq.Enqueue(new Node(src,0,0));
        while(pq.Count()>0){
            //for(int i=0; i<pq.data.Count; i++) Console.WriteLine(i+" "+pq.data[i].v+" "+pq.data[i].w+" "+pq.data[i].k);
            //Console.WriteLine();
            Node cur = pq.Dequeue();
            if(cur.v==dst) return cur.w;
            if(cur.k<=K){
                if(dic.ContainsKey(cur.v)){
                    foreach(var vk in dic[cur.v]){
                        pq.Enqueue(new Node(vk.Key, cur.w + vk.Value, cur.k +1));                      
                    }                    
                }           
            }            
        }
        return -1;    
    }
}
class Node : IComparable<Node> {
    public int v, w, k;
    public Node(int v, int w, int k){ this.v = v; this.w = w; this.k = k; }
    public int CompareTo(Node o){ return w.CompareTo(o.w); }
}
class PriorityQueue<T> where T : IComparable<T> {
    public List<T> data;
    public PriorityQueue(){ data = new List<T>(); }
    public void Enqueue(T item){
        data.Add(item);
        int i = data.Count-1;
        while(i>0){
            int m = (i-1)/2;
            if(data[i].CompareTo(data[m])>=0) break;
            (data[i],data[m])=(data[m],data[i]);
            i=m;
        }
    }
    public T Dequeue(){
        int last = data.Count-1;
        T item = data[0];
        data[0] = data[last];
        data.RemoveAt(last);
        last--;
        int m = 0, i = 0;
        while((i=m*2+1)<=last){
            int r = i+1;
            if(r<=last && data[r].CompareTo(data[i])<0) i=r;
            if(data[m].CompareTo(data[i])<=0) break;
            (data[i],data[m])=(data[m],data[i]);
            m=i;
        }
        return item;
    }
    public T Peek(){ return data[0]; }
    public int Count(){ return data.Count; }
}
