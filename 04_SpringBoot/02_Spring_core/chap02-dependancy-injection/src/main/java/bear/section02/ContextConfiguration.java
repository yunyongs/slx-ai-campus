package bear.section02;

import bear.section02.common.EmailNotificationClient;
import bear.section02.common.NotificationClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("bear.section02")
public class ContextConfiguration {

    @Bean
    public NotificationClient notificationClient() {

        return new EmailNotificationClient(
                "apiUrl",
                "api-key",
                3
        );

    }
}
