package Math;

/**
 * 使用rand7()实现rand10
 * 基于以下事实：
 *  已知RandN()可以等概率生成[1，N]范围的随机数，那么(rand_X()-1) * Y + rand_Y()
 *  可以等概率生成[1,X*Y]范围的随机数，即实现了rand_XY()
 */
public class Rand7_470 {
    public int rand10Fast() {
        int num=(rand7()-1)*7+rand7();
        //1-40之间都是等概率的，只要对其取余+1即可生成1-10，而41-49无法等概率生成1-10，
        //但是41-49它们的概率是相等的，可以num-40后再次利用上面公式，num-40后的值为1-9，所以会生成1-63区间内的数，
        //对61-63采取相同处理措施
        if(num<=40) return num%10+1;
        num=(num-40-1)*7+rand7();
        if(num<=60) return num%10+1;
        num=(num-60-1)*7+rand7();
        return num%10+1;
    }
    public int rand10Mid() {
        int num=(rand7()-1)*7+rand7();
        //1-40之间都是等概率的，只要对其取余+1即可生成1-10，而41-49无法等概率生成1-10，因此将其抛弃
        while(num>40){
            num=(rand7()-1)*7+rand7();
        }
        return num%10+1;
    }
    public int rand10Slow() {
        int num=(rand7()-1)*7+rand7();
        while(num>10){
            num=(rand7()-1)*7+rand7();
        }
        return num;
    }
    //仅仅为了不显示错误
    public int rand7(){return -1;}
}
