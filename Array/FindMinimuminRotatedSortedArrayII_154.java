package Array;

/**
 * 在旋转数组（将一个递增数组前部分放到后面形成了两个有序的子数组）中找最小值，可能有重复
 */
public class FindMinimuminRotatedSortedArrayII_154 {
    public static int findMin_v1(int[] nums) {
        if(nums==null||nums.length==0) throw new IllegalArgumentException("error");
        int p1=0;
        int p2=nums.length-1;
        int mid=p1;
        while (nums[p1]>=nums[p2]){
            if(p2-p1==1){
                mid=p2;
                break;
            }
            mid=(p1+p2)/2;
            if(nums[p1]==nums[p2]&&nums[p2]==nums[mid])
                return findInOrder(nums,p1,p2);
            if(nums[mid]>=nums[p1])
                p1=mid;
            else if(nums[mid]<=nums[p2])
                p2=mid;
        }
        return nums[mid];
    }
    private static int findInOrder(int[] nums,int start,int end){
        int result=nums[start];
        for(int i=start+1;i<=end;i++)
            if(nums[i]<result)
                result=nums[i];
        return result;
    }
/*********************************************************************************/
    public int findMin(int[] nums) {
        int low = 0;
        int high = nums.length - 1;

        while (low < high) {
            int pivot = low + (high - low) / 2;
            if (nums[pivot] < nums[high]) {
                high = pivot;
            } else if (nums[pivot] > nums[high]) {
                low = pivot + 1;
            } else {
                high -= 1;
            }
        }
        return nums[low];
    }
}
