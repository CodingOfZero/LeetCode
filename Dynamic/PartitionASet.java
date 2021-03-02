package Dynamic;

/**
 * 给定一个集合，将其分为两个子集和，求它们和的最小绝对值。假设集合有n个，其中一个子集合有m个元素，则另一个子集合有n-m个元素
 */
public class PartitionASet {


    /**
     * 递归，找出集合所有可能的和，然后从中选择差值最小的
     * 时间复杂度O(2^n) 每一位有两种选择，加入第一个集合或者不加入，所以为2^n
     * @param arr 集合
     * @param i 下标
     * @param sumCalculated 第一个子集的和
     * @param sumTotal 给定集合的和
     * @return 两个子集最小绝对差
     */
    public static int finMinRecur(int[] arr,int i,int sumCalculated,int sumTotal){
        if(i==0){
            return Math.abs((sumTotal-sumCalculated)-sumCalculated);
        }
        return Math.min(
                finMinRecur(arr,i-1,sumCalculated+arr[i-1],sumTotal)
                ,finMinRecur(arr,i-1,sumCalculated,sumTotal)
        );
    }
    public static int finMin(int[] arr){
        int sumTotal=0;
        for(int i:arr) {
            sumTotal+=i;
        }
        return finMinRecur(arr,arr.length,0,sumTotal);
    }

    /**
     * 由于每次递归找，会有重复计算，所以直接把结果自下而上都记录在一张表里面
     * boolean[][] table=new boolean[n+1][sumTotal+1] 行数为集合元素个数 ，列数为从0到集合总和个数
     * 对于从0-i的集合，如果有子集元素和等于j则[i][j]为true，否则为false
     * table制作完成后，我们从 j=sum/2处开始寻找，如果存储的值为true 计算diff=sum-2*j;
     * 想法来自： sum of S1 is j and it should be closest
     * to sum/2, i.e., 2*j should be closest to sum.
     */
    public static int finMinDP(int[] arr){
        int sumTotal=0;
        for(int i:arr) {
            //计算集合总和
            sumTotal+=i;
        }
        int n=arr.length;
        boolean[][] table=new boolean[n+1][sumTotal+1];
        //将第一列初始化为true，和为0,对于所有元素而言都可能
        for(int i=0;i<=n;i++){
            table[i][0]=true;
        }
        //初始化第一行为false，如果集合为空，则和只能为0
        for(int j=1;j<=sumTotal;j++){
            table[0][j]=false;
        }
        for(int i=1;i<=n;i++){
            for(int j=1;j<=sumTotal;j++){
                //第i个元素不在第一个集合内
                table[i][j]=table[i-1][j];
                //第i个元素在第一个集合内
                if(arr[i-1]<=j){
                    table[i][j]=table[i-1][j-arr[i-1]];
                }
            }
        }
        int diff=Integer.MAX_VALUE;
        for(int j=sumTotal/2;j>=0;j--){
            if(table[n][j]){
                diff=sumTotal-2*j;
                break;
            }
        }
        return diff;
    }

    public static void main(String[] args)
    {
        int arr[] = {3, 1, 4, 2, 2, 1};
        int n = arr.length;
        System.out.print("The minimum difference"+
                " between two sets is " +
                finMinDP(arr));
    }
}
