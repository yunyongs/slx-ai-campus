package lecture.calculator;

import java.util.Scanner;


//opt + cmd + t = 구문 감싸기
public class Application {
    // while - 사용자 입력, 연산을 수행하기 위한 입력
    // 입력이 잘못되었을 때 예외 처리
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        Scanner sc = null;

        try {
            sc = new Scanner(System.in);

            while (true) {
                System.out.println("===== 계산기 프로그램=====");

                System.out.println("연산자를 입력하세요 (+, /, 종료: exit)");

                //사용자의 입력을 받고 앞뒤 공백 제거 후 문자열 반환
                String operator = sc.nextLine().trim();

                if ("exit".equals(operator)) {
                    System.out.println("계산기를 종료합니다.");
                    break; //반복문 종료
                }

                double firstNumber = inputNumber(sc, "첫번째 숫자를 입력하세요: ");
                double secondNumber = inputNumber(sc, "두번째 숫자를 입력하세요: ");
                double result = 0;

                switch (operator) {
                    case "+":
                        result = calculator.add(firstNumber, secondNumber);
                        break;
                    case "/":
                        result = calculator.divide(firstNumber, secondNumber);
                        break;
                    default:
                        //  그 외 다른 문자열이면 예외를 던지기!
                        throw new CalculatorException(CalculatorErrorCode.INVALID_OPERATOR);

                }
                System.out.println("계산결과: " + firstNumber + " " + operator + " "+ secondNumber + " = "+ result);
            }
        } catch (CalculatorException e) {
            GlobalExceptionHandler.handler(e);
            //throw new RuntimeException(e);
        }
        sc.close();
    }

    //실수를 입력받는 메소드
    private static double inputNumber(Scanner scanner, String message) {
        System.out.print(message);
        String input = scanner.nextLine().trim();

        try {
            return Double.parseDouble(input); //사용자의 입력이 실수로 변환이 안 될 경우
        } catch (NumberFormatException e) {
            throw new CalculatorException(CalculatorErrorCode.INVALID_NUMBER);
        }
    }
}
