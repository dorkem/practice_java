package java_practice.marsCalendar;

public class CalController {
    UserInput userInput;
    EarthCalendar earthCalendar;
    private long earthDate;

    CalController(UserInput userInput) {
        this.userInput = userInput;
        this.earthCalendar = new EarthCalendar();
    }

    public void calculation(){
        String inputData = userInput.inputEarthDate();
        earthDate = earthDate(inputData);
        System.out.println(earthDate);
    }

    public long earthDate(String string){
        String[] parts = string.split("\\s*[.\\- ]\\s*");
        if (parts.length != 3){
            System.out.println("날짜 형식이 잘못되었습니다. ex)2023-1-2, 2023 3 4, 2023.3.7");
            return -1;
        }

        int year = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int day = Integer.parseInt(parts[2]);

        long date = earthCalendar.calDate(year, month, day);
        return date;
    }
}