package Sort;

import java.util.Arrays;

/**
 * 给定一个包含红色、白色和蓝色，一共n 个元素的数组，原地对它们进行排序，
 * 使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
 *
 * 此题中，我们使用整数 0、1 和 2 分别表示红色、白色和蓝色
 *
 */
public class SortColors_75 {
    //时间空间均为O(n)
    public void sortColors(int[] nums) {
        int[] replication=(int[])nums.clone();
        int red=0;
        int white=0;
        int blue=0;
        for(int color:replication){
            switch(color){
                case 0: red++; break;
                case 1: white++; break;
                case 2: blue++; break;
                default:break;
            }
        }
        Arrays.fill(nums,0,red,0);
        Arrays.fill(nums,red,red+white,1);
        Arrays.fill(nums,red+white,nums.length,2);
    }
    //时间为O(n),空间为O(1)
    public void sortColorsTwo(int[] nums) {
        int len=nums.length;
        int p0=0,p2=len-1;
        for(int i=0;i<=p2;i++){
            //交换后有可能是0，2
            while (i<=p2&&nums[i]==2){
                swap(nums,i,p2);
                p2--;
            }
            if(nums[i]==0){
                swap(nums,i,p0);
                p0++;
            }
        }
    }
    private void swap(int[] nums,int m,int n){
        int temp=nums[m];
        nums[m]=nums[n];
        nums[n]=temp;
    }

}
