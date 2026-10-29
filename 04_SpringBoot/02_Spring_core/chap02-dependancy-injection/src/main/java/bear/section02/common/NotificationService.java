package bear.section02.common;

import org.springframework.stereotype.Service;

//  Notification Client를 생성자 주입 하세요
@Service
public class NotificationService {

    private final NotificationClient notificationClient;

    public NotificationService(NotificationClient notificationClient) {
        this.notificationClient = notificationClient;
    }
}
