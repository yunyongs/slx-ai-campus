package lecture.section01.generic;

public class Application {
    public static void main(String[] args) {

        /*
         * 제네릭(generic)
         * - 데이터의 형식에 의존하지 않고 값이 여러 데이터 타입을 가질 수 있는 기술
         * - 데이터의 타입을 일반화 한다.
         * */

        // 제네릭으로 전달할 타입은 참조형이여함.
        GenericTest<Integer> gt1 = new GenericTest<>(10);

        System.out.println("gt1.getValue() = " + gt1.getValue());
        System.out.println("gt1.getValue()의 타입은 = " + (gt1.getValue() instanceof Integer));

        GenericTest<String> gt2 = new GenericTest<>("테스트");
        System.out.println("gt2.getValue() = " + gt2.getValue());
        System.out.println("gt2.getValue()의 타입은 = " + (gt2.getValue() instanceof String));
    }
}