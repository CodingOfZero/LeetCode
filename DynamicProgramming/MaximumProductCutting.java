package Dynamic;

/**
 * 切一段绳子，求它们最大乘积，假设至少切一次，绳子最少为2米
 * maxProd(n)=Max( i*(n-i), maxProd(n-i)*i)
 */
public class MaximumProductCutting {
    public static int maxProd(int n){
        if(n==0||n==1){
            return 0;
        }
        int maxVal=0;
        for(int i=1;i<n;i++){
            maxVal=Math.max(maxVal,
                    Math.max(i*(n-i),maxProd(n-i)*i));
        }
        return maxVal;
    }
    public static int maxProdDP(int n){
        //存放最大乘积
        int[] table=new int[n+1];

        table[0]=table[1]=0;
        for(int i=1;i<=n;i++) {
            int maxVal=0;

            for(int j=1;j<=i/2;j++){
                maxVal=Math.max(maxVal,Math.max(j*(i-j),table[i-j]*j));
            }
            table[i]=maxVal;
        }

        return table[n];
    }
    //将长度大于4的绳子 长度可能切成3  剩下就是长度为2，3，4
    public static int max(int n){
        if(n==2||n==3) {
            return n-1;
        }
        int res=1;
        while (n>4){
            n-=3;
            res*=3;
        }
        return (n*res);
    }
    public static void main(String[] args)
    {
        System.out.println("Maximum Product is "
                + max(10));
    }
}
