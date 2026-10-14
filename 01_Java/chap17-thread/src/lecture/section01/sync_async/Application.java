package lecture.section01.sync_async;

public class Application {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("=====동기방식====");
        
        //시작 시간을 측정
        long syncStart = System.currentTimeMillis();
        
        FileDownload.downloadSync("문서.pdf", 2000);
        FileDownload.downloadSync("사진.jpg", 1500);
        FileDownload.downloadSync("영상.mp4", 4000);
        
        long syncEnd = System.currentTimeMillis();
        long syncTotal = syncEnd - syncStart;
        System.out.println("syncTotal = " + syncTotal);

        System.out.println("=====비동기방식====");

        //시작 시간을 측정
        long asyncStart = System.currentTimeMillis();

        Thread t1= FileDownload.downloadAsync("문서.pdf", 2000);
        Thread t2= FileDownload.downloadAsync("사진.jpg", 1500);
        Thread t3= FileDownload.downloadAsync("영상.mp4", 4000);

        //대기
        // join():스레드가 끝날때까지 main을 대기시키는 메소드
        t1.join();
        t2.join();
        t3.join();

        long asyncEnd = System.currentTimeMillis();
        long asyncTotal = asyncEnd - asyncStart;
        System.out.println("AsyncTotal = " + asyncTotal);

        
    }
}
