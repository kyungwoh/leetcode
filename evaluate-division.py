# https://leetcode.com/problems/evaluate-division/description/
# HashMap(dictionary) & Union Find
# If 2 nodes are in the same region, division is possible.
# Find next node by BFS(DFS is also possible), cache ongoing values.
# Time: O(e+q) e = len(equations), q = len(queries)
# Space: O(e+q)
class Solution(object):
    def calcEquation(self, equations, values, queries):
        def union(e1, e2):
            p1 = find(e1)
            p2 = find(e2)
            if p1!=p2: dicP[p2] = p1
        
        def find(e):
            if dicP[e]==None: return e
            else:
                dicP[e] = find(dicP[e])
                return dicP[e]
        
        def search(q1, q2):
            que = collections.deque()
            que.append([q1, 1.0])
            while que:
                q = que.popleft()
                #print(q1,q2,q,que)                
                if q2 in dic[q[0]]:
                    if q2 not in dic[q1]: dic[q1][q2] = q[1]*dic[q[0]][q2]
                    return dic[q1][q2]
                else:
                    for k,v in dic[q[0]].items():
                        if k!=q[0]:
                            if k not in dic[q1]: dic[q1][k] = q[1]*v
                            que.append([k, dic[q1][k]])
            
        dic, dicP = {}, {}
        for i in range(len(equations)):
            e1, e2, v = equations[i][0], equations[i][1], values[i]
            if not e1 in dic: dic[e1] = {}
            dic[e1][e2] = v
            dic[e1][e1] = 1.0
            if not e2 in dic: dic[e2] = {}
            dic[e2][e1] = 1/v
            dic[e2][e2] = 1.0
            if not e1 in dicP: dicP[e1] = None
            if not e2 in dicP: dicP[e2] = None
            union(e1, e2)
        ans = []
        for q in queries:
            q1, q2 = q[0], q[1]
            if q1 in dicP and q2 in dicP and find(q1)==find(q2):
                ans.append(search(q1, q2))
            else: ans.append(-1.0)
        return ans
