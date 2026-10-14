package lecture.section01.synchronization;

public class Application {
    public static void main(String[] args) {
        //임계 영역

        /*Account account = new Account();*/
        SafeAccount account = new SafeAccount(); // synchronized 적용
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                account.withdraw(300);
            }
        }, "worker1");
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                account.withdraw(300);
            }
        }, "worker2");
        t1.start();
        t2.start();
    }
}
