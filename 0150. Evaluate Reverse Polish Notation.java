/*
150. Evaluate Reverse Polish Notation
폴란드 표기법(Polish notation, prefix) vs 역 폴란드 표기법 (Reverse Polish notation, postfix)
prefix, infix, postfix
prefix는 단순히 순서대로 쭉 계산해가면 된다.
postfix는 stack에 넣었던 값을 빼서 계산한 다음에 다시 stack에 얹어놓아야 한다.
*/
class Solution {
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        int ans = 0;
        for (String t : tokens) {
            if (t.equals("+")) {
                stack.push(stack.pop() + stack.pop());
            } else if (t.equals("-")) {
                stack.push(- stack.pop() + stack.pop());
            } else if (t.equals("*")) {
                stack.push(stack.pop() * stack.pop());
            } else if (t.equals("/")) {
                int a = stack.pop();
                int b = stack.pop();
                stack.push(b / a);
            } else {
                stack.push(Integer.parseInt(t));
            }
        }
        return stack.pop();
    }
}