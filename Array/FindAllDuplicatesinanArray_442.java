package Array;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * 找到一个重复数字思路：
 *      从头到尾依次扫描这个数组中的每个数字。当扫描到下标为i的数字时，首先比较这个数字（用m表示）是不是与i相等
 *      如果是，则接着扫描下一个数字；如果不是，则拿它和第m个数字进行比较。如果和第m个数字相等，则找到一个重复数
 *      字；如果不等，就把第i个数字和第m个交换，把m放到属于它的位置。接下来重复这个过程。
 * 找到所有重复数字：
 *              对上述思路进行部分修改，首先判断是否为负值，如果是，接着扫描下一个。当找到一个重复数字后，将其
 *              修改为相反数存入数组中。
 * 空间为O(1),时间为O(n)
 */
public class FindAllDuplicatesinanArray_442 {
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> res=new LinkedList<>();
        if(nums==null||nums.length==0) return res;
        for(int i=0;i<nums.length;i++)
            if(nums[i]<1||nums[i]>nums.length)
                return res;
        for(int i=0;i<nums.length;i++){
            int m=nums[i];

            while (m!=(i+1)){
                if(m<0) break;//如果当前为负，跳出循环，继续往后找
                if(m!=nums[m-1]){//如果不等，就交换
                    swap(nums,i,m-1);
                    m=nums[i];
                }else {//找到重复数字，然后将其设置为负数
                    res.add(m);
                    nums[i]=-m;
                    break;
                }
            }
        }
        return res;
    }
    private void swap(int[] nums, int a,int b){
        int c=nums[a];
        nums[a]=nums[b];
        nums[b]=c;
    }

    public static void main(String[] args) {
        int[] nu={4,3,2,7,8,2,3,1};
        FindAllDuplicatesinanArray_442 f=new FindAllDuplicatesinanArray_442();
        List<Integer> li = f.findDuplicates(nu);
        for (Integer i:li)
            System.out.println(i);

    }
}
