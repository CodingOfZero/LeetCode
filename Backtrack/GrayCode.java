package Backtrack;

import java.util.LinkedList;
import java.util.List;

/**
 * 生产格雷码
 */
public class GrayCode {
    private int num=0;
    public  List<Integer> grayCode(int n) {
        if(n<0) {
            return null;
        }
        List<Integer> res=new LinkedList<>();
        grayCodeHelper(res,n);
        return res;
    }
    private  void grayCodeHelper(List<Integer> res,int n){
        if(n==0){
            res.add(num);
            return;
        }
        //忽略
        grayCodeHelper(res,n-1);
        //反转
        num=num^(1<<n-1);
        grayCodeHelper(res,n-1);
    }

    public static void main(String[] args) {
        List<Integer> integers = new GrayCode().grayCode(1);
        integers.forEach(System.out::println);
    }
}
