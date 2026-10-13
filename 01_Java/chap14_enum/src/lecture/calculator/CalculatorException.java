package lecture.calculator;

//사용자 정의 예외
public class CalculatorException extends RuntimeException {

    //enum 타입으로 상수 넣기
    private final CalculatorErrorCode errorCode;

    public CalculatorException(CalculatorErrorCode errorCode) {
      super(errorCode.getMessage());
      this.errorCode = errorCode;
    }

    public CalculatorErrorCode getErrorCode() {
      return errorCode;
    }
}
