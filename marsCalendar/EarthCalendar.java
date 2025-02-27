package java_practice.marsCalendar;
import java.time.LocalDate;

public class EarthCalendar {
        public long calDate(int year, int month, int day){
        LocalDate baseDate = LocalDate.of(1, 1, 1);
        LocalDate inputDate = LocalDate.of(year, month, day);
        long date = inputDate.toEpochDay() - baseDate.toEpochDay()+1;
        return date;
    }
}