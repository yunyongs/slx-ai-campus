package bear.section02;

import bear.section02.common.NotificationClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {

    public static void main(String[] args) {

        ApplicationContext context =
                new AnnotationConfigApplicationContext(ContextConfiguration.class);


        // 등록된 bean 이름 조회
        String[] beans = context.getBeanDefinitionNames();

        for(String bean : beans) {
            System.out.println(bean);
        }

        NotificationClient notificationClient = context.getBean(NotificationClient.class);

        notificationClient.send("hello@gmail.com", "가입 환영!");
    }
}
