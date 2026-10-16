package lecture.section01;

import java.util.stream.IntStream;

public class Application2 {
    public static void main(String[] args) {

        /*
        * Stream <R> map (Function<? super T, ? extends R> mapper)
        * 스트림에 들어 있는 데이터를 특정 람다식을 통해 데이터를 가공하고
        * 새로운 스트림에 담아줌
        * */
        IntStream intStream = IntStream.range(0, 10);

        // .으로 이어가는 문법을 체이닝 문법이라 한다.
        intStream
                .filter((int i) -> (i%2) == 0)
                .map( i -> i*5)
                .forEach(i -> System.out.println(i));

    }
}
