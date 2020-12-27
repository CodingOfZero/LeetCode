package Greedy;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/**
 * 给你一个用字符数组tasks 表示的 CPU 需要执行的任务列表。
 * 其中每个字母表示一种不同种类的任务。
 * 任务可以以任意顺序执行，并且每个任务都可以在 1 个单位时间内执行完。
 * 在任何一个单位时间，CPU 可以完成一个任务，或者处于待命状态。
 * 然而，两个 相同种类 的任务之间必须有长度为整数 n 的冷却时间，
 * 因此至少有连续 n 个单位时间内 CPU 在执行不同的任务，或者在待命状态。
 * 你需要计算完成所有任务所需要的 最短时间 。
 * 解决办法
 * 将字符出现频率最高次数记为m,看做m个桶，桶的大小为n+1,将后续字符依次放入桶中，
 * 对于前m-1个桶来说，放入字符不会改变最后需要完成任务的时间，只有当字符放入最后一个桶时，才增加
 * 桶序号
 *     1 2 ...n+1
 *   1 A B
 *   2 A B
 *   3 A B
 *   4 A
 *   m A
 *
 *  （桶的数量-1）*（n+1）+最后一个桶的任务数  当有空闲操作时
 *   任务数                              当没有空闲操作时
 */
public class TaskScheduler_621 {
    public static int leastInterval(char[] tasks, int n) {
        char[] freq=new char[26];
        int task=tasks.length;
        for(int i=0;i<task;i++){
            freq[tasks[i]-'A']++;
        }
        Arrays.sort(freq);
        //最后一个桶任务
        int cnt=0;
        for(int i=freq.length-1;i>0;i--){
            if(freq[i]==freq[freq.length-1]){
                cnt++;
            }
        }
        int num1=(freq[freq.length-1]-1)*(n+1)+cnt;
        int num2=task;
        return Math.max(num1,num2);
    }

    public static void main(String[] args) {
        int i = leastInterval(new char[]{'A', 'A', 'A', 'B', 'B', 'B'}, 2);
        System.out.println(i);
    }
}
