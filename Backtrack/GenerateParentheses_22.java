package Backtrack;

import java.util.LinkedList;
import java.util.List;

/**
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 * 先构建树，再编码
 * 构建一个二叉树，树高为2*n
 */
public class GenerateParentheses_22 {
    private int left=0;
    private int right=0;
    public List<String> generateParenthesis(int n) {
        List<String> res=new LinkedList<>();
        char[] par=new char[2*n];
        parenthesisHelper(par,res,n,0);
        return res;
    }
    private void parenthesisHelper(char[] par,List<String> res,int n,int cur){
        if(cur==par.length){
            res.add(new String(par,0,par.length));
            return;
        }
        if(left<n){
            par[cur]='(';
            left++;
            parenthesisHelper(par,res,n,cur+1);
            left--;
        }
        if(right<left){
            par[cur]=')';
            right++;
            parenthesisHelper(par,res,n,cur+1);
            right--;
        }
    }
}
