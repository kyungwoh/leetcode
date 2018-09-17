# https://leetcode.com/problems/reconstruct-itinerary/description/
# HashMap(Dict) + PriorityQueue(heap) + DFS
# Time: O(n), Space: O(n)
class Solution:
    def findItinerary(self, tickets):
        def dfs(cur):
            if cur in aDic:
                while aDic[cur]:
                    dfs(heapq.heappop(aDic[cur]))
            ans.append(cur)
        
        aDic = {}
        for t in tickets:
            if t[0] not in aDic: aDic[t[0]] = []
            heapq.heappush(aDic[t[0]],t[1])
        ans = []
        dfs("JFK")
        return ans[::-1]
