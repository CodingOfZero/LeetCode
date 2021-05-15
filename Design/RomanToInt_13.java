package Design;

import java.util.HashMap;
import java.util.Map;


public class RomanToInt_13 {
    public int romanToIntFast(String s) {
        Map<Character,Integer> rule=new HashMap<Character, Integer>(){
            {
                put('M',1000);
                put('D',500);
                put('C',100);
                put('L',50);
                put('X',10);
                put('V',5);
                put('I',1);
            }
        };
        /**
         * 通常情况，罗马数字中小的数字位于大的数字右侧，如果出现右侧数字比当前大，需要减去当前罗马字符所代表的数字
         */
        int sum=0;
        for(int i=0;i<s.length();i++){
//            char c=s.charAt(i);
//            char c2=i+1<s.length()?s.charAt(i+1):'0';
//            if('0'!=c2&&rule.get(c)<rule.get(c2)){
//                sum-=rule.get(c);
//            }else{
//                sum+=rule.get(c);
//            }
            int value=rule.get(s.charAt(i));
            if(i<s.length()-1&&value<rule.get(s.charAt(i+1))){
                sum-=value;
            }else{
                sum+=value;
            }
        }
        return sum;
    }
    public int romanToInt(String s) {
        Map<String,Integer> relation=new HashMap<String, Integer>(){
            {
                put("M",1000);
                put("CM",900);
                put("D",500);
                put("CD",400);
                put("C",100);
                put("XC",90);
                put("L",50);
                put("XL",40);
                put("X",10);
                put("IX",9);
                put("V",5);
                put("IV",4);
                put("I",1);
            }
        };

        char[] str=s.toCharArray();
        int sum=0;
        for(int i=0;i<str.length;){

            if(i+1<str.length){
                String s1 = new String(str, i, 2);
                if(relation.containsKey(s1)){
                    sum+=relation.get(s1);
                    i+=2;
                    continue;
                }
            }
            sum+=relation.get(str[i]+"");
            i++;
        }
        return sum;
    }

    public static void main(String[] args) {
        RomanToInt_13 test = new RomanToInt_13();
        int s1 = test.romanToIntFast("LVIII");
        int s2 = test.romanToIntFast("III");
        int s3 = test.romanToIntFast("IX");
        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s3);
    }
}
