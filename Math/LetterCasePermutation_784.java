package Math;

import java.util.LinkedList;
import java.util.List;

/**
 * 字母大小写排列
 */
public class LetterCasePermutation_784 {
    public List<String> letterCasePermutation(String S) {
        if(S==null||S.length()==0) return new LinkedList<>();
        List<String> res=new LinkedList<>();
        char[] str=S.toCharArray();
        letterHelper(str,new StringBuilder(),res,0);
        return res;
    }

    private void letterHelper(char[] str, StringBuilder sb, List<String> res,int idx) {
        if(sb.length()==str.length){
            res.add(sb.toString());
            return;
        }
        //第一种选择，不变
        sb.append(str[idx]);
        letterHelper(str, sb, res, idx+1);
        sb.deleteCharAt(sb.length()-1);
        //如果是字符，有第二种选择，大写变为小写，小写为大写
        if(!Character.isDigit(str[idx])){
            if(str[idx]>='a'){
                sb.append((char)(str[idx] -32));
            }else{
                sb.append((char)(str[idx] +32));
            }
            letterHelper(str, sb, res, idx+1);
            sb.deleteCharAt(sb.length()-1);
        }
    }

    public static void main(String[] args) {
        LetterCasePermutation_784 test = new LetterCasePermutation_784();
        List<String> a1b2 = test.letterCasePermutation("c");
        a1b2.forEach(System.out::println);
    }

}
