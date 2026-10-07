package lecture.section02.variable;

public class Application3 {


    public static void main(String[] args) {
        // 변수 명명 규칙
        /*
         * 필수 규칙
         * 1. 같은 범위(scope)에 같은 이름을 중복 선언할 수 없다. // 지역변수, 함수, 클래스
         * 2. 예약어를 사용할 수 없고 숫자로 시작할 수 없다.
         * 3. 영문 대소문자를 구분한다.
         * 4. 문자, 숫자, _, $ 등을 사용할 수 있지만 실무에서는 영문자와 숫자를 주로 사용한다.
         *
         * 권장 규칙
         * 1. 소문자로 시작하는 camelCase를 사용한다.
         * 2. 저장하는 값의 의미가 드러나는 이름을 사용한다.
         * 3. boolean은 is, has, can 등으로 시작하면 의미가 분명하다.
         */

        int age = 0;
//        int age = 0;//같은 범위에 중복 선언 안됨
//        int for; //예약어는 사용할 수 없다.
//        int 1age; //숫자로 시작 X

        // age와 AGE는 다른 변수명이다.
        int AGE = 0; // 상수에서는 대문자로만 사용(암묵적인 규칙)

        // 읽기 좋게 (암묵적 규칙) // 다른 사람도 변수명을 읽고 이해할 수 있게
        int maxAge = 20; //
        int minAge = 1;

//        boolean isTrue = false;
//        boolean isAlive = true;


        //상수 : 변하지 않는 값
        final int MAX_AGE; //final을 붙인 변수는 한번 초기화하면 값을 변경할 수 없음







    }
}
