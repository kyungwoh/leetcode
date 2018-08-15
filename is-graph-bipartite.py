# https://leetcode.com/problems/is-graph-bipartite/description/
# Assume 1 node = this part, then its connected nodes = that part.
# Recursively assume those, and find contradiction.
# Time: O(n) each node will be visited only once.
# Space: same as time
class Solution(object):
    def isBipartite(self, graph):
        def isBi(p, i):
            for j in graph[i]:
                #print(i,j,part)
                if part[j]==p: return False
                elif part[j]==0:
                    part[j] = -p
                    if not isBi(-p, j): return False
            return True
        
        part = [0 for x in range(len(graph))] #0=not visited, 1=this part, -1=that part
        for i in range(len(graph)):
            if part[i]==0:
                part[i]=1
                if not isBi(1, i): return False
        return True
