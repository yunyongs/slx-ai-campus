package lecture.section01.sync_async;

public class FileDownload {

    //동기방식: 다운로드가 끝날때까지 멈춰서 기다림(blocking)
    public static void downloadSync(String fileName, long downloadTimeMs) {

        System.out.println("  [" + Thread.currentThread().getName() + "] " + fileName + " 다운로드 시작");
        sleep(downloadTimeMs);
        System.out.println("  [" + Thread.currentThread().getName() + "] " + fileName + " 다운로드 완료");
    }

    // 비동기방식: 다운로드라는 작업을 새 스레드에게 맡기고, 스레드를 돌려줌 (non-blocking)
    // 호출한 쪽에서는 기다리지 않고 작업가능
    public static Thread downloadAsync(String fileName, long downloadTimeMs) {

        //스레드 생성해서 작업 진행
        Thread downloadThread = new Thread(() -> {
            System.out.println("  [" + Thread.currentThread().getName() + "] " + fileName + " 다운로드 시작");
            sleep(downloadTimeMs);
            System.out.println("  [" + Thread.currentThread().getName() + "] " + fileName + " 다운로드 완료");
        }, "다운로드-" + fileName);

        downloadThread.start();

        return downloadThread;
    }

    //다운로드 받는데 걸리는 시간을 표현한 것
    private static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
