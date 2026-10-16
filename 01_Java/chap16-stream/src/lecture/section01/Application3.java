/*
package lecture.section01;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Application3 {
    public static void main(String[] args) {

        */
/*
        * Stream<T> sorted();
        * Stream<T> sorted(Comparator<? super T> comparator);
        * - sorted() 인자가 없으면 오름차순으로 정렬된다.
        *
        * *//*

        List<Integer> integerList = IntStream.of(5, 2, 4, 6, 16, 4572)
                .boxed()// 기본 타입을 참조타입으로 변경해 주는 것
                .sorted()
                .toList();
                //.collect(Collectors.toList());

        List<Integer> integerList2 = IntStream.of(5, 2, 4, 6, 16, 4572)
                .boxed()// 기본 타입을 참조타입으로 변경해 주는 것
                .sorted((s1, s2) -> s2.compareTo(s1))
                .toList();
        //.collect(Collectors.toList());


        List<String> stringList = Arrays.asList("java", "sql", "mybatis", "jdbc", "spring", "java", "mybatis");
        List<String> strList2 = stringList.stream()
                .sorted((s1, s2) -> s2.compareTo(s1))
                .peek((str ) -> System.out.println("str = " + str))
                .distinct()
                .peek((string) -> System.out.println("string = " + string))
                        .limit(3)
                                .peek()


        System.out.println("integerList = " + integerList2);

    }
}
*/
