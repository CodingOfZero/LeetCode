public class ElementAppearing_1287 {
    public static void main(String[] args){
        int[] arr={2,3,4,5,5,5};
        int m=findSpecialInteger(arr);
        System.out.println(m);
    }
    public static int findSpecialInteger(int[] arr) {
        int goal=arr.length/4+1;
        for(int i=0;i<=arr.length-goal;i++){
            if(arr[i]==arr[i+goal-1])
                return arr[i];
        }
        return -1;
    }
}
