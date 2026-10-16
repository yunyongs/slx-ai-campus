/*
package lecture.section02.executor;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.lang.Thread.sleep;

public class Application throws Exception{

    public static void main(String[] args) {
        //스레드를 자동으로 관리: 스레드 풀 thread pool
        // - 스레드를 미리 정해진 수만큼 만들어 두고 재사용
        // - 작업들을 대기열(큐)에 쌓고, 일을 안 하고 있는 스레드가 하나씩 처리한다.

        ExecutorService executor = Executors.newFixedThreadPool(3);

        System.out.println("=== 일감 5개 제출 (일꾼은 3명) ===");

        for (int i = 1; i <= 5; i++) {

            int taskNo = i;

            executor.submit(() -> {
                System.out.println("[" + Thread.currentThread().getName() + "] " + taskNo + "번 일 처리");
                sleep(500);
            });
        }

    }

}
*/
