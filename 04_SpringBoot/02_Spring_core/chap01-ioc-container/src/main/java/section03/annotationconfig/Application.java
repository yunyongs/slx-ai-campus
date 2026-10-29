package section03.annotationconfig;

import common.TestDTO;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import section02.javaconfig.ContextConfiguration;

public class Application {
    public static void main(String[] args) {

        ApplicationContext context =
                new AnnotationConfigApplicationContext(ContextConfiguration.class);

        // 스프링컨테이너에 생성된 bean들의 이름을 배열로 반환
        String[] beanNames = context.getBeanDefinitionNames();

        for (String beanName: beanNames){
            System.out.println("beanName = " + beanName);
        }


    }
}
