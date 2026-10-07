package lecture.section04;

import java.util.Calendar;
import java.util.Date;

public class Application1 {
    public static void main(String[] args) {

        Date now = new Date();
        System.out.println("현재시각: " + now);
        System.out.println("millisecond 현재시각: "+now.getTime());

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH)+1; //월 Calendar의 시작 월은 0월부터 시작한다.
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        System.out.println("day = " + day);
        System.out.println("month = " + month);
        System.out.println("year = " + year);

        // 서식 지정자
        // %d : 정수출력 (%f: 실수, / %.2f : 소수점 두자리수까지, %c : 문자
        // %02d : 정수를 2자리수로 출력, 빈자리는 0으로 채움
        // %n : 줄바꿈
        System.out.printf("%d-%02d-%02%n", year, month, day);




    }
}
