/*
957. Prison Cells After N Days
조합이 2^8=256 밖에 없으므로 반드시 cycle이 생길 것이다. 현재 array의 index를 저장해놓고 되풀이되었을때 계산해서 fast-forward 하면 된다. 마침 2^8 밖에 안되므로 bitmap으로 가능하다. //= 1, |= 0,1 그리고 남은 것은 모듈러로 구한다. 시작점을 포함해야 하니 i=0 i<=N 기준으로 len = i - last; rem = N - i; rem2 = rem % len; i = N - rem2; 정 생각이 안나면 while (i + len <= N) i += len;
*/
class Solution {
    public int[] prisonAfterNDays(int[] curr, int N) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i <= N; i++) {
            if (i > 0) {
                int[] next = new int[8];
                for (int j = 0; j < 8; j++) {
                    next[j] = j > 0 && j < 7 && curr[j-1] == curr[j+1] ? 1 : 0;
                }
                curr = next;
            }
            
            int hash = bitmap(curr);
            if (map.containsKey(hash)) {
                int len = i - map.get(hash);
                int rem = N - i;
                int rem2 = rem % len;
                i = N - rem2;
            } else {
                map.put(hash, i);
            }
        }
        return curr;
    }
    private int bitmap(int[] curr) {
        int hash = 0;
        for (int i = 0; i < 8; i++) {
            hash *= 2;
            hash += curr[i];
        }
        return hash;
    }
}
class Solution {
    public int[] prisonAfterNDays(int[] curr, int N) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i <= N; i++) {
            if (i > 0) {
                int[] next = new int[8];
                for (int j = 0; j < 8; j++) {
                    next[j] = j > 0 && j < 7 && curr[j-1] == curr[j+1] ? 1 : 0;
                }
                curr = next;
            }
            
            int hash = bitmap(curr);
            if (map.containsKey(hash)) {
                int len = i - map.get(hash);
                int rem = N - i;
                int rem2 = rem % len;
                i = N - rem2;
            } else {
                map.put(hash, i);
            }
        }
        return curr;
    }
    private int bitmap(int[] curr) {
        int hash = 0;
        for (int i = 0; i < 8; i++) {
            hash <<= 1;
            hash |= curr[i];
        }
        return hash;
    }
}
class Solution {
    public int[] prisonAfterNDays(int[] curr, int N) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < N; i++) {
            int[] next = new int[8];
            for (int j = 0; j < 8; j++) {
                next[j] = j > 0 && j < 7 && curr[j-1] == curr[j+1] ? 1 : 0;
            }
            curr = next;
            
            int hash = getHash(curr);
            if (map.containsKey(hash)) {
                int len = i - map.get(hash);
                while (i + len < N) i += len;
            } else {
                map.put(hash, i);
            }
        }
        return curr;
    }
    private int getHash(int[] curr) {
        int hash = 0;
        for (int i = 0; i < 8; i++) {
            hash <<= 2;
            hash += curr[i];
        }
        return hash;
    }
}