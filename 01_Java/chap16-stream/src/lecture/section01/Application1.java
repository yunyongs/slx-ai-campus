package lecture.section01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.IntStream;

public class Application1 {
    public static void main(String[] args) {

        /*
        * Stream
        * - 컬렉션에 저장된 요소들을 하나씩 순회하면서 처리할 수 있는 기능
        *
        * */
        IntStream intStream = IntStream.range(0, 10);
        //Predicate<Integer> predicate = (integer i) -> (i%2) == 0;

        intStream
                .filter((int i) -> (i%2) == 0)
                .forEach(i -> System.out.println(i));
    }
}
