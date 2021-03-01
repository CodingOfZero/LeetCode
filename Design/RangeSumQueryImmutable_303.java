package Design;

/**
 * 设计一个NumArray类
 * 实现求得给定区间和
 * 由于多次调用检索，需将检索的时间复杂度降为O(1)
 *
 * 由sum(i,j)=sum(0,j)-sum(0,i-1);
 * 对数据进行预处理，sum(i)表示sum(0,i-1)的前缀和
 */
public class RangeSumQueryImmutable_303 {
    private final int[] sums;
    //初始化O(n)
    public RangeSumQueryImmutable_303(int[] nums){
        int n=nums.length;
        sums=new int[n+1];
        for(int i=0;i<n;i++){
            sums[i+1]=sums[i]+nums[i];
        }

    }
    //时间复杂度为O(1)
    public int sumRange(int i, int j) {
        return sums[j+1]-sums[i];
    }
}
