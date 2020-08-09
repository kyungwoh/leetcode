/*
468. Validate IP Address
Pattern으로 풀 수도 있지만 그걸 원하진 않을 것 같고,
if 문으로 하나씩 전진해가면서 찾아야 하니까 코드가 길어질 수 밖에 없다.

*/
class Solution {
    public String validIPAddress(String s) {
        String neither = "Neither";
        if (s == null || s.isEmpty()) return neither;
        if (s.contains(".")) {
            // IPv4
            if (s.startsWith(".") || s.endsWith(".")) return neither;
            String[] ss = s.split("\\.");
            if (ss == null || ss.length != 4) return neither;
            for (String s0 : ss) {
              if (s0 == null || s0.isEmpty() || (s0.charAt(0) == '0' && s0.length() != 1) || s0.length() > 3) return neither;
              for (int i = 0; i < s0.length(); i++) {
                char c = s0.charAt(i);
                if (c < '0' || c > '9') return neither;
              }
              try {
                int i = Integer.parseInt(s0);
                if (i < 0 || i > 255) return neither;
              } catch (NumberFormatException e) {
                return neither;
              }
            }
            return "IPv4";
        } else if (s.contains(":")) {
            // IPv6
            if (s.startsWith(":") || s.endsWith(":")) return neither;
            String[] ss = s.split("\\:");
            if (ss == null || ss.length != 8) return neither;
            for (String s0 : ss) {
              if (s0 == null || s0.isEmpty() || s0.length() > 4) return neither;
              for (int i = 0; i < s0.length(); i++) {
                char c = s0.charAt(i);
                if ((c < '0' || c > '9') && (c < 'a' || c > 'f') && (c < 'A' || c > 'F')) return neither;
              }
            }
            return "IPv6";
        }
        return neither;
    }
}
import java.util.regex.Pattern;
class Solution {
    public String validIPAddress(String s) {
        Pattern p4 = Pattern.compile("(([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])\\.){3}([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])");
        Pattern p6 = Pattern.compile("([0-9a-fA-F]{1,4}\\:){7}([0-9a-fA-F]{1,4})");
        if (p4.matcher(s).matches()) return "IPv4";
        if (p6.matcher(s).matches()) return "IPv6";
        return "Neither";
    }
}