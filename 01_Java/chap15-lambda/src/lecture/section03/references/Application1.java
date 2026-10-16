package lecture.section03.references;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class Application1 {
    public static void main(String[] args) {
        List<String> subjects = new ArrayList<>();
        subjects.add("java");
        subjects.add("mysql");
        subjects.add("jdbc");
        subjects.add("html");
        subjects.add("css");

        // stream
        // 배열이나 컬렉션을 좀 더 잘 다루기 위한 문법
        // Function
        // 리스트의 모든 요소를 대문자로 만드는 코드
        Function<String, String> function = (string) -> string.toUpperCase();


        /*
        * 메소드 참조
        * 객체명::메서드명
        * 클래스::정적메서드
        * */
        subjects.stream()
                .map(String::toUpperCase)
                .forEach(System.out::println);

        // 클래스명::정적메소드명
        subjects.forEach(Example::printName);
    }
}
