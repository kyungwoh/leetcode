# https://leetcode.com/problems/word-ladder/description/
# HashMap + Dijkstra w/ Priority Queue
# Time: O(V+VlogE), Space: O(V)
class Solution:
    def ladderLength(self, beginWord, endWord, wordList):
        wordList.append(beginWord)
        wDic, sDic, n = {}, {}, len(beginWord)
        for w in wordList:
            wDic[w] = []
            sDic[w] = sys.maxsize
        if endWord in wDic:
            q = []
            heapq.heappush(q, (1,beginWord))
            while q:
                step,w = heapq.heappop(q)
                if w==endWord: return step
                step += 1
                for i in range(n):
                    for j in range(26):
                        w2 = w[:i]+chr(97+j)+w[i+1:]
                        if w2 in wDic and sDic[w2]>step:
                            sDic[w2] = step
                            heapq.heappush(q, (step,w2))
            return sDic[endWord] if sDic[endWord]!=sys.maxsize else 0
        else: return 0
