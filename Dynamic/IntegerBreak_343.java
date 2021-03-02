package Dynamic;

public class IntegerBreak_343 {
    public static int integerBreak(int n) {
        if(n==2) return 1;
        int[] dp=new int[n+1];
        dp[1]=1;
        dp[2]=1;
        int max=0;
        for(int i=3;i<=n;i++){
            for(int j=1;j<=i/2;j++){
                max=Math.max(max,Math.max(j,dp[j])*Math.max(i-j,dp[i-j]));
            }
            dp[i]=max;
        }
        max=dp[n];
        return max;
    }
    public static int integerBreak_2(int n) {
        if(n<2) return 0;
        if(n==2) return 1;
        if(n==3) return 2;

        int[] products=new int[n+1];
        products[0]=0;
        products[1]=1;
        products[2]=2;
        products[3]=3;
        int max;
        for(int i=4;i<n+1;i++){
            max=0;
            for(int j=1;j<=i/2;j++){
                int product=products[j]*products[i-j];
                if(max<product)
                    max=product;
            }
            products[i]=max;
        }
        max=products[n];
        return max;
    }
    //贪婪算法，尽可能多的表示长度为3
    public static int integerBreak_3(int n) {
        if(n<2) return 0;
        if(n==2) return 1;
        if(n==3) return 2;

        int timeof3=n/3;
        if(n-timeof3*3==1){
            timeof3-=1;
        }
        //当剩下为4，统计2的个数  因为2x2>3x1
        int timeof2=(n-timeof3*3)/2;
        return (int)Math.pow(3,timeof3)*(int)Math.pow(2,timeof2);
    }
    public static void main(String[] args) {
        int n=10;
        System.out.println(integerBreak_3(n));
    }
}
