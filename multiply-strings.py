# https://leetcode.com/problems/multiply-strings/description/
# 1. Multiply by each position & carry on
# 2. Sum all & carry on
# Time: O(len(n1)*len(n2)), Space: O(len(n1)*(len(n1)+len(n2))
class Solution:
    def multiply(self, num1, num2):
        if num1=="0" or num2=="0": return "0"
        else:
            r1 = num1[::-1]
            r2 = num2[::-1]
            sums = []
            sMaxLen = 0
            for i in range(len(r1)):
                c1 = int(r1[i])
                s = "" + "0"*i
                c = 0
                for c2 in r2:
                    c += c1 * int(c2)
                    s += str(c%10)
                    c //= 10
                while c>0:
                    s += str(c%10)
                    c //= 10
                sums.append(s)
                sMaxLen = max(sMaxLen, len(s))
            r3 = ""
            c = 0
            for i in range(sMaxLen):
                for s in sums:
                    c += 0 if len(s)<=i else int(s[i])
                r3 += str(c%10)
                c //= 10
            while c>0:
                r3 += str(c%10)
                c //= 10
            return r3[::-1]
