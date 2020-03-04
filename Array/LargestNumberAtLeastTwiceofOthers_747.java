package Array;
public class LargestNumberAtLeastTwiceofOthers_747 {
    public int dominantIndex(int[] nums) {
        int max=0;
        int indices=-1;
        for(int k=0;k<nums.length;k++){
            if(nums[k]>max){
                max=nums[k];
                indices=k;
            }
        }
        for(int i=0;i<nums.length;++i){
            if(i!=indices&&2*nums[i]>max){
                indices=-1;
                break;
            }
        }
        return indices;
    }
    public static void main(String[] args) {
        int[] k={1, 2, 3, 4};
        int i = new LargestNumberAtLeastTwiceofOthers_747().dominantIndex(k);
        System.out.println(i);
    }
}
