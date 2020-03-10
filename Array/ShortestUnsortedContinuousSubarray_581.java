package Array;
import java.util.Arrays;
public class ShortestUnsortedContinuousSubarray_581 {
    public int findUnsortedSubarray(int[] nums) {
        int[] sorted =nums.clone();
        Arrays.sort(sorted);
        int head=0,tail=0;
        int i=0,dis=0;
        /*
        从前扫描，找到第一个不同的数字位置，为头部
         */
        while(i<nums.length) {
            if(nums[i] != sorted[i]){
                head=i;
                break;
            }else
                i++;
        }
        /*
        从后扫描，找到第一个不同的数字位置，为尾部
         */
        i=nums.length-1;
        while(i>=0) {
            if(nums[i] != sorted[i]){
                tail=i;
                break;
            }else
                i--;
        }
        dis=tail-head;
        if(dis<=0) return 0;
        return dis+1;
    }

    public static void main(String[] args) {
        int[] nums={1,2,3,4};
        int i = new ShortestUnsortedContinuousSubarray_581().findUnsortedSubarray(nums);
        System.out.println(i);
    }
}
