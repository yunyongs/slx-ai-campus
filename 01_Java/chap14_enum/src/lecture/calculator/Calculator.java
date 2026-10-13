package lecture.calculator;

//계산 메소드 만들어 놓은 클래스
public class Calculator {
    //생성자가 있어야만 호출, 기본 생성자 생성됨

    private int num;

    //기본 생성자
    public Calculator() {

    }

    // 매개변수를 가진 생성자
    // double로 리턴, double로 매개변수 두 개를 받아서 더해주는 매소드
    public double add (double firstNumber, double secondNumber ) {
        return firstNumber + secondNumber;
    }

    public double divide (double firstNumber, double secondNumber ) {
        //분모가 0일 때 예외 처리필요

        if (secondNumber == 0) {
            throw new CalculatorException(CalculatorErrorCode.DIVIDE_BY_ZERO);
        }
        return firstNumber / secondNumber;

    }

}
