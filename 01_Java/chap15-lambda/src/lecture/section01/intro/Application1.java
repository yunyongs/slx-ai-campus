package lecture.section01.intro;

public class Application1 {
    public static void main(String[] args) {
        /*
        * lambda expression
        * (매개변수, ...) -> { ... }
        * -> 매개변수의 타입은 추론이 가능해서 명시적으로 작성하지 않아도 됨!
        * -> 매개변수가 한개만 있을 때는 ()를 생략가능 ex) str -> {}
        * -> 실행문이 한줄일 경우 {}도 생략가능 ex) str -> 실행문
        * */

        // 1. 인터페이스를 구현한 구현체(클래스)를 이용한 방식
        CalculatorImpl calculator = new CalculatorImpl();
        System.out.println(calculator.sumTwoNumber(5, 4));

        // 2. 익명클래스: 클래스의 선언부가 없는 익명클래스 // 1회성 사용
        Calculator calculator2 = new Calculator() {
            @Override
            public int sumTwoNumber(int a, int b) {
                return a+b;
            }

        };

        // 3. lambda exp
        Calculator calculator3 = (int x, int y) -> x + y;
        System.out.println(calculator3.sumTwoNumber(5, 4));


    }
}
