package Greedy;

/**
 * 给定一个非负整数数组，你最初位于数组的第一个位置。
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * 判断你是否能够到达最后一个位置。
 *
 * 思路：
 *      1.如果某一个作为起跳点的格子可以跳跃的距离是3，那么它之后的3个格子都可以作为起跳点
 *      2.可以对每个格子起跳一次，把能跳到最远的距离不断更新
 *      3.如果可以跳到最后，可表示成功
 */
public class JumpGame_55 {
    public boolean canJump(int[] nums) {
        int position=0;
        for(int index=0;index<nums.length;index++){
            //最远距离小于当前下标，表明无法跳到该处
            if(index>position){
                return false;
            }
            position=Math.max(position,nums[index]+index);
        }
        return true;
    }
}
