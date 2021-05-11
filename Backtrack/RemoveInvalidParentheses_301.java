package Backtrack;

import java.util.*;

/**
 * 删除无效括号，给定字符串中字符种类有字母以及小括号
 */
public class RemoveInvalidParentheses_301 {
    public List<String> removeInvalidParentheses(String s) {
        char[] str = s.toCharArray();
        //统计无效的括号
        int left=0;
        int inValid=0;
        for(char c:str){
            if(c=='('){
                left++;
            }else if(c==')'){
                if(left!=0){
                    left--;
                }else{
                    inValid++;
                }
            }
        }
        inValid+=left;

        List<String> res=new LinkedList<>();
        //结果字符串的预计长度，原因在于要最小删除
        int target=str.length-inValid;
        if(target<=0){
            res.add("");
            return res;
        }
        dfs(str,target,new StringBuilder(),0,res,0);
        return res;
    }

    /**
     *
     * @param str  给定的字符串对应的字符数组
     * @param target 目标长度
     * @param cache 暂存遍历过程中的字符
     * @param index 当前处理下标
     * @param res 结果集合
     * @param left 左括号的个数
     */
    private void dfs(char[] str, int target, StringBuilder cache,int index,
                     List<String> res,int left) {
        if(target==cache.length()&&left==0){
            res.add(new String(cache));
            return;
        }
        for(int i=index;i<str.length;i++){
            char c=str[i];
            //去重
            if(i!=index&&c==str[i-1]){
                continue;
            }
            if(c==')'){
                //如果加入c，则不是一个合法字符串，提前返回
                if(left==0){
                    continue;
                }
                left--;
            }else if(c=='('){
                left++;
            }
            cache.append(str[i]);
            dfs(str, target, cache, i+1, res,left);
            cache.deleteCharAt(cache.length()-1);
            if(c=='('){
                left--;
            }else if(c==')'){
                left++;
            }
        }
    }

    /**
     * bfs,对于解，肯定处于同一层
     * 从给定字符串开始，去除一个字符，剩下的字符组成新的字符串，而去除的字符可以是字符串任意位置上的字符；对每个字符串进行判定
     *  第一层            ()())
     *  第二层  )()) (()) ())) ()() ()()
     *  ...
     * @param s
     * @return
     */
    public List<String> removeInvalidParenthesesBFS(String s) {
        List<String> res=new LinkedList<>();
        if(s==null||s.length()==0) {
            res.add("");
            return res;
        }
        //bfs的队列
        Deque<String> queue=new LinkedList<>();
        //去重集合
        Set<String> visited=new HashSet<>();
        queue.add(s);
        visited.add(s);
        //结果肯定在同一层,定义一个flag，当为true，处理完该层，直接退出
        boolean flag=false;
        while (!queue.isEmpty()){
            //最优解在同一层
            int size = queue.size();
            for(int i=0;i<size;i++){
                String cur = queue.pop();
                if(isValid(cur)){
                    flag=true;
                    res.add(cur);
                }
                int curWordLen=cur.length();
                char[] charArray = cur.toCharArray();
                for(int j=0;j<curWordLen;j++){
                    if(charArray[j]!='('&&charArray[j]!=')'){
                        continue;
                    }
                    String next=new String(charArray,0,j)+new String(charArray,j+1,curWordLen-j-1);
                    if(!visited.contains(next)){
                        queue.add(next);
                        visited.add(next);
                    }
                }
            }
            if(flag){
                break;
            }
        }
        return res;
    }
    private boolean isValid(String s){
        char[] str = s.toCharArray();
        int count=0;
        for(char c:str){
            if(c=='('){
                count++;
            }else if(')'==c){
                count--;
            }
            if(count<0){
                return false;
            }
        }
        return count==0;
    }
    public static void main(String[] args) {
        RemoveInvalidParentheses_301 test = new RemoveInvalidParentheses_301();
        List<String> strings = test.removeInvalidParentheses("a(");
        strings.forEach(System.out::println);
    }

}
