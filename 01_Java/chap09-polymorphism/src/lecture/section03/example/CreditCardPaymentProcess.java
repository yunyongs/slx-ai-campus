package lecture.section03.example;

public class CreditCardPaymentProcess implements PaymentProcessor{
    @Override
    public boolean pay(int amount) {
        System.out.println("카드결제로 " + amount + "원을 결제합니다.");
        return true;
    }
    //결제 수단을 제공하는 기능
    /*
    * pay() : boolean 응답 (결제가 되었으면 true, 아니면 false, 금액을 매개변수로 받음
    * */
    //추상메서드



}
