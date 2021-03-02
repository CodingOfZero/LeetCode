package Dynamic;

/**
 * 最长递增子序列
 * 递推公式
 * L(i) = 1 + max( L(j) ) where 0 < j < i and arr[j] < arr[i]; or
 * L(i) = 1, if no such j exists.
 */
public class LongestIncreasingSubsequence {
    //以下为递归实现，时间复杂度为指数级
    private  int maxLis;
    public  int lis(int[] arr,int n){
        maxLis=0;
        lisHelper(arr,n);
        return maxLis;
    }
    private int lisHelper(int[] arr, int n) {
        //base case
        if(n==1) {
            return 1;
        }
        int maxLisTemp=1,res;
        /* 递归得到所有以arr[0]、arr[1]...arr[n-2]结尾的所有LIS。
           如果 arr[i-1] 小于 arr[n-1] ，且以arr[n-1]结尾的max需要更新
           则更新 */
        for(int i=1;i<n;i++){
            res=lisHelper(arr,i);
            if(arr[i-1]<arr[n-1]&&res+1>maxLisTemp){
                maxLisTemp=res+1;
            }
        }
        //如果比全局记录长度长，则更新
        if(maxLisTemp>maxLis) {
            maxLis=maxLisTemp;
        }
        //返回以arr[n-1]结尾的最大递增子序列
        return maxLisTemp;
    }

    /**
     * 动态规划,自底向上,时间O(n^2)
     * @param arr
     * @param n
     * @return
     */
    public static int lisBottomUp(int[] arr, int n){
        int[] lis=new int[n];
        int i,j,max=0;
        for(i=0;i<n;i++){
            lis[i]=1;
        }
        //自底向上，填充数组
        for(i=1;i<n;i++){
            for(j=0;j<i;j++){
                if(arr[j]<arr[i]&&lis[j]+1>lis[i])
                    lis[i]=lis[j]+1;
            }
        }
        //选出最大的
        for(i=0;i<n;i++){
            if(max<lis[i])
                max=lis[i];
        }
        return max;
    }

    /**
     * 二分加贪心算法
     * 1.假设最大序列的末端元素是E，如果有一个元素A[j] (j > i)，
     * 使得E < A[i] < A[j] （进行添加）或 A[i] < A[j] < E (进行替换)，我们可以将当前元素A[i]添加(替换)到现有序列中。
     * 2.当我们在数组中遇到新的最小的元素时，它可以成为一个潜在的候选者来开始新的序列。
     */
    public static int lengthOfLIS(int[] nums) {
        int size=nums.length;
        if(size==0) return 0;
        int[] table=new int[size];
        int len=1;
        table[0]=nums[0];
        for(int i=1;i<size;i++){
            if(nums[i]<table[0])
                table[0]=nums[i];
            else if(nums[i]>table[len-1])
                table[len++]=nums[i];
            else{
                table[binarySearch(table,0,len,nums[i])]=nums[i];
            }
        }
        return len;
    }
    private static int binarySearch(int[] nums,int l,int r,int key){
        //未命中就返回最右边 区间[)
        int mid=0;
        while(r>l){
            mid=l+(r-l)/2;
            if(nums[mid]>key)
                r=mid;
            else if(nums[mid]<key)
                l=mid+1;
            else
                return mid;
        }
        return r;
    }


    public static void main(String args[])
    {
        int arr[] = { 2, 5, 3, 7, 11, 8, 10, 13, 6 };
        int n = arr.length;
        System.out.println("Length of lis is "
                + lengthOfLIS(arr) + "\n" );
    }

}
