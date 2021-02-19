package SlidingWindow;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 求解滑动窗口内的中位数,k为窗口的长度
 * 当k为偶数时，中位数为中间两个数的中位数
 * 当k为奇数时，中位数为中间那个数
 * 创建一个数据结构DualHeap,该结构有insert、erase、getMedian()接口
 */
public class SlidingWindowMedian_480 {
    /**
     *
     * @param nums 数字
     * @param k 窗口长度
     * @return 中位数数组
     */
    public double[] medianSlidingWindow(int[] nums, int k) {
        if(nums==null||nums.length==0) {
            return new double[0];
        }
        DualHeap dh= new DualHeap(k);
        for(int i=0;i<k;i++){
            dh.insert(nums[i]);
        }
        double[] ans=new double[nums.length-k+1];
        ans[0]=dh.getMedian();
        for(int i=k;i<nums.length;i++){
            dh.insert(nums[i]);
            dh.erase(nums[i-k]);
            ans[i-k+1]=dh.getMedian();
        }
        return ans;
    }
    class DualHeap{
        //大根堆，维护较小的一半元素
        private PriorityQueue<Integer> small;
        //小根堆，维护较大的一半元素
        private PriorityQueue<Integer> large;
        //哈希表，记录 延迟删除的元素， key为元素，value为需要删除的次数
        private Map<Integer,Integer> delayed;
        //窗口大小
        private int capacity;
        // small 和 large 当前包含的元素个数，需要扣除被「延迟删除」的元素
        private int smallSize, largeSize;

        public DualHeap(int capacity){
            this.small=new PriorityQueue<>((o1, o2) -> Integer.compare(o2,o1));
            this.large=new PriorityQueue<>((o1, o2) -> Integer.compare(o1,o2));
            this.capacity=capacity;
            this.delayed=new HashMap<>();
            this.smallSize=0;
            this.largeSize=0;
        }
        public void insert(int num){
            if(small.isEmpty()||num<=small.peek()){
                small.offer(num);
                ++smallSize;
            }else{
                large.offer(num);
                ++largeSize;
            }
            makeBalance();
        }
        public void erase(int num){
            //延迟删除
            delayed.put(num,delayed.getOrDefault(num,0)+1);
            if(num<=small.peek()){
                --smallSize;
                if(num==small.peek()){
                    prune(small);
                }
            }else{
                --largeSize;
                if(num==large.peek()){
                    prune(large);
                }
            }
            makeBalance();
        }
        //small、large两者之间的元素个数要么相同，要么small比large多1个
        public double getMedian(){
            return (capacity&1)==1?small.peek():((double)small.peek()+large.peek())/2;
        }
        // 不断地弹出 heap 的堆顶元素，并且更新哈希表
        //当我们需要移出优先队列中的某个元素时，我们只将这个删除操作「记录」下来，
        // 而不去真的删除这个元素。当这个元素出现在 small 或者 large 的堆顶时，
        // 我们再去将其移出对应的优先队列
        private void prune(PriorityQueue<Integer> heap) {
            while (!heap.isEmpty()) {
                int num = heap.peek();
                if (delayed.containsKey(num)) {
                    delayed.put(num, delayed.get(num) - 1);
                    if (delayed.get(num) == 0) {
                        delayed.remove(num);
                    }
                    heap.poll();
                } else {
                    break;
                }
            }
        }
        // 调整 small 和 large 中的元素个数，使得二者的元素个数满足要求
        // 当插入一个元素时，会有以下两种不合法情况
        //      small 比 large 的元素个数多了 2 个；
        //      small 比 large 的元素个数少了 1 个；
        private void makeBalance() {
            if(smallSize>largeSize+1){
                //多2个,将small大根堆，堆顶元素弹出放到large中去
                large.offer(small.poll());
                --smallSize;
                ++largeSize;
                // small 堆顶元素被移除，需要进行 prune
                // 为了保证堆顶元素一定有效，不是延迟删除的元素
                prune(small);
            }else if(smallSize<largeSize){
                //少1个
                small.offer(large.poll());
                smallSize++;
                largeSize--;
                // large 堆顶元素被移除，需要进行 prune
                prune(large);
            }
        }
    }
}
