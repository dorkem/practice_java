package java_practice.marsCalendar;

public class CalController {
    UserInput userInput;
    EarthCalendar earthCalendar;
    MarsInfo marsInfo;
    private long earthDate;

    CalController(UserInput userInput) {
        this.userInput = userInput;
        this.earthCalendar = new EarthCalendar();
        this.marsInfo = new MarsInfo();
    }

    public void calculation(){
        String inputData = userInput.inputEarthDate();
        earthDate = earthDate(inputData);
        if (earthDate != -1) MarsDateCal(earthDate);
    }

    private long earthDate(String string){
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

    private void MarsDateCal(long earthDate){
        // 연도 계산
        int twoYearCycle = MarsInfo.YEAR * 2 + 1;
        long marsYear = (earthDate / twoYearCycle) * 2;
        int remainDate = (int)(earthDate % twoYearCycle);

        boolean isLeapYear = (marsYear % 2 == 0);

        if (remainDate > MarsInfo.YEAR) {
            marsYear += 1;
            remainDate -= MarsInfo.YEAR;
            isLeapYear = false;
        }

        // 월 계산
        int marsMonth = 0;
        int calDate = 0;

        for (int i = 1; i <= MarsInfo.MONTH; i++) {
            int monthLength = 28;

            if (!isLeapYear && (i % 6 == 0)) {
                monthLength = 27;
            }

            if (calDate + monthLength > remainDate) {
                marsMonth = i;
                break;
            }
            calDate += monthLength;
        }
        int marsDay = remainDate - calDate + 1;

        System.out.println(marsYear + " " + marsMonth + " " + marsDay);
    }
}