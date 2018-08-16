# https://leetcode.com/problems/top-k-frequent-words/description/
# Count words & HeapQ
# Time: O(wlogk), Space: O(w) without output
class Solution:
    class Node(object):
        def __init__(self, cnt, w):
            self.cnt = cnt
            self.w = w
        def __lt__(self, o):
            return self.cnt < o.cnt if self.cnt!=o.cnt else self.w > o.w
        #def __repr__(self): return str(self.cnt)+","+self.w
        
    def topKFrequent(self, words, k):
        dic, heap = {}, []
        for w in words:
            if w in dic: dic[w] += 1
            else: dic[w] = 1
        for w,cnt in dic.items():
            heapq.heappush(heap, self.Node(cnt,w))
            if len(heap)>k: heapq.heappop(heap)
        #print(heap)
        ans = collections.deque()
        while heap:
            n = heapq.heappop(heap)
            ans.appendleft(n.w)
        return list(ans)
