package lecture.section03.references;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class Application0 {
    public static void main(String[] args) {
        /*
        * 매소드 참조
        * - 이미 존재하는 메소드를 람다식에서 호출 할 때 사용하는 축약 문법
        *
        * *//*

        List<String> names = Arrays.asList("홍길동", "유관순", "이순신");

        Consumer<String> consumer = (str) -> System.out.println(str);
        names.forEach(str) -> System.out.println(str);*/
    }
}
