package Tree;

/**
 * 验证二叉树前序序列化是否合法
 */
public class VerifyPreSerializationofaBTree_331 {
    /**
     * 定义一个槽位概念，当是非叶结点时，消耗一个槽位，生成两个槽位，当是叶子结点时，消耗一个槽位
     * @param preorder
     * @return
     */
    public static boolean isValidSerializationTwo(String preorder) {
        int slot=1;
        int i=0;
        int len=preorder.length();
        char[] str = preorder.toCharArray();

        while (i<len){
            if(slot==0) {
                return false;
            }
            if(str[i]==','){
                i++;
            }else if(str[i]=='#'){
                slot--;
                i++;
            }else{
                //非叶结点
                while (i<len&&str[i]!=','){
                    i++;
                }
                slot++;//slot-1+2
            }
        }
        return slot==0;
    }


    public boolean isValidSerialization(String preorder) {
        //序列合法条件：结点数量关系要满足，其次，遍历过程中，叶子结点不能分布不均
        //记录叶子结点和总结点
        int leafCnt=0,nodeCnt=1;
        for(char c:preorder.toCharArray()){
            //在先序遍历过程中，在最后一个结点前叶子结点最多和非叶子结点相等
            //如果叶子结点超过了非叶子，表明至少有一个叶子结点接上了其他结点
            if(leafCnt>nodeCnt-leafCnt) return false;
            if(c==',') nodeCnt++;
            if(c=='#') leafCnt++;
        }
        return leafCnt==(nodeCnt-leafCnt)+1;
    }

    public static void main(String[] args) {
        boolean validSerializationTwo = isValidSerializationTwo("9,3,4,#,#,1,#,#,2,#,6,#,#");
        System.out.println(validSerializationTwo);
    }
}
