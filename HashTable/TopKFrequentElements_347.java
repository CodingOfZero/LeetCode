package Heap;

import java.util.*;

/**
 * 给定一个非空的整数数组，返回其中出现频率前 k 高的元素。
 * 你可以假设给定的k总是合理的，且 1 ≤ k ≤ 数组中不相同的元素的个数。
 * 你的算法的时间复杂度必须优于 O(n log n) ,n是数组的大小。
 * 题目数据保证答案唯一，换句话说，数组中前 k 个高频元素的集合是唯一的。
 * 你可以按任意顺序返回答案。
 */
public class TopKFrequentElements_347 {
    /**
     * 时间复杂度O(nlogk)
     * @param nums
     * @param k
     * @return
     */
    public int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer,Integer> hashMap=new HashMap<>();
        for (int num : nums) {
            Integer freq = hashMap.getOrDefault(num, 0);
            hashMap.put(num, freq + 1);
        }
        //int[]  第一个元素代表数组的值，第二个元素代表出现的频率
        PriorityQueue<int[]> queue=new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1]-o2[1];
            }
        });
        //小根堆
        for(Map.Entry<Integer,Integer> entry:hashMap.entrySet()){
            int num= entry.getKey(),frep= entry.getValue();
            if(queue.size()==k){
                if(queue.peek()[1]<frep){
                    queue.poll();
                    queue.add(new int[]{num,frep});
                }
            }else{
                queue.add(new int[]{num,frep});
            }
        }
        int[] res=new int[k];
        for(int i=0;i<k;i++){
            res[i]=queue.poll()[0];
        }
        return res;
    }
}
