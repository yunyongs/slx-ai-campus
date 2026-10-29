package section01.aop;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan
@EnableAspectJAutoProxy // @Aspect가 붙은 클래스의 AOP 기능을 활성화
public class ContextConfiguration {
}
