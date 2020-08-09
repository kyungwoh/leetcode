/*
158. Read N Characters Given Read4 II - Call multiple times

페북이 좋아하는 library 구현 문제다. 딱히 아이디어가 필요하진 않고, 주어진 그대로 풀면 된다.
boilerplate 코드이기 때문에 반복적일 수 있는데, 의도가 그게 맞으니 개의치 말고 푼다.

이 문제의 tricky point는, 지난번에 읽다 말았던 부분을 마저 읽어야 한다는 점이다.
그래서 읽던 4 bytes buffer, len, pointer i를 남겨놔야 마저 읽을 수 있다.
그리고 시작하기 전에 읽단 마저 읽고 시작해야 한다.

그 다음 trikcy point는, read4()로 4 bytes씩 읽어야 하기 때문에 for문이 2개 필요하다.
1. read4()를 읽는 loop (4 bytes)
2. read4() 결과를 1 byte씩 읽는 loop
*/
/**
 * The read4 API is defined in the parent class Reader4.
 *     int read4(char[] buf); 
 */

public class Solution extends Reader4 {
    /**
     * @param buf Destination buffer
     * @param n   Number of characters to read
     * @return    The number of actual characters read
     */
    char[] b = new char[4];
    int i = 0, len = 0;
    public int read(char[] buf, int n) {
        int j = 0;
        for (; i < len && j < n;) buf[j++] = b[i++];
        while(j < n) {
            len = read4(b);
            for(i = 0; i < len && j < n;) buf[j++] = b[i++];
            if (len < 4) break;
        }
        return j;
    }
}