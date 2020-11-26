package Backtrack;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个字符串，在不使用任何正则表达式的情况下，找出字符串是否遵循给定模式。
 * 示例：
 *  Input:
 * string - GraphTreesGraph
 * pattern - aba
 * Output:
 * a->Graph
 * b->Trees
 *
 * Input:
 * string - GraphGraphGraph
 * pattern - aaa
 * Output:
 * a->Graph
 */
public class MatchAPattern {
    /**
     * 判断是否匹配
     * @param str 字符串
     * @param pat 模式
     * @param n   字符串长度
     * @param m 模式长度
     * @return 能否匹配
     */
    public static boolean patternMatch(String str,String pat,int n,int m){
        if(n<m){
            return false;
        }
        Map<Character,String> map=new HashMap<>();
        boolean res=patternMatchHelper(str,n,0,pat,m,0,map);
        if(res){
            map.forEach((k,v)-> System.out.println(k+"--->"+v));
        }
        return res;
    }

    /**
     * @param str 字符串
     * @param n 字符串长度
     * @param i 当前字符串下标
     * @param pat  模式
     * @param m  模式长度
     * @param j  当前模式下标
     * @param map 存放模式字符对应字符串
     * @return 是否匹配
     */
    private static boolean patternMatchHelper(String str,
                                              int n,
                                              int i,
                                              String pat,
                                              int m,
                                              int j,
                                              Map<Character, String> map) {
        //如果字符串和模式都到了末尾
        if(i==n&&j==m){
            return true;
        }
        if(i==n||j==m){
            return false;
        }
        char curPat=pat.charAt(j);;
        //如果在map中已经存在当前模式的字符对应的字符串
        if(map.containsKey(curPat)){
            //取出模式对应的字符串
            String s = map.get(curPat);
            int len=s.length();
            //取出字符串中对应长度的字符串
            String substr=null;
            if(len+i<=n){
                substr=str.substring(i,len+i);
            }else {
                return false;
            }
//            String substr=str.substring(i,len+i);
            //比较两个字符串是否相同,不同表示此次失败
            if(!s.equals(substr)){
                return false;
            }
            //如果匹配，递归处理剩下字符串
            return patternMatchHelper(str,n,i+len,pat,m,j+1,map);
        }
        //如果第一次遇见,处理方式与分词那个问题相似
        //len为当前字符串的长度
        for(int len=1;len<n-i;len++){
            //考虑从位置i开始长度为len的子串，并将其添加到map中
            String substr=str.substring(i,len+i);
            map.put(curPat,substr);
            //查看是否能够产生一个解决方案
            if(patternMatchHelper(str,n,i+len,pat,m,j+1,map)){
                return true;
            }
            //如果不能，从map中删除该模式对应的字符串
            map.remove(curPat);
        }
        return false;
    }

    public static void main(String[] args) {
        String str = "GeeksforGeeks", pat = "GfG";

        int n = str.length(), m = pat.length();

        if (!patternMatch(str, pat, n, m)) {
            System.out.println("No Solution exists");
        }
    }

}
