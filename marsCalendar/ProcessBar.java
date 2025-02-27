package java_practice.marsCalendar;

import java.util.Calendar;

public class ProcessBar {
    private static final String ESC = "\u001B[";
    private static final String RESET = ESC + "0m";     // 초기화
    private static final String BLACK = ESC + "30m";    // 검정
    private static final String RED = ESC + "31m";      // 빨강
    private static final String ORANGE = ESC + "38;5;208m"; // 주황 (ANSI 기본 색상엔 없음, 256컬러 사용)
    private static final String YELLOW = ESC + "33m";   // 노랑
    private static final String GREEN = ESC + "32m";    // 초록
    private static final String BLUE = ESC + "34m";     // 파랑
    private static final String NAVY = ESC + "38;5;19m"; // 남색 (256컬러 사용)
    private static final String PURPLE = ESC + "35m";   // 보라
    private static final String WHITE = ESC + "37m";    // 흰색
    private static final String PINK = ESC + "38;5;206m";
    private static final String WHITE_TEXT = ESC + "37m"; // 흰색 글자
    private static final String GRAY_TEXT = ESC + "90m";  // 회색 글자

    private int totalTime;
    private int barLength;

    public ProcessBar(int totalTime, int barLength) {
        this.totalTime = totalTime;
        this.barLength = barLength;
    }

    public void loding() throws InterruptedException {
        int updateTime = totalTime / 10;

        for (int i = 0; i<=10; i++){
            int percentage = i * 10;
            int progress = (int) ((i / 10.0) * barLength);

            String color;
            if (progress < 4) color = RED;
            else if (progress < 6) color = ORANGE;
            else if (progress < 8) color = YELLOW;
            else if (progress < 10) color = GREEN;
            else if (progress < 12) color = BLUE;
            else if (progress < 14) color = NAVY;
            else if (progress < 16) color = PURPLE;
            else if (progress < 18) color = WHITE;
            else if (progress < 20) color = BLACK;
            else color = PINK;

            String bar = color + "▓".repeat(progress) + RESET;
            String emptyBar = GRAY_TEXT + "░".repeat(barLength - progress) + RESET;

            System.out.printf("\r%s%s 화성까지의 여행 %3d%%", bar, emptyBar, percentage);
            System.out.flush();
            Thread.sleep(updateTime);
        }
    }
}
