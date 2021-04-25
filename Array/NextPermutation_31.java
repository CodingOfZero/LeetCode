package Array;

/**
 * 寻找下一个更大的排序队列
 * 假定nums包含：1,2,3
 * 其中1，2，3排列为
 * 1,2,3
 * 1,3,2
 * 2,1,3
 * 2,3,1
 * 3,1,2
 * 3,2,1
 * 则下一个更大的排列为1,3,2
 *
 * 思路：左边找到一个较小值，右边找到一个较大值，并且左边尽可能靠右，较大值尽可能小，交换它们两个，对后面进行排序
 * 1.从数组后面开始找，找第一对nums[i]<nums[i+1]，此时i即为较小值且靠右
 * 2.从后往前找，找第一个比nums[i]大的数，那么这个数就是较大值，且满足尽可能小的条件，然后交换它们，此时[i+1,n)必然有序且为降序
 * 3.使用双指针对[i+1,n)交换，排序
 * 注：当较小值不存在时，省略步骤2，进行步骤3
 */
public class NextPermutation_31 {
    public void nextPermutation(int[] nums) {
        if(nums.length==1) return;
        int n=nums.length;
        int minIndex=-1;
        //找较小值
        for(int i=n-2;i>=0;i--){
            if(nums[i]<nums[i+1]){
                minIndex=i;
                break;
            }
        }
        //找较大值
        if(minIndex!=-1){
            for(int i=n-1;i>=0;i--){
                if(nums[i]>nums[minIndex]){
                    int temp=nums[minIndex];
                    nums[minIndex]=nums[i];
                    nums[i]=temp;
                    break;
                }
            }
        }
        //双指针交换
        int left=minIndex+1,right=n-1;
        while (left<right){
            int temp=nums[right];
            nums[right]=nums[left];
            nums[left]=temp;
            left++;
            right--;
        }

    }


}
