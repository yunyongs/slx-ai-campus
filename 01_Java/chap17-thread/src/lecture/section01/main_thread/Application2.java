package lecture.section01.main_thread;

public class Application2 {
    public static void main(String[] args) throws InterruptedException {
        PrintingTask myTask = new PrintingTask("빨래", 3);

        //스레드 만들기
        Thread worker = new Thread(myTask, "일꾼1");

        /*
        * worker.start()
        * worker.run()
        * */
        worker.start();

        for (int i=1; i <=5; i++) {
            System.out.println(
                    "설거지를" + "["
                            +Thread.currentThread().getName() +"]"
                            + i +"번 작업처리");
            Thread.sleep(1000);
        }

    }
}
