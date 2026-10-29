package common;

import org.springframework.stereotype.Component;


/*
* @Component 어노테이션이 붙어 있으면 ComponentScan을 할 때 자동으로
* 스프링컨텍스트에 Bean 등록이 된다.
* */
@Component
public class TestDTO {

    private int no;

}
