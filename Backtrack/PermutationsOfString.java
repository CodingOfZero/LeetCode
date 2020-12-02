package Backtrack;

import java.util.Arrays;

/**
 * 字符串的全排列
 * 有n!个
 */
public class PermutationsOfString {

    public static void permute(String str,int l,int r){
        if(l==r){
            System.out.println(str);
        }else{
            for(int i=l;i<=r;i++){
                str=swap(str,l,i);
                permute(str,l+1,r);
                str=swap(str,l,i);
            }
        }
    }



    public static void arrangement(String str,String res){
        int len=str.length();
        if(len==0){
            System.out.println(res);
            return;
        }
        for(int i=0;i<str.length();i++){
            String swapStr=swap(str,0,i);
            String temp="";
            if(swapStr.length()>1){
                temp=swapStr.substring(1);
            }
            arrangement(temp,res+str.charAt(i));
        }
    }

    private static String swap(String str, int j, int i) {
        if(j==i){
            return str;
        }
        char[] chars = str.toCharArray();
        char c1=chars[j];
        chars[j]=chars[i];
        chars[i]=c1;
        return String.valueOf(chars);
    }

    public static void main(String[] args) {
        String str="abc";
        permute(str,0,str.length()-1);
    }
}
