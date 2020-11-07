package Dynamic;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * 给n个长方体盒子，高度为h,宽度为w,深度为d  求把它们叠起来的最大高度是多少，
 * 即要保证下面盒子的宽度x深度要大于等于上面盒子
 * 同一个盒子，可以旋转，使用不同的面当底面，也可以使用同一种盒子多次，但每次底面要不同（即最多三次）
 * 思路：一个盒子有三种旋转方式，比如1x2x3:  1x2x3  2x1x3 3x1x2
 * 为了便于计算，我们假设宽度小于深度，首先定义一个数组将每个盒子的三种方式都存入进去
 * 然后按底面积从大到小进行排序，此时求最大高度等价于求最长递增子序列问题LIS
 */
public class BoxStacking {
    static class Box implements Comparable<Box>{
        //h高度，w宽度,d为深度，area为底面积
        int h,w,d,area;

        public Box(int h, int w, int d) {
            this.h = h;
            this.w = w;
            this.d = d;
        }

        @Override
        public int compareTo(Box o) {//下面盒子面积要大于上面盒子的面积
            return o.area-this.area;
        }
    }

    public static int maxStackHeight(Box[] arr,int n){
        Box[] rot=new Box[3*n];
        //将每个盒子的三种旋转方式存入到数组
        for(int i=0;i<n;i++){
            Box box = arr[i];
            rot[3*i]=new Box(box.h,Math.min(box.w,box.d),Math.max(box.w,box.d));
            rot[3*i+1]=new Box(box.w,Math.min(box.h,box.d),Math.max(box.h,box.d));
            rot[3*i+2]=new Box(box.d,Math.min(box.w,box.h),Math.max(box.w,box.h));
        }
        //计算每个盒子的底面积
        for(Box box:rot){
            box.area=box.w*box.d;
        }
        //按底面积进行排序
        Arrays.sort(rot);

        int count=3*n;
        //LIS问题
        //msh[] 盒子i在顶部时的最大可能堆叠高度
        int[] msh=new int[count];
        Arrays.fill(msh,0);
//      将高度赋值给msh数组，此时该问题与求递增最长子序列是一样的
//        for(int i=0;i<count;i++){
//            msh[i]=rot[i].h;
//        }

        for(int i=0;i<count;i++){
 //           msh[i]=0;
            Box box=rot[i];
            int val=0;

            for(int j=0;j<i;j++){
                Box preBox=rot[j];
                //下面的盒子比上面面积要大
                if(box.w<preBox.w&&box.d<preBox.d){
                    val=Math.max(val,msh[j]);
                }
            }
            msh[i]=val+box.h;
        }
        int max=-1;
        for(int it:msh){
            if(max<it) {
                max=it;
            }
        }
        return max;
    }
    public static void main(String[] args) {
        Box[] arr = new Box[4];
        arr[0] = new Box(4, 6, 7);
        arr[1] = new Box(1, 2, 3);
        arr[2] = new Box(4, 5, 6);
        arr[3] = new Box(10, 12, 32);

        System.out.println("The maximum possible "+
                "height of stack is " +
                maxStackHeight(arr,4));
    }
}
