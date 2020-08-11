package Array;

/**
 * 在有序数组中找到指定目标，返回它第一次出现和最后出现的位置，如果不存在，则为（-1，-1）
 * 时间复杂度应为O(logn)
 * 考虑二分查找
 */
public class FindFirstandLastPositionofElementinSortedArray_34 {
    public int[] searchRange(int[] nums, int target) {
        int [] index=new int[2];
        index[0]=-1;
        index[1]=-1;
        searchRangeFirst(nums,0,nums.length-1,target,index);
        searchRangeLast(nums,0,nums.length-1,target,index);
        return index;
    }

    private void searchRangeLast(int[] nums, int start, int end, int target,int [] index) {
        int mid;
        while (start<=end){
            mid=(start+end)/2;
            if(target>nums[mid]){
                start=mid+1;
            }else if(target<nums[mid]){
                end=mid-1;
            }else{
                if(mid==end||nums[mid]!=nums[mid+1]){
                    index[1]=mid;
                    break;
                }
                else
                    start=mid+1;
            }
        }
    }
    private void searchRangeFirst(int[] nums, int start, int end, int target,int [] index) {
        while (start<=end){
            int mid=(start+end)/2;
            if(target>nums[mid]){
                start=mid+1;
            }else if(target<nums[mid]){
                end=mid-1;
            }else{
                if(mid==start||nums[mid]!=nums[mid-1]){
                    index[0]=mid;
                    break;
                }
                else
                    end=mid-1;
            }
        }
    }

    public static void main(String[] args) {
        int[] nums={5,7,7,8,8,10};
        int target=8;
        FindFirstandLastPositionofElementinSortedArray_34 t=new FindFirstandLastPositionofElementinSortedArray_34();
        int[] ints = t.searchRange(nums, target);
        for(int i:ints)
            System.out.println(i);
    }
}
