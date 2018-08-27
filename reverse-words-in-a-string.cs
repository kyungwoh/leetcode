// https://leetcode.com/problems/reverse-words-in-a-string/description/
public class Solution {
    // In-place
    // Time: O(n), Space: O(1)
    public string ReverseWords(string s) {
        char[] c = s.ToCharArray();
        
        //1. trim right & reverse all
        int max=c.Length-1;
        if(max>=0){
            while(max>0 && c[max]==' ') max--;
            Reverse(c,0,max);

            //2. find the word & trim spaces in between
            int l=0, r=0, l2=0;
            while(r<=max){
                while(r<=max && c[r]!=' '){ r++; l2++; }
                Reverse(c,l,r-1);
                l=++l2;
                while(r<=max && c[r]==' ') r++;
            }
            
            //3. trim left
            while(max>=0 && c[max]==' ') max--;
            Array.Resize(ref c, max+1);
        }
        return new string(c);
    }
    private void Reverse(char[] c, int s, int e){
        Console.WriteLine(s+","+e);
        for(; s<e; s++,e--) (c[s],c[e])=(c[e],c[s]);
    }
    
    // Stack
    // Time: O(n), Space: O(n)
    public string ReverseWords2(string s) {
        LinkedList<char> stack = new LinkedList<char>();
        StringBuilder sb = new StringBuilder();
        for(int i=s.Length-1; i>=-1; i--){
            char c = i>=0 ? s[i] : ' ';
            if(c!=' ') stack.AddLast(c);
            else if(stack.Count>0){
                if(sb.Length!=0) sb.Append(' ');
                while(stack.Count>0){
                    sb.Append(stack.Last.Value);
                    stack.RemoveLast();
                }
            }
        }
        return sb.ToString();
    }
}
