package Math;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 字符串的全排列，可能存在重复字符，同样需要排序后判重
 * 1.先画递归树，确定算法签名
 * 2.确定结束条件
 * 3.确定选择列表
 * 4.查看是否需要剪枝
 * 5.做出选择
 * 6.撤销选择
 */
public class PermutationOfString_38_Offer {
    private List<String> res;
    public String[] permutation(String s) {
        if(s==null||s.length()==0) return new String[0];
        char[] c=s.toCharArray();
        //排序
        Arrays.sort(c);
        boolean[] visited=new boolean[c.length];
        res=new LinkedList<>();
        funHelper(c,new StringBuilder(),visited);
        return res.toArray(new String[0]);
    }

    private void funHelper(char[] c, StringBuilder sb, boolean[] visited) {
        if(sb.length()==c.length){
            res.add(sb.toString());
            return;
        }
        for(int i=0;i<c.length;i++){
            if(!visited[i]){
                //剪枝，比如[1,2,2]出现重复原因在于第二次使用2，还可以使用第一次的2
                if(i>0&&c[i]==c[i-1]&&!visited[i-1]){
                    //条件含义：有两个相等的情况下，第一个还可以使用
                    continue;
                }
                sb.append(c[i]);
                visited[i]=true;
                funHelper(c,sb,visited);
                visited[i]=false;
                sb.deleteCharAt(sb.length()-1);
            }
        }
    }

    public static void main(String[] args) {
        PermutationOfString_38_Offer test = new PermutationOfString_38_Offer();
        String[] abcs = test.permutation("abc");
        Arrays.stream(abcs).forEach(System.out::println);
    }
}
