package lecture.section01.main_thread;

public class Application {
    public static void main(String[] args) {

        //추상화
        Thread current = Thread.currentThread();
        System.out.println("current.getName() = " + current.getName());

        for (int i=1; i <=5; i++) {
            System.out.println("[" +current.getName() +"]" + i +"번 작업처리");
        }
        
        // 현재 JVM이 사용할 수 있는 CPU 코어 개수
        int cores = Runtime.getRuntime().availableProcessors();
        System.out.println("cores = " + cores);
    }
}
