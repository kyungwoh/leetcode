// https://leetcode.com/problems/basic-calculator/description/
// https://leetcode.com/problems/basic-calculator-ii/description/
// https://leetcode.com/problems/basic-calculator-iii/description/
// If it's postfix, it's easy. Just use stack.
// But this is infix. infix -> postfix takes time.
// So just use infix as it is.
// Time: O(n), Space: O(n)
class Solution {
    int i = 0;
    public int calculate(String s) {
        return calculate2(s);
    }
    private int calculate2(String s){        
        int l = s.length();
        if(l==0) return 0;
        int ans = 0, num = 0;
        char lastOp = '+';
        ArrayDeque<Integer> deq = new ArrayDeque<Integer>();
        while(true){
            char c = s.charAt(i);
            if(c=='('){ i++; num = calculate2(s); }
            else if(Character.isDigit(c)) num = num*10 + Character.getNumericValue(c);
            //System.out.println(i+" "+c+" "+num+" "+lastOp+" "+deq);
            if((!Character.isDigit(c) && c!=' ') || c==')'|| i==(l-1)){
                if(lastOp=='+') deq.push(num);
                else if(lastOp=='-') deq.push(-num);
                else if(lastOp=='*') deq.push(deq.pop()*num);
                else if(lastOp=='/') deq.push(deq.pop()/num);
                
                if(c==')' || i==(l-1)){
                    while(!deq.isEmpty()) ans += deq.pop();
                    return ans;
                }
                lastOp = c;
                num = 0;                
            }
            i++;
        }
    }
}
