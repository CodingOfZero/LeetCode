package WeekendGame;

import java.util.HashMap;
import java.util.Map;

public class Game_241 {
    public Game_241(){}

    /**
     * 1863. 找出所有子集的异或总和再求和
     */
    public int subsetXORSum(int[] nums) {
        return dfs(nums,0,0);
    }
    private int dfs(int[] nums,int cur,int index){
        if(index== nums.length){
            return cur;
        }
        return dfs(nums, cur^nums[index], index+1)+dfs(nums,cur,index+1);
    }

    /**
     * 5760. 构成交替字符串需要的最小交换次数
     */
    public int minSwaps(String s) {
        int n=s.length();
        char[] str = s.toCharArray();
        int zero=0;
        for(char c:str){
            if(c=='0') {
                zero++;
            }
        }
        /**
         * 相差大于1，不满足条件直接返回-1
         */
        if(Math.abs(n-2*zero)>1){
            return -1;
        }
        int ans=Integer.MAX_VALUE;
        //统计偶数位上1的个数,从第0位开始
        int one=0;
        for(int i=0;i<str.length;i+=2){
            if(str[i]=='1'){
                one++;
            }
        }
        if(n%2==0){
            //如果是偶数，有两种排序方式0101.. 或 1010...
            //两者取最小
            //假设0开头，统计偶数位上1的个数，从第0位开始
            //假设1开头，偶数位是0的个数有n/2-one
            ans=Math.min(one,n/2-one);
        }else{
            if(zero>n-zero){
                ans=one;
            }else{
                ans=n/2+1-one;
            }
        }
        return ans;
    }



    /**
     * 5761. 找出和为指定值的下标对
     * 通过题目可得，num1的范围为0-1000，num2的范围为0-10的5次，将num2使用map来代替
     */
    private Map<Integer, Integer> nums2Dict;
    private int[] nums1;
    private int[] nums2;
    public Game_241(int[] nums1, int[] nums2) {
        this.nums1=nums1;
        this.nums2=nums2;
        nums2Dict=new HashMap<>();
        for (int num : nums2) {
            nums2Dict.put(num, nums2Dict.getOrDefault(num, 0)+1);
        }
    }

    public void add(int index, int val) {
        int pre=nums2[index];
        nums2[index]+=val;
        nums2Dict.put(nums2[index],nums2Dict.getOrDefault(nums2[index],0)+1);
        nums2Dict.put(pre,nums2Dict.get(pre)-1);
    }
    public int count(int tot) {
        int cnt=0;
        for(int num:nums1){
            int target=tot-num;
            if(nums2Dict.containsKey(target)){
                cnt+=nums2Dict.get(target);
            }
        }
        return cnt;
    }

    /**
     * 1866. 恰有 K 根木棍可以看到的排列数目
     * dp[i][j] ：有i根木棍能看到j根木根的方案数，i根木棍相比于i-1根木棍仅仅是多了第i个木棍
     * 求解[1,i-1]中插入第i根木棍能看到j根木棍的方案数 等价于 在[2,i]中插入长度为1的木棍能看到j根木棍的方案
     * 当长度为1的木棍放在最前面时才能被看到  dp[i][j]+=dp[i-1][j-1]
     * 除此之外，还有i-1种放置位置，dp[i][j]+=dp[i-1][j]*(i-1)
     * 两者合并即为状态转移方程
     *
     * 该题与斯特林公式是一样的
     */
    public int rearrangeSticks(int n, int k) {
        long[][] solution=new long[n+1][k+1];
        solution[1][1]=1;
        long M=(long)1e9+7;
        for(int i=2;i<=n;i++){
            for(int j=1;j<=Math.min(k,i);j++){
                solution[i][j]=solution[i-1][j-1];
                solution[i][j]+=solution[i-1][j]*(i-1);
                solution[i][j]%=M;
            }
        }
        return (int)solution[n][k];
    }
    public static void main(String[] args) {
        Game_241 test2 = new Game_241();
        int i = test2.subsetXORSum(new int[]{3, 4, 5, 6, 7, 8});
        System.out.println(i);
        int i1 = test2.rearrangeSticks(20, 11);
        System.out.println(i1);
    }
}
