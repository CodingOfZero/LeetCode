package UnionFind;

import java.util.*;

/**
 * 邮箱账户合并
 * 同一个账户里的多个邮箱一定是同一个人，不同账户如果存在相同邮箱，说明也是同一个人
 */
public class AccountsMerge_721 {
    private class UnionFind{
        int[] id;
        int count;
        public UnionFind(int n){
            this.count=n;
            id=new int[n];
            for(int i=0;i<n;i++){
                id[i]=i;
            }
        }
        public void union(int u,int v){
            int uRoot=find(u);
            int vRoot=find(v);
            if(vRoot==uRoot) return;
            id[vRoot]=uRoot;
            count--;
        }

        private int find(int u) {
            int root=u;
            while (root!=id[root]) root=id[root];
            int temp=0;
            while (u!=root){
                temp=id[u];
                id[u]=root;
                u=temp;
            }
            return root;
        }
    }
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        //邮箱对应id号
        Map<String,Integer> emailToIndex=new HashMap<>();
        Map<String,String> emailToName=new HashMap<>();
        //建立关系
        int emailCount=0;
        for(List<String> account:accounts){
            String name=account.get(0);
            int size=account.size();
            for(int i=1;i<size;i++){
                String email = account.get(i);
                if(!emailToIndex.containsKey(email)){
                    emailToIndex.put(email,emailCount++);
                    emailToName.put(email,name);
                }
            }
        }
        UnionFind uf=new UnionFind(emailCount);
        //将同一个用户的所有邮箱连通
        for(List<String> account:accounts){
            String firstEmail=account.get(1);
            int firstIndex = emailToIndex.get(firstEmail);
            int size=account.size();
            for(int i=2;i<size;i++){
                String nextEmail = account.get(i);
                int nextIndex = emailToIndex.get(nextEmail);
                uf.union(firstIndex,nextIndex);
            }
        }
        //索引对应的邮箱
        Map<Integer,List<String>> indexToEmail=new HashMap<>();
        for(String email: emailToIndex.keySet()){
            int index=uf.find(emailToIndex.get(email));
            List<String> account = indexToEmail.getOrDefault(index, new ArrayList<>());
            account.add(email);
            indexToEmail.put(index,account);
        }
        //处理结果
        List<List<String>> ans=new ArrayList<>();
        for(List<String> emails: indexToEmail.values()){
            Collections.sort(emails);
            String name = emailToName.get(emails.get(0));
            List<String> accout=new ArrayList<>();
            accout.add(name);
            accout.addAll(emails);
            ans.add(accout);
        }
        return ans;
    }



}
