package Array;

public class MaxConsecutiveOnes_485 {
    public int findMaxConsecutiveOnes(int[] nums) {
        int max=0;
        int count=0;
        for(int i=0;i<nums.length;i++){
            if(nums[i]!=1){
                max=count>max?count:max;
                count=0;
            }else{
                count++;
            }
        }
        max=count>max?count:max;
        return max;
    }

    public static void main(String[] args) {
        int[] nums={1,1,0,1,1,1};
        int i = new MaxConsecutiveOnes_485().findMaxConsecutiveOnes(nums);
        System.out.println(i);
    }
}
