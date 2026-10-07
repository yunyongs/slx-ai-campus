package lecture.section03.example;

public class Application {

/*
* 주문 처리하는 클래스
* ====
* pay()
* ====
*
*
* 결제수단
* - 계좌이체
* pay()
* - 카드
* pay()
*
* << 인터페이스 형태가 되어야 >>
*
*
*
* */
    public static void main(String[] args) {

        BankTransferPaymentProcess bank = new BankTransferPaymentProcess();
        CreditCardPaymentProcess credit = new CreditCardPaymentProcess();

        OrderService orderService = new OrderService(bank);
        orderService.checkout(50000);

    }


}
