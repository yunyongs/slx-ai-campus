package lecture.section02.virtual_thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.lang.Thread.sleep;

public class Application {
    public static void main(String[] args) {
        int taskCount = 10_000;


        System.out.println("=== 플랫폼 스레드 풀(200명)로 " + taskCount + "개 처리 ===");

        long start = System.currentTimeMillis();

        try (ExecutorService executor = Executors.newFixedThreadPool(200)) {

            for (int i = 0; i < taskCount; i++) {
                executor.submit(() -> sleep(100)); //작업당 0.1초
            }
        }

        System.out.println("소요 시간: 약 " + (System.currentTimeMillis() - start) + "ms\n");

        /*
        * 플렛폼스레드 : OS 스레드와 1:1로 묶이는 무거운 스레드
        * 가상스레드: 아주 가벼운 스레드 힙영역에 작은 객체형태로 만들어짐.
        * 가상 스레드가 동작하기 위해서는 (소수의)OS 스레드(캐리어스레드) 위에서 동작함
        * sleep(), 네트워크 대기같은 기다리는 작업을 만나면, OS 스레드 위에서 동작하다가 내려옴(unmount)
        * OS스레드가 할 작업이 비어있게 되므로, 기다리는 동안 다른 작업을 할 가상 스레드를 실행시킬 수 있다.
        * */
        System.out.println("=== 가상 스레드로 " + taskCount + "개 처리 ===");

        start = System.currentTimeMillis();

        //가상 스레드풀 생성
        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {

            //작업진행
            for (int i = 0; i < taskCount; i++) {
                executor.submit(() -> sleep(100)); //작업당 0.1초
            }
        }
        System.out.println("소요 시간: 약 " + (System.currentTimeMillis() - start) + "ms\n");
    }
    private static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }


}
