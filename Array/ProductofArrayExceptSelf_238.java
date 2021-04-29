package Array;

/**
 * 除自身以外的数组乘积
 * 思路，使用两个数组分别记录索引i左侧和右侧乘积
 * 不能使用先计算总乘积的方法然后依次除以当前的数，该方法遇到0时失效
 */
public class ProductofArrayExceptSelf_238 {
    public int[] productExceptSelf(int[] nums) {
        int n=nums.length;
        int[] res=new int[n];
        int[] left=new int[n];
        int[] right=new int[n];

        //对于left而言 left[0]=1;  left[i]表示i左侧的所有乘积
        //对于right, right[n-1]=1;
        left[0]=1;
        right[n-1]=1;
        for(int i=1;i<n;i++){
            left[i]=left[i-1]*nums[i-1];
        }
        for(int i=n-2;i>=0;i--){
            right[i]=right[i+1]*nums[i+1];
        }
        for(int i=0;i<n;i++){
            res[i]=left[i]*right[i];
        }
        return res;
    }
}
