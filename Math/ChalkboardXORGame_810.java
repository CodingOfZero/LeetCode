package Math;

/**
 * 黑板上的异或游戏
 * 当且仅当以下条件时，Alice获胜
 *      1.nums的长度为偶数
 *      2.nums所有值异或结果为0
 *
 *
 * 博弈论
 */
public class ChalkboardXORGame_810 {
    /**
     * 规则：轮到某个玩家时，如果当前黑板上所有数字异或结果等于 0，则当前玩家获胜，由于Alice 是先手，
     * 因此如果初始时黑板上所有数字异或结果等于 0，则 Alice 获胜。
     *
     * 分析初始时黑板上所有数字结果不为0的情况
     *
     * 由于两人交替擦除数字，且每次都恰好擦掉一个数字，因此对于这两人中的任意一人，其每次在擦除数字前，
     * 黑板上剩余数字的个数的奇偶性一定都是相同的。
     *
     * 证明为偶数时，一定不会输，记S=nums[0]^nums[1]^...nums[n-1]!=0  Si为擦掉nums[i]的结果
     *          Si^nums[i]=S  --->  Si=S^nums[i]
     *          如果无论擦掉哪个数字，剩余的所有数字异或结果都等于0，则Si=0,因此所有Si异或结果也等于0
     *          S0^S1^S2^...S(n-1)=0  将Si=S^nums[i] 带入得
     *
     *          0=S0^S1^S2^...S(n-1)
     *           =(S^nums[0])^(S^nums[1])...(S^nums[n-1])
     *           =(S^S...^S)^(nums[0]^nums[1]...^nums[n-1])
     *           =0^S
     *           =S
     *  与前提矛盾，故当为偶数时先手Alice一定赢
     *
     * 当数组的长度是偶数时，先手 Alice 总能找到一个数字，在擦掉这个数字之后剩余的所有数字异或结果不等于 0。
     * 当数组的长度是奇数时，Alice 在擦掉一个数字之后，留给 Bob 的一定是黑板上剩下偶数个数字，因此 Bob 必胜。
     *
     */
    public boolean xorGame(int[] nums) {
        if(nums.length%2==0){
            return true;
        }
        int res=0;
        for(int num:nums){
            res^=num;
        }
        return res==0;
    }
}
