/*
721. Accounts Merge
union-find로 풀어야 하는데 map을 써야 한다.
map<email, account> account 안에 set<email>, name, parent를 넣는다.

일단 각 Account 별로 account 객체를 만든다.
그리고 그것의 email을 하나씩 돌면서 map에 key=email이 있는지 찾아서, 있으면 union한다. 그리고 map.put() 한다.

이렇게 돌고 나면 Account들이 다 합쳐져 있을 것이다.
문제는 map의 key가 아니라 value로 들어있는 거라, map을 돌면서 distinct account를 해야 한다. 이건 set을 쓰면 된다.
emails.sort()도 필요하다.
*/
class Solution {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<String, Account> map = new HashMap<>();
        for (List<String> account : accounts) {
            Account a = new Account(account.get(0));
            for (int i = 1; i < account.size(); i++) {
                String email = account.get(i);
                a.emails.add(email);
                if (map.containsKey(email)) a = a.union(map.get(email).find());
                map.put(email, a);
            }
        }
        List<List<String>> res = new ArrayList<>();
        Set<Account> set = new HashSet<>();
        for (Account a : map.values()) {
            Account p = a.find();
            if (set.contains(p)) continue;
            set.add(p);
            List<String> account = new ArrayList<>();
            account.add(p.name);
            List<String> emails = new ArrayList<>(p.emails);
            Collections.sort(emails);
            account.addAll(emails);
            res.add(account);
        }
        return res;
    }
    
}
class Account {
    String name;
    Set<String> emails;
    Account p;
    Account(String name) {
        this.name = name;
        emails = new HashSet<>();
        p = this;
    }
    Account union(Account a) {
        if (a == this) return a;
        p = a;
        p.emails.addAll(emails);
        return p;
    }
    Account find() {
        if (p == this) return p;
        return p = p.find();
    }
}