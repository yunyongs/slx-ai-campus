package exercise;

public class Answer4 {
    public static void main(String[] args) {
        /*
         * 문제 4. 오버플로우와 계산 자료형 확인
         *
         * 1. byte 변수 max에 127을 저장한 뒤 1 증가시켜 출력한다.
         * 2. byte 변수 min에 -128을 저장한 뒤 1 감소시켜 출력한다.
         * 3. int 변수 firstNumber에 1_000_000, secondNumber에 700_000을 저장한다.
         * 4. 두 int를 먼저 곱한 결과를 long에 저장하여 출력한다.
         * 5. firstNumber를 long으로 형변환한 뒤 곱한 결과를 출력한다.
         *
         * 실행 결과
         * byte 최댓값에서 1 증가 : -128
         * byte 최솟값에서 1 감소 : 127
         * int로 계산한 결과 : -79669248
         * long으로 계산한 결과 : 700000000000
         */

        byte max = 127;
        byte min = -128;

        System.out.println("byte 최댓값에서 1 증가 :" + ++max);
        System.out.println("byte 최솟값에서 1 감소 :" + --min);

        int firstNumber = 1000000;
        int secondNumber = 700000;
        long multi = firstNumber * secondNumber;
        System.out.println("int로 계산한 결과 : " + multi);

        long multiLong = (long) firstNumber * secondNumber;
        System.out.println("long으로 계산한 결과 : " + multiLong);

    }
}
