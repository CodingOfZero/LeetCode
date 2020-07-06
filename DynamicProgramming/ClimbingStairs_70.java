package Array;

/**
 * 爬楼梯：斐波那契数列应用
 */
public class ClimbingStairs_70 {
    public static int climbStairs(int n) {
        if(n<=0) return 0;
        int[]  result={1,2};
        if(n<=2) return result[n-1];
        int firstOne=1;
        int firstTwo=2;
        int temp=-1;
        for(int i=2;i<n;i++){
            temp=firstOne+firstTwo;
            firstOne=firstTwo;
            firstTwo=temp;
        }
        return temp;
    }

    public static void main(String[] args) {
        System.out.println(climbStairs(3));
    }
}
