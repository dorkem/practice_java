package java_practice.marsCalendar;

import java.util.Scanner;

public class UserInput {
    public String inputEarthDate(){
        System.out.println("> 지구 날짜는? ");
        Scanner sc = new Scanner(System.in);
//        String strDate = sc.nextLine();
        String strDate = "2024-01-01";
        return strDate;
    }
}
