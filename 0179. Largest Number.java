/*
179. Largest Number
두 string (b+a) (a+b) 를 비교해서 sort하면 된다. max(len) = O(1), sort O(nlogn)
*/
class Solution {
    public String largestNumber(int[] nums) {
        List<String> list = new ArrayList<>();
        for (int n : nums) list.add(String.valueOf(n));
        list.sort((a,b)-> (b+a).compareTo(a+b));
        if (list.get(0).equals("0")) return "0";
        StringBuilder sb = new StringBuilder();
        for (String s : list) sb.append(s);
        return sb.toString();
    }
}