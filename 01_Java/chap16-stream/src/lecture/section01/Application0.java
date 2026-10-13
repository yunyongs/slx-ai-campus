package lecture.section01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class Application0 {
    public static void main(String[] args) {

        /*
        * Stream
        * - 컬렉션에 저장된 요소들을 하나씩 순회하면서 처리할 수 있는 기능
        *
        * */
        List<String> stringList = new ArrayList<>(Arrays.asList("hello", "world", "stream"));
        for (String str: stringList) {
            System.out.println(str);
        }

        //컬렉션 안에 구현되어 있는 forEach()
        stringList.forEach(str-> System.out.println(str));

        stringList.stream().forEach(string -> System.out.println(string));

        IntStream intStream = IntStream.range(5, 10);
        intStream.forEach(i -> System.out.println(i));
    }
}
