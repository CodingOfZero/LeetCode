package Backtrack;

import java.util.LinkedList;
import java.util.List;

public class RestoreIPAddresses_93 {
    static final int SEG_COUNT=4;
    int[] segments=new int[SEG_COUNT];

    public List<String> restoreIpAddresses(String s) {
        char[] str = s.toCharArray();
        List<String> ans=new LinkedList<>();
        dfs(s,0,ans,0);
        return ans;
    }
    private void dfs(String s,int start,List<String> ans,int segId){
        //如果找到了4段IP地址
        if(segId==SEG_COUNT){
            //并且遍历完字符串，记录答案
            if(start==s.length()){
                StringBuilder sb=new StringBuilder();
                for(int i=0;i<SEG_COUNT;i++){
                    sb.append(segments[i]);
                    if(i!=SEG_COUNT-1){
                        sb.append('.');
                    }
                }
                ans.add(sb.toString());
            }
            return;
        }
        if(start==s.length()){
            return;
        }
        //处理前导0,如果为0，这一段IP只能为0
        if(s.charAt(start)=='0'){
            segments[segId]=0;
            dfs(s,start+1,ans,segId+1);
        }
        int addr=0;
        for(int i=start;i<s.length();i++){
            addr=addr*10+(s.charAt(i)-'0');
            if(addr>0&&addr<=0xFF){
                segments[segId]=addr;
                dfs(s,i+1,ans,segId+1);
            }else{
                break;
            }
        }
    }

    public static void main(String[] args) {
        RestoreIPAddresses_93 test = new RestoreIPAddresses_93();
        List<String> strings = test.restoreIpAddresses("25525511135");
        strings.forEach(System.out::println);

    }
}
