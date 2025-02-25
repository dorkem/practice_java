package java_practice.PracticeVFS;

import java.util.Scanner;

public class userInput {
    public long userInit(){
        System.out.print("저장된 파일 시스템이 없습니다.\n파일 시스템의 최대 크기를 입력해 주세요.\nmy-vfs> ");
        Scanner sc = new Scanner(System.in);
        String userSize = sc.nextLine();
        long sizeByte = parseSize(userSize);
        System.out.println(formatSize(sizeByte)+" 파일 시스템의 초기화를 완료했습니다.");
        return sizeByte;
    }

    private static long parseSize(String input) {
        input = input.trim().toUpperCase();

        if (!input.endsWith("M") && !input.endsWith("G")) {
            throw new IllegalArgumentException("지원되지 않는 단위입니다.");
        }

        long size;
        try {
            long value = Long.parseLong(input.substring(0, input.length() - 1));

            if (input.endsWith("M")) {
                size = value * 1024 * 1024;
            }
            else if (input.endsWith("G")) {
                size = value * 1024 * 1024 * 1024;
            } else {
                throw new IllegalArgumentException("지원되지 않는 단위입니다.");
            }


            if (size < 1 * 1024 * 1024 || size > 1024 * 1024 * 1024) {
                throw new IllegalArgumentException("1MB ~ 1GB 사이의 크기를 입력하세요.");
            }

        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자를 올바르게 입력하세요.");
        }

        return size;
    }

    private static String formatSize(long sizeBytes) {
        return (sizeBytes >= 1024L * 1024 * 1024)
            ? (sizeBytes / (1024L * 1024 * 1024)) + " 기가"
            : (sizeBytes / (1024L * 1024)) + " 메가";
    }

}
