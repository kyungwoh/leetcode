/*
227. Basic Calculator II
이게 operator가 끝에 오는 postfix가 아니라 중간에 오는 infix이기 때문에, last operator를 저장했다 써야 한다.
그리고 operator가 새로 추가될때만이 아니라 배열의 맨 끝에서도 last operator 처리를 해야 한다.

더하고 빼는 건 우선순위가 낮으니 그냥 순서대로 스택에 넣으면 되는데,
곱하고 나누는 건 우선순위가 높아서 미리 계산해서 스택에 넣어야 한다. pop() 해서 곱하거나 나눈 다음 다시 push한다.

맨 마지막에는 스택 값들을 모두 더하면 된다.

괄호가 있을 경우에는 recursive하게 푼다. 괄호가 열릴때 들어가고, 닫힐때 return한다. index는 따로 관리.
*/
class Solution {
    int i = 0;
    public int calculate(String s) {
        int num = 0;
        char lastc = '+';
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            
            if (c >= '0' && c <= '9') {
               num = 10 * num + (int)(c - '0');
            }
            
            if (c == '+' || c == '-' || c == '*' || c == '/' || i == s.length() - 1) {
                if (lastc == '+') stack.push(num);
                if (lastc == '-') stack.push(-num);
                if (lastc == '*') stack.push(stack.pop() * num);
                if (lastc == '/') stack.push(stack.pop() / num);
                lastc = c;
                num = 0;
            }
        }
        
        int sum = 0;
        while (!stack.isEmpty()) sum += stack.pop();
        return sum;
    }
}

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