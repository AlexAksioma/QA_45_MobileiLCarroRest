package helper;

import java.time.Month;
import java.util.Locale;

public class HelperDate {
    public static void main(String[] args) {
        System.out.println(returnNumOfMonth("June"));
        System.out.println(returnNumOfMonth("February"));
        System.out.println(returnNumOfMonth("Mar"));
    }
    private static int returnNumOfMonth(String month){
        Month month1 = Month.valueOf(month.toUpperCase(Locale.ENGLISH));
        return month1.getValue();
    }
}
