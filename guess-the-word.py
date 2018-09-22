# https://leetcode.com/problems/guess-the-word/description/
# Find the most possible candidates repetatively (Minimax)
class Solution:
    def findSecretWord(self, wordlist, master):
        cand = wordlist
        while True:
            pick = cand[random.randint(0,len(cand)-1)]
            diff = master.guess(pick)
            if diff==6: return
            else:
                newCand = []
                for c in cand:
                    newDiff = 0
                    for i in range(6):
                        if pick[i]==c[i]: newDiff+=1
                    if diff==newDiff: newCand.append(c)
                cand = newCand
