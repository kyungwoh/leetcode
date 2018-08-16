# https://leetcode.com/problems/find-all-anagrams-in-a-string/description/
# Count alphabets & Compare with bitmask
# Time: O(s+p), Space: O(1) without output
class Solution:
    def findAnagrams(self, s, p):
        def cntBitMask(j, bitMask):
            if cnt[j]==0: bitMask &= ~(1<<j)
            else: bitMask |= 1<<j
            return bitMask
        
        bitMask = 0
        cnt = [0 for x in range(26)]        
        for c in p:
            j = ord(c)-97
            cnt[j] += 1
            bitMask |= 1<<j
        ans = []
        if len(p)<=len(s):
            for i in range(len(p)):
                j = ord(s[i])-97
                cnt[j] -= 1
                bitMask = cntBitMask(j, bitMask)
            if len(p)==len(s) and bitMask==0: ans.append(0)
            else:
                for i in range(len(s)-len(p)):
                    if bitMask==0: ans.append(i)
                    j1 = ord(s[i])-97
                    j2 = ord(s[i+len(p)])-97
                    cnt[j1] += 1
                    bitMask = cntBitMask(j1, bitMask)
                    cnt[j2] -= 1
                    bitMask = cntBitMask(j2, bitMask)
                if bitMask==0: ans.append(len(s)-len(p))
        return ans
        
