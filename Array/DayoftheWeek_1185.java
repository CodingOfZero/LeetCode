package Array;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Scanner;
/**
 * Given a date, return the corresponding day of the week for that date.
 * The input is given as three integers representing the day, month and year respectively.
 * Return the answer as one of the following values
 * {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"}.
 * */
public class DayoftheWeek_1185 {
    public static void main(String[] args){
        String[] week={"Sunday","Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        Scanner in=new Scanner(System.in);
        int year=in.nextInt();
        int month=in.nextInt();
        int day=in.nextInt();
//        LocalDate date=LocalDate.of(year,month,day);
//        DayOfWeek weekday=date.getDayOfWeek();
//        int value=weekday.getValue();
//        System.out.println(week[value-1]);
        //基姆拉尔森计算公式： W= (d+2*m+3*(m+1)/5+y+y/4-y/100+y/400) mod 7
        //在公式中d表示日期中的日数，m表示月份数，y表示年数,注意：把一月和二月看成是上一年的十三月和十四月

        int week_value=0;
//        switch (month){
//            case 1:
//                year-=1;
//                month=13;
//                week_value=(day+2*month+3*(month+1)/5+year+year/4-year/100+year/400)%7;
//                break;
//            case 2:
//                year-=1;
//                month=14;
//                week_value=(day+2*month+3*(month+1)/5+year+year/4-year/100+year/400)%7;
//                break;
//            default:
//                week_value=(day+2*month+3*(month+1)/5+year+year/4-year/100+year/400)%7;
//                break;
//        }
//        System.out.println(week[week_value]);
        //蔡勒公式:W=Y+[Y/4]+[C/4]-2C+[26(M+1)/10]+d-1
        // W为星期数；C为世纪；Y为年（两位数）； M为月数（M=m（当m>2）；M=m+12（m<3））；d为日
//        if(month==1){
//            month=13;
//            year-=1;
//        }else if(month==2){
//            month=14;
//            year-=1;
//        }
//        int c=year/100-1;
//        int y=year%100;
//        int m=month;
//        int d=day;
//        int w=y+y/4+c/4-2*c+26*(m+1)/10+d-1;
//        int k=w%7;
////        k=k>=0?k:k+7;
//        System.out.println(week[k]);

        String[] week_1={"Friday","Saturday","Sunday", "Monday", "Tuesday", "Wednesday", "Thursday" };
        int [] year_1={31,28,31,30,31,30,31,31,30,31,30,31};
        int [] year_2={31,29,31,30,31,30,31,31,30,31,30,31};
        int count=0;

        for(int i=1971;i<year;i++){
            if(i%4!=0||i%100==0&&i%400!=0)
                count+=365;
            else
                count+=366;
        }
        if(year%4!=0||year%100==0&&year%400!=0){
            for(int i=0;i<month-1;i++)
                count+=year_1[i];

        } else{
            for(int i=0;i<month-1;i++)
                count+=year_2[i];

        }
        int k=(count+day-1)%7;
        System.out.println(week_1[k]);
    }


}
