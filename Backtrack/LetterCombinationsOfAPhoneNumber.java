package Backtrack;

import java.util.*;

public class LetterCombinationsOfAPhoneNumber {
    public List<String> letterCombinations(String digits) {
        List<String> list= new LinkedList<>();
        if(digits==null||digits.length()==0) {return list;}
        Map<Character, Character[]> map = numberMap();
        letterHelper(digits.toCharArray(),list,map,new StringBuilder(),0);
        return list;
    }
    private void letterHelper(char[] digits,List<String> list,Map<Character,Character[]> map,StringBuilder str,int cur){
        if(cur==digits.length){
            list.add(str.toString());
            return;
        }
        char digit = digits[cur];
        //直接使用digit，map不能将其识别为键值
        //原因在于填充map时
        Character[] chars = map.get((char)Character.getNumericValue(digit));
        if(chars==null) {
            letterHelper(digits,list,map,str,cur+1);
            return;
        }
        for (Character aChar : chars) {
            str.append(aChar);
            letterHelper(digits, list, map, str, cur + 1);
            str.deleteCharAt(str.length() - 1);
        }
    }
    private Map<Character,Character[]> numberMap(){
        Map<Character,Character[]> hashmap=new HashMap<>();
        char first='a';
        //将char定义为 char i=2的形式，直接使用map无法取到
        for(char i=2;i<10;i++){
            int len=(i==7||i==9)?4:3;
            Character[] chars = new Character[len];
            int j=0;
            for(;j<len;j++){
                chars[j]=first++;
            }
            hashmap.put(i,chars);
        }
        return hashmap;
    }

    public static void main(String[] args) {
//        Map<Character, Character[]> characterMap = new LetterCombinationsOfAPhoneNumber().numberMap();
//        for(Character c:characterMap.keySet()){
//            Character[] chars = characterMap.get(c);
//
//            System.out.println((int)c+": ");
//            for(int i=0;i<chars.length;i++){
//                System.out.printf("%c  ",chars[i]);
//            }
//            System.out.println();
//        }
        Map<Character, String> map = new HashMap<Character, String>() {{
            put('2', "abc");
            put('3', "def");
            put('4', "ghi");
            put('5', "jkl");
            put('6', "mno");
            put('7', "pqrs");
            put('8', "tuv");
            put('9', "wxyz");
        }};
        char c='2';
        char e=2;
        String s = map.get(e);
        System.out.println(s);
//        List<String> strings = new LetterCombinationsOfAPhoneNumber().letterCombinations("23");
//        strings.forEach(System.out::println);

    }
}
