# https://leetcode.com/problems/string-compression/description/
# Use 2 pointers
# Time: O(n), Space: O(1) input in-place
class Solution:
    def compress(self, chars):
        pre=chars[0]
        j,cnt=0,1
        for i in range(1,len(chars)+1):
            if i==len(chars) or chars[i]!=pre:
                chars[j]=pre
                if cnt>1:
                    cntS = str(cnt)
                    chars[j+1:j+1+len(cntS)] = cntS
                    j+=1+len(cntS)
                else: j+=1
                if i<len(chars): pre=chars[i]
                cnt=1
            else: cnt+=1
        return j
