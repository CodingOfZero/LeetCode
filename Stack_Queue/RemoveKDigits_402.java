package Stack_Queue;

/**
 * 给定一个以字符串表示的非负整数num，移除这个数中的 k 位数字，使得剩下的数字最小。
 *
 * 注意:
 *
 * num 的长度小于 10002 且≥ k。
 * num 不会包含任何前导零
 *
 * 1.两个相同位数的数字，最左边的数字决定了两个数字的大小
 * 2.要使得到的数字最小，需要保证靠前的数字尽可能小。
 * 3.当已删除的位数m小于k时，还需要删除单调栈的后k-m位
 * 4.要处理前导零情况
 * 思路：贪心+单调递增栈
 */
public class RemoveKDigits_402 {
    public String removeKdigits(String num, int k) {
        char[] stack=new char[num.length()];
        int sp=-1;
        char[] chars = num.toCharArray();
        for(int i=0;i<num.length();i++){
            while (sp!=-1&&k!=0&&chars[i]<stack[sp]){
                sp--;
                k--;
            }
            stack[++sp]=chars[i];
        }
        //当删除位数小于k时
        while (k>0){
            sp--;
            k--;
        }
        //处理前导零
        int head=0;
        //栈的长度
        int len=sp+1;
        //当栈非空时处理前导零
        while (len>0&&stack[head]=='0'){
            head++;
            len--;
        }
        //栈为空返回字符串0，否则返回栈内字符组成的字符串
        return len==0?"0":new String(stack,head,len);
    }
}
