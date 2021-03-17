package Interview;

public class MedianofTwoSortedArrays_4 {
    //时间复杂度为O(logn)
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int len1=nums1.length,len2=nums2.length;
        int total=len1+len2;
        if(total%2==1){
            //奇数
            return getKthElement(nums1,nums2,total/2+1);
        }else{
            return (getKthElement(nums1,nums2,total/2+1)+getKthElement(nums1,nums2,total/2))/2.0;
        }
    }
    //返回两个有序数组第k个元素
    private int getKthElement(int[] nums1, int[] nums2,int k){
        /* 主要思路：要找到第 k (k>1) 小的元素，那么就取 pivot1 = nums1[k/2-1] 和 pivot2 = nums2[k/2-1] 进行比较
         * 这里的 "/" 表示整除
         * nums1 中小于等于 pivot1 的元素有 nums1[0 .. k/2-2] 共计 k/2-1 个
         * nums2 中小于等于 pivot2 的元素有 nums2[0 .. k/2-2] 共计 k/2-1 个
         * 取 pivot = min(pivot1, pivot2)，两个数组中小于等于 pivot 的元素共计不会超过 (k/2-1) + (k/2-1) <= k-2 个
         * 这样 pivot 本身最大也只能是第 k-1 小的元素
         * 如果 pivot = pivot1，那么 nums1[0 .. k/2-1] 都不可能是第 k 小的元素。把这些元素全部 "删除"，剩下的作为新的 nums1 数组
         * 如果 pivot = pivot2，那么 nums2[0 .. k/2-1] 都不可能是第 k 小的元素。把这些元素全部 "删除"，剩下的作为新的 nums2 数组
         * 由于我们 "删除" 了一些元素（这些元素都比第 k 小的元素要小），因此需要修改 k 的值，减去删除的数的个数
         */

        int len1=nums1.length,len2=nums2.length;
        int index1=0,index2=0;
        int kThElement=0;

        while (true){
            //边界情况
            if(index1==len1){
                return nums2[index2+k-1];
            }
            if(index2==len2){
                return nums1[index1+k-1];
            }
            if(k==1){
                return Math.min(nums1[index1],nums2[index2]);
            }
            int half=k/2;
            int newIndex1=Math.min(index1+half,len1)-1;
            int newIndex2=Math.min(index2+half,len2)-1;
            int pivot1=nums1[newIndex1],pivot2=nums2[newIndex2];
            if(pivot1<=pivot2){
                k-=(newIndex1-index1+1);
                index1=newIndex1+1;
            }else{
                k-=(newIndex2-index2+1);
                index2=newIndex2+1;
            }
        }
    }

//    /**
//     * 归并排序，时间复杂度为O(n)
//     * @param nums1
//     * @param nums2
//     * @return
//     */
//    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
//        int[] res=merge(nums1,nums2,nums1.length,nums2.length);
//        int len=res.length;
//        return (len&1)==1?(res[len/2]+res[len/2-1])/2.0:res[len/2];
//    }
//
//    private int[] merge(int[] nums1, int[] nums2, int m, int n) {
//        int i=0,j=0;
//        int[] res=new int[m+n];
//        int k=0;
//        while (i<m&&j<n){
//            if(nums1[i]<nums2[j]){
//                res[k++]=nums1[i++];
//            }else{
//                res[k++]=nums2[j++];
//            }
//        }
//        while (j<n){  res[k++]=nums2[j++];}
//        while (i<m) {
//            res[k++]=nums1[i++];
//        }
//        return res;
//    }

    public static void main(String[] args) {

    }
}
