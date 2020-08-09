/*
49. Group Anagrams
애너그램인지를 한번에 찾아서 묶으려면 맵의 키를 만들어야 하는데, 가장 단순한 방법은 String 조합이다. “#1#2#333” 이렇게 절대 겹치지 않는 문자열을 만들면 된다.
아니면 직접 클래스를 만들어 hashCode()와 equals()를 override하면 된다.
문제는 hashCode()를 잘 만들어야 하는데, 단순히 prime number를 곱해주면서 밀어내면 무슨 숫자가 들어와도 비교적 evenly distributed된다.
그냥 적당한 소수 하나 17, 31 등을 곱해주거나 다양한 소수 배열 [2,3,7,11,13,17,23….]을 쓰면 더 좋다.
*/
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String s : strs) {
            int[] cnts = new int[26];
            for (char c : s.toCharArray()) cnts[(int)(c - 'a')]++;
            
            StringBuilder sb = new StringBuilder();
            for (int c : cnts) sb.append(',').append(c);
            String key = sb.toString();
            
            if (map.containsKey(key)) {
                map.get(key).add(s);
            } else {
                List<String> list = new ArrayList<>();
                list.add(s);
                map.put(key, list);
            }
        }
        List<List<String>> ans = new ArrayList<>();
        for (Map.Entry<String, List<String>> e : map.entrySet()) {
            ans.add(e.getValue());
        }
        return ans;
    }
}
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<Anag, List<String>> map = new HashMap<>();
        for (String s : strs) {
            Anag a = new Anag(s);
            if (map.containsKey(a)) {
                map.get(a).add(s);
            } else {
                List<String> list = new ArrayList<>();
                list.add(s);
                map.put(a, list);
            }
        }
        List<List<String>> ans = new ArrayList<>();
        for (Map.Entry<Anag, List<String>> e : map.entrySet()) {
            ans.add(e.getValue());
        }
        return ans;
    }
}
class Anag {
    int[] cnts;
    int hash;
    Anag(String s) {
        cnts = new int[26];
        for (char c : s.toCharArray()) {
            cnts[(int)(c - 'a')]++;
        }
        hash = 0;
        for (int c : cnts) {
            hash = (hash * 31 + c) % 1000000007;
        }
    }
    @Override public int hashCode() {
        return hash;
    }
    @Override public boolean equals(Object o) {
        Anag a = (Anag) o;
        for (int i = 0; i < 26; i++) {
            if (cnts[i] != a.cnts[i]) return false;
        }
        return true;
    }
}
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<Anag, List<String>> map = new HashMap<>();
        for (String s : strs) {
            Anag a = new Anag(s, primes);
            List<String> list = map.get(a);
            if (list == null) {
                list = new ArrayList<>();
                map.put(a, list);
            }
            list.add(s);
        }
        List<List<String>> res = new ArrayList<>();
        for (Map.Entry<Anag, List<String>> e : map.entrySet()) res.add(e.getValue());
        return res;
    }
    public static int[] primes = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101};
}
class Anag {
    int[] cnts = new int[26];
    int hash = 1;
    Anag(String s, int[] primes) {
        for (int i = 0; i < s.length(); i++) cnts[s.charAt(i) - 'a']++;
        for (int i = 0; i < 26; i++) if (cnts[i] > 0) hash *= primes[i];
    }
    @Override public boolean equals(Object o) {
        Anag a = (Anag)o;
        for (int i = 0; i < 26; i++) if (cnts[i] != a.cnts[i]) return false;
        return true;
    }
    @Override public int hashCode() {
        return hash;
    }
}