package Backtrack;


import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 打印所有的回文字符
 */
public class PalindromicPartitionsOfAString {
    /**
     * 将字符串转为字符数组，然后进行处理
     * @param s
     * @return
     */
    public List<List<String>> partitionFaster(String s){
        List<List<String>> res=new LinkedList<>();
        if(s==null||s.isEmpty()){
            return res;
        }
        LinkedList<String> cur=new LinkedList<>();


        char[] chars = s.toCharArray();
        partitionUtilFaster(chars,0,chars.length,res,cur);
        return res;
    }

    private void partitionUtilFaster(char[] chars, int start, int end, List<List<String>> res, LinkedList<String> cur) {
        if(start==end){
            res.add(new LinkedList<>(cur));
            return;
        }
        for(int i=start;i<end;i++){
            if(!check(chars,start,i)){
                continue;
            }
            cur.addLast(new String(chars,start,i-start+1));
            partitionUtilFaster(chars,i+1,end,res,cur);
            cur.removeLast();
        }
    }

    private boolean check(char[] chars, int start, int end) {
        while (start<end){
            if(chars[start]!=chars[end]){
                return false;
            }
            start++;
            end--;
        }
        return true;
    }


    /**
     * 加入动态规划的回溯
     * @param s
     * @return
     */
    public List<List<String>> partitionDP(String s){
        int len = s.length();
        List<List<String>> res=new LinkedList<>();
        LinkedList<String> cur=new LinkedList<>();
        if(len==0){
            return res;
        }

        boolean[][] state=new boolean[len][len];
        for(boolean[] temp:state){
            Arrays.fill(temp,false);
        }
        //state[i][j] 表示 s[i][j] 是否是回文
        //用空间换时间
        for(int right=0;right<len;right++){
            //left==right在于单个字符也是回文
            for(int left=0;left<=right;left++){
                if(s.charAt(left)==s.charAt(right)&&(right-left<=2||state[left+1][right-1])){
                    state[left][right]=true;
                }
            }
        }
        partitionUtil(s,0,len,state,res,cur);
        return res;
    }

    private void partitionUtil(String s, int start, int end, boolean[][] state, List<List<String>> res, LinkedList<String> cur) {
        if(start==end){
            res.add(new LinkedList<>(cur));
            return;
        }
        for(int i=start;i<end;i++){
            if(!state[start][i]){
                continue;
            }
            cur.addLast(s.substring(start,i+1));
            partitionUtil(s,i+1,end,state,res,cur);
            cur.removeLast();
        }
    }


    /**
     * 回溯
     * @param s
     * @return
     */
    public List<List<String>> partition(String s){
        int len=s.length();
        List<List<String>> res=new LinkedList<>();
        LinkedList<String> cur=new LinkedList<>();
        if(len==0){
            return res;
        }
        partitionHelper(s,0,len,res,cur);
        return res;
    }

    private void partitionHelper(String str, int start, int end, List<List<String>> res, LinkedList<String> cur) {
        if(start==end){
            res.add(new LinkedList<>(cur));
            return;
        }
        for(int i=start;i<end;i++){
            if(!checkPalindrom(str,start,i)){
                continue;
            }
            cur.add(str.substring(start,i+1));
            partitionHelper(str,i+1,end,res,cur);
            cur.removeLast();
        }
    }

    private boolean checkPalindrom(String str, int left, int right) {
        while (left<right){
            if(str.charAt(left)!=str.charAt(right)){
                return false;
            }
            left++;
            right--;
        }
        return true;
    }



    private void printSolution(List<List<String>> list){
        for (List<String> next : list) {
            for (String res : next) {
                System.out.printf("%s ", res);
            }
            System.out.println();
        }
    }

    /**
     * 判断是否是回文
     * @param str
     * @return
     */
    private boolean isSafe(String str){
        int j=str.length()-1;
        int i=0;
        while (i<j){
            if(str.charAt(i)!=str.charAt(j)){
                return false;
            }
            i++;
            j--;
        }
        return true;
    }
    public static void main(String[] args)
    {
        String s = "cbbbcc";
        PalindromicPartitionsOfAString partitions = new PalindromicPartitionsOfAString();
        List<List<String>> result = partitions.partitionDP(s);
        partitions.printSolution(result);

    }
}
