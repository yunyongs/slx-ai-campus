package lecture.section01.main_thread;

public class PrintingTask implements Runnable{

    private final String taskName;
    private final int count;

    //객체를 만들 때 필드에 꼭 초기화를 해야함
    //? final 키워드에 의해 메모리에 올라갈 경우 변경할 수 없기 때문에
    public PrintingTask(String taskName, int count) {
        this.taskName = taskName;
        this.count = count;
        System.out.println("taskName + count = " + taskName + count);
    }

    // 스레드가 수행할 일
    @Override
    public void run() {

        for (int i = 1; i <= count; i++) {
            System.out.println(taskName+"을 ["+Thread.currentThread().getName()
            + "]이" + i +"회차 수행함."
            );

        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
