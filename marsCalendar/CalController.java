package java_practice.marsCalendar;

public class CalController {
    UserInput userInput;
    EarthCalendar earthCalendar;
    MarsInfo marsInfo;
    ProcessBar processBar;
    private long earthDate;

    CalController(UserInput userInput) {
        this.userInput = userInput;
        this.earthCalendar = new EarthCalendar();
        this.marsInfo = new MarsInfo();
    }

    public void calculation() throws InterruptedException {
        String inputData = userInput.inputEarthDate();
        earthDate = earthDate(inputData);
        printResult();
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
        earthDate = 738858;
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

            // **비윤년일 때 6개월마다 하루씩 빼지만, 윤년이면 전부 28일 유지**
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

        System.out.println(marsYear + " 화성년 " + marsMonth + "월 " + marsDay + "일");

        printCalendar((int)marsYear, marsMonth, marsDay);
    }

    private void printResult() throws InterruptedException {
        this.processBar = new ProcessBar(1000, 20);
        processBar.loding();
        System.out.println();
        System.out.print("지구 날은 "+ earthDate + " => ");
        if (earthDate != -1) MarsDateCal(earthDate);
    }

    private void printCalendar(int marsYear, int marsMonth, int marsDay) {
        System.out.println();
        System.out.println("     "+ marsYear + "년 " + marsMonth + "월");
        String arr[][] = new String[5][7];
        arr[0] = "Su Lu Ma Me Jo Ve Sa".split(" ");

        int date = 1;
        for (int i = 1; i < 5; i++) {
            for (int j = 0; j < 7; j++) {
                if (date > marsDay) {
                    arr[i][j] = "  ";
                } else {
                    arr[i][j] = String.format("%2d", date++);
                }
            }
        }

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println(); // 줄바꿈
        }

    }
}