package Greedy;

import java.util.Arrays;

/**
 * 给两个数组，分别代表到达时间和发车时间，所需的最少平台数目
 * 思路：将两个数组排序
 * 创建i=1,j=0;
 * 在i <n和j <n时运行一个循环， 比较到达数组的第ith个元素和离开数组的第j个元素
 * 如果到达时间小于或等于离开，则需要一个平台，因此增加计数，即plat ++并增加i
 * 否则，如果到达时间大于出发时间，则需要少一个平台，因此减少计数，即plat ++并增加j
 *
 * 类似于归并排序
 *
 Time      Event Type     Total Platforms Needed
 at this Time
 9:00       Arrival                  1
 9:10       Departure                0
 9:40       Arrival                  1
 9:50       Arrival                  2
 11:00      Arrival                  3
 11:20      Departure                2
 11:30      Departure                1
 12:00      Departure                0
 15:00      Arrival                  1
 18:00      Arrival                  2
 19:00      Departure                1
 20:00      Departure                0
 */
public class MinimumNumberofPlatformsForBusStation {
    //时间(Ologn)  空间O(1)
    public static int findPlatform(int[] arr,int[] dep){
        int n=arr.length;
        int i=1,j=0,platform=1,result=1;
        Arrays.sort(arr);
        Arrays.sort(dep);

        while (i<n&&j<n){
            //如果到达时间小于离开时间，车站数+1
            if(arr[i]<=dep[j]){
                platform++;
                i++;
            }else{//到达时间大于离开时间，车站数-1
                platform--;
                j++;
            }
            if(platform>result) {
                result =platform;
            }
        }
        return result;
    }
    public static void main(String[] args)
    {
        int[] arr = { 900, 940, 950, 1100, 1500, 1800 };
        int[] dep = { 910, 1200, 1120, 1130, 1900, 2000 };
        int n = arr.length;
        System.out.println("Minimum Number of Platforms Required = "
                + findPlatform(arr, dep));
    }
}
