package Array;

/**
 * 给定一个包含 n + 1 个整数的数组 nums，其数字都在 1 到 n 之间（包括 1 和 n），可知至少存在一个重复的整数。假设只有一个重复的整数，找出
 * 这个重复的数。
 * 不能更改原数组（假设数组是只读的）。
 * 只能使用额外的 O(1) 的空间。
 * 时间复杂度小于 O(n2) 。
 * 数组中只有一个重复的数字，但它可能不止重复出现一次。
 * 二分查找O(nlogn)
 */
/**************************************使用二分查找****************************************/
public class FindtheDuplicateNumber_287 {
    public int findDuplicate(int[] nums) {
        if(nums==null||nums.length==0) return -1;
        int start=1;
        int end=nums.length-1;
        int mid,c;
        while (start<=end){
            mid=start+(end-start)/2;
            c=count(nums,start,mid);
            if(start==end){
                if(c>1) return start;
                else break;
            }
            if(c>mid-start+1){
                end=mid;
            }else{
                start=mid+1;
            }
        }
        return -1;
    }
    private int count(int[] nums,int start,int end){//[] O(n)
        int result=0;
        for (int num : nums)
            if (num >= start && num <= end)
                result++;
        return result;
    }
/**************************************************************************************/
    public static void main(String[] args) {
        int[] n0 = {1, 4, 5, 3, 2, 2};
        int[] n1 = {2, 2, 2, 2, 2};
        int[] n2 = {1, 4, 5, 4, 2, 2};
        int[] n3 = {};
        FindtheDuplicateNumber_287 k = new FindtheDuplicateNumber_287();
//        int r0 = k.findDuplicate(n0);
//        System.out.println(r0);
        int r1= k.findDuplicate(n1);
        System.out.println(r1);
//        int r2= k.findDuplicate(n2);
//        System.out.println(r2);
//        int r3= k.findDuplicate(n3);
//        System.out.println(r3);
    }
}
