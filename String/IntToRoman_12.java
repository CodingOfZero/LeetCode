package String;

/**
 * 整数转罗马数字
 * 找不超过num最大value
 */
public class IntToRoman_12 {
    public String intToRoman(int num) {
        int[] values={1000,900,500,400,100,90,50,40,10,9,5,4,1};
        String[] symbols={"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};
        StringBuilder roman=new StringBuilder();
        for(int i=0;i<values.length;i++){
            int value=values[i];
            while (num>=value){
                roman.append(symbols[i]);
                num-=value;
            }
            if(num==0){
                break;
            }
        }
        return roman.toString();
    }
}
