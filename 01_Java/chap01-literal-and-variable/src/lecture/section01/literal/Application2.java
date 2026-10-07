package lecture.section01.literal;

public class Application2 {

    public static void main(String[] args) {

        // 정수끼리의 연산
        // 전수끼리 연산하면 정수의 결과가 나온다!
        System.out.println(12 + 34);
        System.out.println(12 - 34);
        System.out.println(12 * 34);
        System.out.println(12 / 34); //나머지
        System.out.println(12 % 34); // 나머지

        // 실수가 포함된 연산
        System.out.println("===== 실수가 포함된 연산 ====");
        System.out.println(10 / 4.0);
        System.out.println(0.1 + 0.2);

        // 부동 소수점
        // 소수를 가장 가까운 근사값으로 저장해서 계산하게 됨, 오차가 생길 수 있음
        // 예로 위 0.1+0.2의 값이 0.300000000004로 나옴.

        // 문자 연산
        System.out.println("===== 문자 연산 ====");
        System.out.println('a' + 'b');
        System.out.println('a' + 1);

        // 문자열 연산
        System.out.println("====== 문자열 연산====");
        System.out.println("hello" + "world");
        System.out.println("hello" + 100);
        System.out.println("123"+ "100");
        System.out.println("123"+ true);
//        System.out.println(false+ true);



    }
}
