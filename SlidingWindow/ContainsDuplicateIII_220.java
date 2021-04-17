package SlidingWindow;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

public class ContainsDuplicateIII_220 {
    /**
     * 如果有k范围内有相同的，则一定成立
     * [i-t,i+t]//如果有序集合中有大于或等于i-t，且小于或等于i+t则返回true
    */
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        int n=nums.length;
        TreeSet<Long> set=new TreeSet<>();
        //维护一个大小为k的窗口
        for(int i=0;i<n;i++){
            //大于或等于(long) nums[i] - (long) t
            //set.higher()表示大于
            Long ceiling = set.ceiling((long) nums[i] - (long) t);
            if(ceiling!=null&&ceiling<=(long)nums[i]+(long)t){
                return true;
            }
            set.add((long)nums[i]);
            if(i>=k){
                //收缩窗口
                set.remove((long)nums[i-k]);
            }
        }
        return false;
    }

    /**
     * 桶排序
     * 桶的大小为t+1（比如计算与A生日相差一个月的人数，那么与A当月一定是，A生日月相邻的月份也有可能）
     * 注意：不是说分了0-t个桶，而是每个桶内数据最多差t
     * 1.位于同一个桶的元素一定在abs(nums[i]-nums[j])<=t
     * 2.相邻的桶可能出现abs(nums[i]-nums[j])<=t
     * 3.不相邻的桶一定不会出现abs(nums[i]-nums[j])<=t， 比如0号桶最大值和2号桶最小值差了t+1
     */
    public boolean containsNearbyAlmostDuplicateBucket(int[] nums, int k, int t) {
        int n=nums.length;
        Map<Long,Long> map=new HashMap<>();
        long w=(long) t+1;
        for(int i=0;i<n;i++){
            long id=getId(nums[i],w);
            if(map.containsKey(id)){
                return true;
            }
            if(map.containsKey(id-1)&&Math.abs(nums[i]-map.get(id-1))<w){
                return true;
            }
            if(map.containsKey(id+1)&&Math.abs(nums[i]-map.get(id+1))<w){
                return true;
            }
            map.put(id,(long)nums[i]);
            if(i>=k){
                map.remove(getId(nums[i-k],w));
            }
        }
        return false;
    }

    private long getId(int num, long w) {
        if(num>=0){
            //示例，比如w=10, 0-9为一组，10-19为另一组
            return num/w;
        }
        //对于负数进行处理
        //示例比如 w=10 -1~-10为一组，如果使用num/w，会被分为2组，id为0和-1组，所以先加1，变为 -0~-9 和正数一样表示一组
        //然后-1，放到id为-1的组内
        return (num+1)/w-1;
    }

    public static void main(String[] args) {
        ContainsDuplicateIII_220 test = new ContainsDuplicateIII_220();
        boolean b = test.containsNearbyAlmostDuplicateBucket(new int[]{1,2,3,1}, 3, 0);
        System.out.println(b);
    }
}
