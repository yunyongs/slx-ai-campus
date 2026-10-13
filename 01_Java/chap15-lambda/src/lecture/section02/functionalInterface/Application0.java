package lecture.section02.functionalInterface;

import jdk.swing.interop.SwingInterOpUtils;

import java.time.LocalDateTime;
import java.util.function.*;

public class Application0 {

    /*
    * 표준 함수적 인터페이스
    * Runnable : 매개변수 X, 리턴값 X : run()
    * Consumer : 매개변수 O, 리턴값 X : accept()
    * Supplier : 매개변수 X, 리턴값 O : get()
    * Function : 매개변수 O, 리턴값 O : apply()
    * Operator : 매개변수 O, 리턴값 O : apply() * 매개변수와 Return 타입이 동일
    * Predicate: 매개변수 O, 리턴값 O : test() * Boolean값을 리턴
    * */
    public static void main(String[] args) {

        BinaryOperator<Integer> binaryOperator = (a, b) -> a+b;
        System.out.println(binaryOperator.apply(5, 4));

        //Runnable: 매개변수 X, 리턴값 X
        Runnable runnable =  () -> System.out.println("runnable 실행");
        runnable.run();

        //Consumer : 매개변수 o, 리턴값 X : accept()
        Consumer<String> consumer = (str) -> System.out.println(str);
        consumer.accept("Consumer 실행");

        Supplier<LocalDateTime> supplier = () -> {return LocalDateTime.now();};
        Supplier<LocalDateTime> supplier2 = () -> LocalDateTime.now();
        System.out.println(supplier.get());


        //Function: 매개변수 o, 리턴값 o, apply()
        Function<String , Integer> funtion = (str) -> Integer.parseInt(str);
        //System.out.println(funtion.apply(50)+5);

        // Preducate: 매개변수 0, 리턴값 o, test() *Boolean값을 리턴
        Predicate<Integer> isEven = n -> n%2 == 0;
    }
}
