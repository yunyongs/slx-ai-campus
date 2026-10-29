package bear.section02.common;

// 외부 라이브러리가 제공하는 클래스라고 가정
// 이 코드를 수정할 수 없음
public class EmailNotificationClient implements NotificationClient {

    private final String apiUrl;
    private final String apiKey;
    private final int timeoutSeconds;

    public EmailNotificationClient(String apiUrl, String apiKey, int timeoutSeconds) {
        this.apiUrl = apiUrl;
        this.apiKey = apiKey;
        this.timeoutSeconds = timeoutSeconds;
    }

    @Override
    public String send(String receiver, String message) {

        return "메일 전송 요청: receiver=" + receiver
                + ", message=" + message
                + ", apiUrl=" + apiUrl
                + ", timeout=" + timeoutSeconds + "초"
                + ", 인증키 설정=" + !apiKey.isBlank();
    }
}
