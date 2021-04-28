package DoublePointer;

import java.util.HashSet;
import java.util.Set;

public class SumofSquareNumbers_633 {
    //令a=0,b=根号c，sum=a^2+b^2
    //利用二分法，如果sum==c return true;
    //如果大于c，令 b减一，计算sum
    //如果小于c，令a加1，计算sum
    //时间复杂度为 O(sqrt(n))
    public boolean judgeSquareSumTwoPoint(int c){

        int left=0,right=(int)Math.sqrt(c);
        while (left<=right){
            int sum=left*left+right*right;
            if(sum==c){
                return true;
            }else if(sum<c){
                left++;
            }else {
                right--;
            }
        }
        return false;
    }


    public boolean judgeSquareSumBF(int c){
         for(long a=0;a*a<=c;a++){
             double b=Math.sqrt(c-a*a);
             if(b==(int)b){
                 return true;
             }
         }
         return false;
    }

    public boolean judgeSquareSum(int c) {
        if(c<=1) return true;
        int mid=c/2;
        Set<Integer> hashSet=new HashSet<>();
        for(int i=0;i<=mid;i++){
            int pow=(int)Math.pow(i,2);
            if(pow>c){break;}
            hashSet.add(pow);
            int sub=c-pow;
            if(hashSet.contains(sub)){
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        SumofSquareNumbers_633 test = new SumofSquareNumbers_633();
        boolean b = test.judgeSquareSum(2);
        boolean b1 = test.judgeSquareSum(3);
        boolean b2 = test.judgeSquareSum(4);
        boolean b3 = test.judgeSquareSum(5);
        System.out.println(b);
        System.out.println(b1);
        System.out.println(b2);
        System.out.println(b3);

    }
}
