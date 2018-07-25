// https://leetcode.com/problems/fizz-buzz/description/
// Time: O(n), Space: O(n)
class Solution {
    public List<String> fizzBuzz(int n) {
        List<String> r = new ArrayList<String>();
        for(int i=1; i<=n; i++){
            StringBuilder sb = new StringBuilder();
            if(i%3==0) sb.append("Fizz");
            if(i%5==0) sb.append("Buzz");
            if(i%3!=0 && i%5!=0) sb.append(Integer.toString(i));
            r.add(sb.toString());
        }
        return r;
    }
}
