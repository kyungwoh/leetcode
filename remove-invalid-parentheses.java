// https://leetcode.com/problems/remove-invalid-parentheses/description/
class Solution {
    // DFS (2 path = forward + backward)
    // 1. When find invalid, go deep (possible valid cases)
    // 2. When find valid, go backward
    // 3. When find valid backward, add to result
    // Time: O(n^2), Space: O(n^2)
    public List<String> removeInvalidParentheses(String s) {
        List<String> rList = new ArrayList<String>();
        if(s==null) return rList;
        
        dfs(s, rList, 0, 0, true);
        return rList;
    }
    private void dfs(String s, List<String> rList, int lastI, int lastJ, boolean isForward){
        char open = isForward ? '(' : ')';
        char close = isForward ? ')' : '(';
        int cnt = 0;
        for(int i=lastI; i<s.length(); i++){
            if(s.charAt(i)==open) cnt++;
            if(s.charAt(i)==close) cnt--;
            
            if(cnt<0){
                for(int j=lastJ; j<=i; j++){
                    if(s.charAt(j)==close && (j==lastJ || s.charAt(j-1)!=close)){
                        dfs(s.substring(0,j)+s.substring(j+1), rList, i, j, isForward);
                    }
                }
                return;
            }
        }
        String sBack = new StringBuilder(s).reverse().toString();
        if(isForward){            
            dfs(sBack, rList, 0, 0, false);
        }else{
            rList.add(sBack);
        }
    }
    
    // BFS (1 path = forward)
    // Brute force (check all possibles) + memo (don't visit twice)
    // Time: O(n^2), Space: O(n^2)
    public List<String> removeInvalidParentheses2(String s) {
        List<String> rList = new ArrayList<String>();
        if(s==null) return rList;
        
        HashSet<String> visited = new HashSet<String>();
        ArrayDeque<String> que = new ArrayDeque<String>();
        que.offer(s);
        
        boolean isDone = false;
        while(!que.isEmpty()){
            String ss = que.poll();
            if(isValid(ss)){
                rList.add(ss);
                isDone = true;
            }else if(!isDone){
                for(int i=0; i<ss.length(); i++){
                    if(ss.charAt(i)=='(' || ss.charAt(i)==')'){
                        String sss = ss.substring(0,i) + ss.substring(i+1);
                        if(!visited.contains(sss)){
                            que.offer(sss);
                            visited.add(sss);
                        }                        
                    }
                }
            }
        }
        return rList;
    }
    private boolean isValid(String s){
        int cnt = 0;
        for(char c : s.toCharArray()){
            if(c=='(') cnt++;
            else if(c==')') cnt--;
            if(cnt<0) return false;
        }
        return cnt==0;
    }
}
