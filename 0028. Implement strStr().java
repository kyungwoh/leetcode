/*
28. Implement strStr()
스트링 매치 알고리듬. 외워야 한다.
일치하지 않았을 때 얼마나 뒤로 돌아갈지 위치를 기억해두는 KMP 알고리듬과,
슬라이딩 윈도우 + 롤링 해쉬로 단 한번에 비교하는 라빈카프 알고리듬이 있다.

그 전에 패턴이 빈 경우 뭘 리턴할지, 패턴이 더 길 경우 바로 0을 리턴할지 확인한다.
패턴 길이만큼 남았는데도 못 찾았으면 바로 리턴한다. 그것만 해도 O((m-n)n)이 된다. O(mn)보다 빨라진다.

KMP는 pre[]를 만들어놔야 한다. Pre[0] = 0이고, i=1, j=0 부터 비교한다.
일단 불일치하는지 확인하고, 불일치한다면 일치할때까지 기록해놓았던 pre 배열을 이용해 계속 뒤로 따라 따라 간다. While (불일치한다면) { j = pre[j-1]; }
그래서 결국 더 돌아갈 자리가 없거나 일치하는 걸 찾을 때까지 돌아간 다음에, 일치한다면 j++ 한 칸 전진하고 pre[i] = j 저장.
그러니까 웬만하면 j++이 잘 안되고 거의 0을 찍게 되는데, 혹시라도 패턴에 반복되는 부분이 있다면 숫자가 좀 올라갈 수 있다.
극단적으로 aaaaaaa 같으면 012345… 이렇게 한 칸씩만 뒤로 가도록 저장해놓을 것이다.

하여튼 이렇게 pre[]를 다 만들면, 패턴을 하나씩 찾아가다가 틀리면 j = pre[j-1] 로 돌아가면 된다.

라빈카프는 해쉬값을 잘 만들어야 한다.
A-z 26개라면 sum = sum * 26 + digit 로 한 자리씩 밀어내가는데 (26진수인 셈이다) 오버플로우가 발생하지 않도록 % mod 로 감싸준다.
Long으로 한다면 long_max/26 을 하면 되는데, 넉넉하게 int_max 정도로 해도 된다.
근데 이렇게 mod를 쓰면 혹시라도 해쉬는 같은데 실제 문자열은 다를 수도 있으므로, hashCode() + equals() 로 한번 더 확인해야 한다.
그래서
1. 패턴의 해쉬, 타겟의 해쉬를 쭉 만든다. 패턴 길이까지.
2. 일단 둘이 같은지 보고, 같으면 첫번째 인덱스인 0을 리턴한다. 맨 앞에서 일치했다는 거니까.
3. 그럼 그 패턴 길이 다음부터, 왼쪽 건 빼고 오른쪽 건 더해가면서 해쉬를 롤링한다.
오른쪽 거는 그냥 더해주면 되는데, 왼쪽 거 뺄 때는 26^m(패턴의 길이)를 곱해서 빼줘야 하니까 이건 미리 구해놓자.

Math.pow()를 쓰면 편할...줄 알았는데 26씩 곱하면서 오버플로우가 날 수 있으니까 매번 % mod로 감싸야 한다.
*/
class Solution {
    public int strStr(String haystack, String needle) {
        if (needle.isEmpty()) return 0;
        for (int i = 0, j = 0; i < haystack.length(); i++) {
            if (haystack.charAt(i) == needle.charAt(j)) {
                j++;
                if (j == needle.length()) return i - j + 1;
            } else {
                i -= j;
                j = 0;
            }
        }
        return -1;
    }
}
class Solution {
    public int strStr(String haystack, String needle) {
        int hLen = haystack.length(), nLen = needle.length();
        if (nLen == 0) return 0;
        if (hLen == 0 || hLen < nLen) return -1;
        
        // make a pre pattern
        int[] pre = new int[nLen];
        pre[0] = 0;
        for (int i = 1, j = 0; i < nLen; i++) {
            while (j>0 && needle.charAt(i) != needle.charAt(j)) j = pre[j-1];
            if (needle.charAt(i) == needle.charAt(j)) j++;
            pre[i] = j;
        }
        
        // search
        for (int i = 0, j = 0; i < hLen; i++) {
            while (j>0 && haystack.charAt(i) != needle.charAt(j)) j = pre[j-1];
            if (haystack.charAt(i) == needle.charAt(j)) j++;
            if (j == nLen) return i-j+1;
        }
    
        return -1;
    }
}
class Solution {
    public int strStr(String haystack, String needle) {
        int hLen = haystack.length(), nLen = needle.length();
        if (nLen == 0) return 0;
        if (hLen == 0 || hLen < nLen) return -1;
        
        // make a first hash
        long mod = (long)Integer.MAX_VALUE;
        long nHash = 0, hHash = 0;
        for (int i = 0; i < nLen; i++) {
            nHash = (nHash*26 + toLong(needle,i))%mod;
            hHash = (hHash*26 + toLong(haystack,i))%mod;
        }
        if (nHash == hHash && needle.equals(haystack.substring(0, nLen))) return 0;
        
        // rolling hash
        long lastDigit = 1;
        for (int i = 0; i < nLen; i++) lastDigit = (lastDigit*26)%mod;
        for (int i = 0, j = nLen; j < hLen; i++, j++) {
            hHash = (hHash*26 - toLong(haystack,i)*lastDigit + toLong(haystack,j))%mod;
            if (nHash == hHash && needle.equals(haystack.substring(i+1,j+1))) return i+1;
        }
        
        return -1;
    }
    private long toLong(String s, int i) {
        return (long)s.charAt(i) - (long)'a';
    }
}