package lecture.section02.string;

import java.util.Locale;

public class Application1 {
    public static void main(String[] args) {

        //String 도 참조형 변수임
        String text = "  Java Programming  ";

        //조회
        System.out.println("길이: " + text.length());
        System.out.println("첫 글자: "+text.charAt(2));

        System.out.println("Java 포함: " + text.contains("Java"));
        System.out.println("Java 시작위치: " + text.indexOf("Java")); //어떤 인덱스에서 시작하는지 반환

        // 변환
        String trimmed = text.strip();
        System.out.println("공백 제거 : #" + trimmed + "#");
        System.out.println("부분 문자열: " + trimmed.substring(0,5));
        System.out.println("부분 문자열: " + trimmed.replace("Java","Kotlin"));
        System.out.println("대/소문자 변환: " + trimmed.toLowerCase());
        System.out.println("대/소문자 변환: " + trimmed.toUpperCase());

    }
}
