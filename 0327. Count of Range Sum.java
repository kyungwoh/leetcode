/*
327. Count of Range Sum
range sum(partial sum)을 lower bound, upper bound로 구하는 문제다.
그냥 모든 경우를 찾아보면 O(n^2)가 되겠지만, 좀 더 나은 방법이라면 O(nlogn)이 될 것이다. sort 아니면 tree다.

모든 경우 대신에 필요한 경우만 해보려면, 정렬을 해놓고 전진만 하면 된다.
psum을 구하려면 시작점과 끝점이 있어야 하니까, 시작점을 고정하고 끝점이 어디일지를 찾는다.
그 다음에 시작점을 한칸 전진하면, 끝점을 뒤로 돌릴 필요가 없다. 끝점도 전진하면서 찾으면 된다.
이러면 시작점, 끝점 모두 전진만 하기 때문에 O(n)에 가능하다.

다만 이것은 lower, upper 둘 다 찾아야 하기 때문에,
마찬가지로 시작점을 고정하고, ㅣower의 끝점, upper의 끝점을 찾아서, 두 끝점의 차이를 비교한다.
lower와 upper를 같은 점에서 시작해서 전진만 하면, lower와 upper가 역전되는 일은 없을 것이고,
가능한 경우만 차이가 벌어질 것이다. 그 차이만큼이 psum이 존재하는 갯수다.

이것들을 계속 count를 더해나가면, merge할때 왼쪽 구간과 오른쪽 구간을 더 뒤져볼 필요가 없다.
그냥 왼쪽 구간에서 구했던 count, 오른쪽에서 구했던 count,
그리고 새로 등장하는 구간을 위해 시작점을 왼쪽에서만 잡고, 끝점을 오른쪽에서만 잡아서 전진하면서 더하면 된다.

---
아니면 C의 multiset을 이용하면 좀 더 간단하게 구할 수 있다. (자바에는 멀티셋이 없다)
멀티셋은 sorted set인데, 정렬되어있으므로 lower_bound와 upper_bound를 binary search로 O(logn)에 구할 수 있다.
그리고 std::distance는 두 점 사이의 거리를 구하는 것으로 (중복 숫자를 감안해서) 마찬가지로 O(logn)에 된다.
그리고 insert도 O(logn)에 가능하다. 
그러면 psum을 각각 한칸씩 binary search하고 insert하면 O(nlogn)에 가능하다.

sum = 끝점 - 시작점 이므로, 시작점을 하나씩 돌면서 끝점을 찾아도 되고, 끝점을 하나씩 돌면서 시작점을 찾아도 된다.
여기서는 끝점을 하나씩 돌았는데, 그러면 psum을 별도의 for문으로 안 빼고 같이 돌아도 된다. 대신에 식이 좀 복잡해지는데
lower < 끝점 - 시작점 < upper 니까, 시작점 기준으로 바꾸면
끝점 - upper < 시작점 < 끝점 - lower 가 된다. 그래서 lower_bound(끝점 - upper), upper_bound(끝점 - lower)를 찾는다.
*/
class Solution {
    long[] psum;
    int lower, upper;
    public int countRangeSum(int[] nums, int lower, int upper) {
        this.lower = lower;
        this.upper = upper;
        this.psum = new long[nums.length + 1];
        for (int i = 0; i < nums.length; i++) psum[i+1] = psum[i] + nums[i];
        return mergeSort(0, nums.length);
    }
    int mergeSort(int l, int r) {
        if (l >= r) return 0;
        
        int m = (l+r)>>>1;
        int count = mergeSort(l, m) + mergeSort(m+1, r);
        for (int i = l, start = m+1, end = m+1; i <= m; i++) {
            while (start <= r && psum[start] - psum[i] < lower) start++;
            while (end <= r && psum[end] - psum[i] <= upper) end++;
            count += end - start;
        }
        
        // merge
        List<Long> temp = new ArrayList<>();
        int i = l, j = m+1;
        while (i <= m && j <= r) {
            if (psum[i] <= psum[j]) temp.add(psum[i++]);
            else temp.add(psum[j++]);
        }
        while (i <= m) temp.add(psum[i++]);
        while (j <= r) temp.add(psum[j++]);
        for (i = l, j = 0; j < temp.size(); i++, j++) psum[i] = temp.get(j);
        
        return count;
    }
}
class Solution {
public:
    int countRangeSum(vector<int>& nums, int lower, int upper) {
        multiset<long long> pSum;
        int res = 0,  i; 
        long long left, right, sum=0;
        for(i=0,pSum.insert(0); i<nums.size(); ++i)
        {
            sum += nums[i];
            res += std::distance(pSum.lower_bound(sum-upper), pSum.upper_bound(sum-lower));
            pSum.insert(sum);
        }
        return res;
    }
};
public class Solution {
    public int CountRangeSum(int[] nums, int lower, int upper) {
        int cnt = 0;
        if(nums.Length>0){
            MultiSet rSet = new MultiSet();
            rSet.Add(0);
            long sum = 0;
            for(int i=0; i<nums.Length; i++) {
                sum += (long)nums[i];
                cnt += rSet.Distance(sum-(long)upper, sum-(long)lower);
                rSet.Add(sum);
            }
        }
        return cnt;
    }

}
class MultiSet {
    public List<long> list;
    public MultiSet(){ list = new List<long>(); }
    public void Add(long i){
        if(list.Count==0) list.Add(i);
        else{
            int u = Upper(i);
            if(u<0) u=0;
            if(u==list.Count) list.Add(i);
            else list.Insert(u,i);
        }
    }
    public int Lower(long o){
        int lo = 0, hi = list.Count-1;
        if(o.CompareTo(list[lo])<0) return lo-1;
        else if(o.CompareTo(list[hi])>0) return hi+1;
        else{
            while(lo<=hi){
                int m=(hi-lo)/2+lo;
                if(list[m].CompareTo(o)==0 &&(m==0 || list[m-1].CompareTo(o)<0)) return m;
                else if(list[m].CompareTo(o)<0) lo=m+1;
                else hi=m-1;
            }
            return lo;
        }
    }
    public int Upper(long o){
        int lo = 0, hi = list.Count-1;
        if(o.CompareTo(list[lo])<0) return lo-1;
        else if(o.CompareTo(list[hi])>=0) return hi+1;
        else{
            while(lo<=hi){
                int m=(hi-lo)/2+lo;
                if(list[m].CompareTo(o)==0 && (m==list.Count-1 || list[m+1].CompareTo(o)>0)) return m;
                else if(list[m].CompareTo(o)>0) hi=m-1;
                else lo=m+1;
            }
            return lo;
        }
    }
    public int Distance(long lo, long up){        
        int l = Lower(lo);
        int u = Upper(up);
        int dis = 0;
        //Console.Write("lo="+lo+" up="+up+" [");
        if(u>=l){
            if(u==l){
                if(u>=0 && u<list.Count && list[u]<=up && list[l]>=lo) dis=1;
            }else{
                if(u<list.Count && list[u]==up) u++;
                if(l<0) l=0;
                else if(list[l]<lo) l++;
                
                dis = u-l;
            }
        }
        /*
        Console.Write("dis="+dis+" lo="+lo+" up="+up+" [");
        foreach(long ll in list) Console.Write(ll+" ");
        Console.WriteLine("] l="+l+" u="+u);
        */
        return dis;
    }
}