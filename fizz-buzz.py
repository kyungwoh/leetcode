# https://leetcode.com/problems/fizz-buzz/description/
# Time: O(n), Space: O(n)
class Solution:
    def fizzBuzz(self, n):
        rList = []
        for i in range(1, n+1):
            s = ""
            if i%3==0: s += "Fizz"
            if i%5==0: s += "Buzz"
            if s=="": s = str(i)
            rList.append(s)
        return rList
        
