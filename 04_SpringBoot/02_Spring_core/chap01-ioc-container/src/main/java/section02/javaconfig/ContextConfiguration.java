package section02.javaconfig;

import common.MemberDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// @Configuration이 정의된 클래스가 설정파일임을 의미(bean)
@Configuration("configurationSection02")
public class ContextConfiguration {


    /*
    * 외부 라이브러리를 사용할 때: 외부라이브러리에 어노테이션을 붙일 수 없어서 configuration을 만들고
    * 직접 빈을 등록해야 함 (이때 JAVA Config방식을 사용함)
    * - 또는 객체를 꼭 초기화해야 할 때: 설정값을 넣어서 Bean으로 등록함
    * 기본적으로는 componentScan으로 사용
    * */

    @Bean(name="member")
    public MemberDTO getMember() {

        return new MemberDTO(1, "user01", "pass01", "bear");
    }
}
