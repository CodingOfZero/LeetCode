package Heap;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;

/**
 * 在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 */
public class KthLargestElementInAnArray_215 {
    /**
     * 快速选择+快排
     * 如果我们选择的中枢恰好是第k个元素，则刚好就是要求的结果
     * 如果小于k，则在右边区间递归取，否则，在左边区间递归取
     * @param nums
     * @param k
     * @return
     */
    private static final Random random=new Random();
    public static int findKthLargestFaster(int[] nums, int k) {
        return quickSelect(nums,0, nums.length-1, nums.length-k);
    }
    private static int quickSelect(int[] nums,int l,int r,int index){
        //q为下标
        int q=randomPartition(nums,l,r);
        if(q==index){
            return nums[q];
        }else{
            return q<index?quickSelect(nums,q+1,r,index):quickSelect(nums, l, q-1, index);
        }
    }
    private static int randomPartition(int[] nums, int l, int r) {
        int i=random.nextInt(r-l+1)+l;
        swap(nums,i,l);
        return partition(nums,l,r);
    }

    private static void swap(int[] nums, int l, int r) {
        int temp=nums[l];
        nums[l]=nums[r];
        nums[r]=temp;
    }
    private static int partition(int[] nums, int l, int r){
        int x=nums[l];
        while (l<r){
            while (l<r&&nums[r]>=x) {
                r--;
            }
            nums[l]=nums[r];
            while (l<r&&nums[l]<=x){
                l++;
            }
            nums[r]=nums[l];
        }
        nums[l]=x;
        return l;
    }

    /**
     * 利用的最大堆 时间O(nlogn)
     * @param nums
     * @param k
     * @return
     */
    public static int findKthLargest(int[] nums, int k) {
        //默认是最小堆
        //Comparator.reverseOrder()返回与自然数相反的比较器
        PriorityQueue<Integer> pq=new PriorityQueue<>(Comparator.reverseOrder());
        for(Integer element:nums){
            pq.add(element);
        }
        int res=0;
        for(int i=0;i<k;i++){
            if(!pq.isEmpty()){
                res=pq.poll();
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int i = findKthLargest(new int[]{3, 2, 1, 5, 6, 4}, 2);
        System.out.println(i);
    }
}
