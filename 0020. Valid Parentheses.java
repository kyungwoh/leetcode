/*
20. Valid Parentheses
그냥 괄호 열고 닫는 갯수만 쓰면 hierarchy를 찾을 수 없다.

Stack을 써서 기존 순서를 저장해야 한다.
뺄 때 앞의 것과 현재 것이 아귀가 맞는지 확인한다.
아니면 빼야 하는데 스택이 비었던지, 마지막까지 갔는데 스택이 안 비었는지를 확인한다.

괄호가 대칭이므로 가능하면 if 문에서 대칭이 되도록 짠다.


모든 경우의 수를 따져보는 수밖에 없다 -> DFS -> 트리 형태로 테스트해볼 것
시작 -> 중간 -> 종료 케이스 순서대로
2개씩 나눠지니 O(2^n)
*/
class Solution {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else {
                if (stack.size() == 0) return false;
                char b = stack.peek();
                if ((b == '(' && c == ')') ||
                    (b == '{' && c == '}') ||
                    (b == '[' && c == ']')) {
                    stack.pop();
                } else {
                    return false;
                }
            }
        }
        return stack.size() == 0;
    }
}

class Solution {
    public boolean isValid(String s) {
        LinkedList<Character> stack = new LinkedList<>();
        for (int i =0; i<s.length(); i++) {
            char c = s.charAt(i);
            if (c=='(' || c=='{' || c=='[') stack.addLast(c);
            else if (stack.size() == 0) return false;
            else {
                char lastC = stack.removeLast();
                if (lastC=='(' && c!=')') return false;
                if (lastC=='{' && c!='}') return false;
                if (lastC=='[' && c!=']') return false;
            }
        }
        return stack.size() == 0;
    }
}