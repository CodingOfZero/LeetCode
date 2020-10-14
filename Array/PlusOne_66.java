package Array;


import java.util.Arrays;

/**
 * 将数组表示的数加1，数组长度为1到100，考虑进位问题，注意下第一位是否有进位即可
 */
public class PlusOne_66 {
    public static int[] plusOne(int[] digits) {
        int carry=0;
        int[] num=new int[digits.length+1];
        int fromIndex=digits.length-1,toIndex=digits.length;
        for(;fromIndex>=0;fromIndex--){
            int no=0;
            if(fromIndex==digits.length-1) {
                no=digits[fromIndex]+1+carry;
            }else {
                no=digits[fromIndex]+carry;
            }
            if(no>9){
                carry=1;
                num[toIndex--]=no%10;
            }else{
                carry=0;
                num[toIndex--]=no;
            }
        }
        if(carry==1) {
            num[0]=1;
            return Arrays.copyOfRange(num,0,num.length);
        }else {
            return Arrays.copyOfRange(num,1,num.length);
        }
    }

    public static void main(String[] args) {
        int[] digits={1,2,4};
        int[] ints = PlusOne_66.plusOne(digits);
        for (int i:ints) {
            System.out.println(i);
        }
    }
}
