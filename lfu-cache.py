# https://leetcode.com/problems/lfu-cache/description/
# Use value Dict + count Dict
# Time: get() O(n), put() O(n): can be O(1) using pointer of cDic's deque nodes
# Space: O(n)
class LFUCache:

    def __init__(self, capacity):
        self.capa = capacity
        self.cnt = 0
        self.iDic = {}
        self.cDic = {} 

    def get(self, key):
        if key in self.iDic:
            v,c = self.iDic[key]
            self.cDic[c].remove(key)
            if len(self.cDic[c])==0: self.cDic.pop(c)
            if c+1 not in self.cDic: self.cDic[c+1] = collections.deque()
            self.cDic[c+1].append(key)
            self.iDic[key] = (v,c+1)
            return v;
        else: return -1

    def put(self, key, value):
        if key in self.iDic:
            v,c = self.iDic[key]
            self.iDic[key] = (value,c)
            self.get(key)
        elif self.capa>0:
            if self.cnt==self.capa:
                cDel = min(self.cDic)
                #print("cDel="+str(cDel)+" "+str(self.cDic))
                keyDel = self.cDic[cDel].popleft()
                if len(self.cDic[cDel])==0: self.cDic.pop(cDel)
                self.iDic.pop(keyDel)
                #print(self.iDic,self.cDic,"cDel="+str(cDel),"keyDel="+str(keyDel))
            else: self.cnt += 1
                
            self.iDic[key] = (value,1)            
            if 1 not in self.cDic: self.cDic[1] = collections.deque()
            self.cDic[1].append(key)

# Your LFUCache object will be instantiated and called as such:
# obj = LFUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)
