package Math;

import java.math.BigDecimal;

public class Sqrt_x_69 {
    /**
     * 二分法,隔根区间
     * @param x
     * @return
     */
    public int mySqrt(int x) {
        if(x<=1) return x;
        int a=0,b=x;
        while(b-a>1){
            //int min=a+(b-a)>>1; 未考虑运算符的优先级，而导致错误发生，+-运算符优先级比移位高
            int min=(b+a)/2;
            int res=fun(min,x);
            if(res==0){
                return min;
            }else if(res>0){
                b=min;
            }else{
                a=min;
            }
        }
        return a;
    }
    private int fun(int x,int c){
        int res= (int)(Math.pow(x,2)-c);
        return Integer.compare(res, 0);
    }

    /**
     * 牛顿迭代法
     */
    public int mySqrtNewton(int x) {
        if(x==0){
          return 0;
        }
        double C=x,x0=x;
        double dis;
        do{
            double xi=0.5*(x0+C/x0);
            dis=Math.abs(xi-x0);
            x0=xi;
        }while(dis>1e-6);
        return (int)x0;
    }
    public static void main(String[] args) {
        Sqrt_x_69 test = new Sqrt_x_69();
        int i = test.mySqrtNewton(8);
        System.out.println(i);
    }
}
