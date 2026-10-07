package exercise;

import java.util.Scanner;

public class Answer3 {

    /* Q1. Scanner 를 이용한 대화형 사칙연산 계산기를 만드세요.
     * 두 정수와 연산 기호(+, -, *, /)를 입력 받아 결과를 출력합니다.
     * 단,
     * - 0 으로 나누는 경우 "0 으로 나눌 수 없습니다." 를 출력 후 종료
     * - 위 4가지 외 연산 기호가 입력되면 "지원하지 않는 연산입니다." 를 출력 후 종료
     *
     * -- 입력 예시 --
     * 첫 번째 정수 : 12
     * 두 번째 정수 : 4
     * 연산 기호(+, -, *, /) : /
     *
     * -- 출력 예시 --
     * 12 / 4 = 3
     * */

    public void Calculator () {
        Scanner sc = new Scanner(System.in);

        System.out.print("첫 번째 정수: ");
        int firstInt = sc.nextInt();

        System.out.print("두 번째 정수: ");
        int secondInt = sc.nextInt();

        System.out.print("연산기호: ");
        String op = sc.next();

        int result=0;
        boolean valid = true;

        switch (op) {
            case "+" -> result = add(firstInt, secondInt);
            case "-" -> result = subtract(firstInt, secondInt);
            case "*" -> result = multiply(firstInt, secondInt);
            case "/" -> {
                if (secondInt == 0) {
                    System.out.println("0으로 나눌 수 없습니다.");
                    return;
                }
                result = divide(firstInt, secondInt);
            }
            default -> {
                System.out.println("지원하지 않는 연산입니다.");
                 valid = false;
                return;
            }
        }

        if (valid) {
            System.out.println(firstInt + op + secondInt + "=" + result);
        }

    }
    public int add(int x, int y) {
        return x+y;
    }
    public int subtract(int x, int y) {
        return x-y;
    }
    public int multiply(int x, int y) {
        return x*y;
    }
    public int divide(int x, int y) {
        return x/y;
    }

    /* Q2.
     * 사용자에게 다음 정보를 차례로 입력 받아 그대로 출력하세요.
     *
     * ① 정수 한 개 (nextInt 사용)
     * ② 이름 한 줄 (nextLine 사용. 공백을 포함할 수 있음)
     * ③ 한 단어로 된 좋아하는 색 (next 사용)
     *
     * 참고, nextInt() 다음에 nextLine() 을 호출하면
     * 엔터(개행 문자)가 그대로 남아있어 의도치 않게 빈 문자열이 입력되는 문제가 있습니다.
     * 이 문제를 해결하려면 nextInt() 호출 직후에 sc.nextLine() 을 한 번 더 호출해
     * 남아있는 개행을 비워줘야 합니다.
     *
     * -- 입력 예시 --
     * 나이를 입력하세요 : 25
     * 이름을 입력하세요 : 홍 길동
     * 좋아하는 색을 한 단어로 입력하세요 : 파랑
     *
     * -- 출력 예시 --
     * 나이 : 25
     * 이름 : 홍 길동
     * 좋아하는 색 : 파랑
     * */

    public void personalInfo () {
        Scanner sc = new Scanner(System.in);

        System.out.print("나이를 입력하세요: ");
        int age = sc.nextInt();
        sc.nextLine();
        System.out.println("이름을 입력하세요: ");
        String name = sc.nextLine();
        System.out.print("좋아하는 색을 한 단어로 입력하세요: ");
        String fColor = sc.next();

        System.out.println("나이: " + age);
        System.out.println("이름: " + name);
        System.out.println("좋아하는 색: " + fColor);

    }

}
