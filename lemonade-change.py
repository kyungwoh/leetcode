# https://leetcode.com/problems/lemonade-change/description/
# Give changes bigger->smaller (Greedy. Bigger is always better)
# Time: O(n), Space: O(1)
class Solution:
    def lemonadeChange(self, bills):
        cnt5,cnt10,cnt20 = 0,0,0
        for b in bills:
            if b==5: cnt5 += 1
            elif b==10: cnt10 += 1
            elif b==20: cnt20 += 1
            
            b -= 5
            if b//20>0 and b//20<=cnt20:
                cnt20 -= b//20
                b %= 20
            if b//10>0 and b//10<=cnt10:
                cnt10 -= b//10
                b %= 10
            if b//5>0:
                if b//5<=cnt5:
                    cnt5 -= b//5
                    b %= 5
                else: return False
        return True
        
