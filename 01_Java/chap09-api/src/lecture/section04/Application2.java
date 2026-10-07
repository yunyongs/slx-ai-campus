package lecture.section04;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class Application2 {
    public static void main(String[] args) {

//        Date now = new Date();
//        System.out.println("현재시각: " + now);
//        System.out.println("millisecond 현재시각: "+now.getTime());
//
//        Calendar calendar = Calendar.getInstance();
//        int year = calendar.get(Calendar.YEAR);
//        int month = calendar.get(Calendar.MONTH)+1; //월 Calendar의 시작 월은 0월부터 시작한다.
//        int day = calendar.get(Calendar.DAY_OF_MONTH);
//
//        System.out.println("day = " + day);
//        System.out.println("month = " + month);
//        System.out.println("year = " + year);
//
//        // 서식 지정자
//        // %d : 정수출력 (%f: 실수, / %.2f : 소수점 두자리수까지, %c : 문자
//        // %02d : 정수를 2자리수로 출력, 빈자리는 0으로 채움
//        // %n : 줄바꿈
//        System.out.printf("%d-%02d-%02%n", year, month, day);
//
//        LocalDate date = LocalDate.of(2026, 10, 7);
////        LocalDate dateTime = LocalDate
////        ZonedDateTime seoulTime = dateTime.atZone(ZoneId.of(Asia:Seoul));


        LocalDateTime now = LocalDateTime.now();
        System.out.println(now.getYear());
        System.out.println(now.getMonth());
        System.out.println(now.getDayOfMonth());
        System.out.println(now.getDayOfWeek());

        //포멧팅
        // yyyy/MM/dd 2026/10/07
        String today = "2026/10/07";
        DateTimeFormatter input = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate customDate;
        customDate = LocalDate.parse(today, input);
        System.out.println("customDate = " + customDate);
        
        //output
            DateTimeFormatter output = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH:mm");
            LocalDateTime outputExample = LocalDateTime.now();
            System.out.println("outputExample = " + outputExample);
            String formatted = outputExample.format(output);
            System.out.println("formatted = " + formatted);

            /*
            * WrappingClass
            * boxing >>
            * int a -> Integer
            * << unboxing
            * Integer (참조자료형)
            * - 문자열 -> 정수로 변환
            * - 정수 -> 문자열
            * */

            /*
            * 4. 날짜 타입
            * - Date : 옛날 타입
            * - LocalDateTime 
            *
            * */
    }
}
