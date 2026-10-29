package section04;

import common.MemberDTO;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import section02.javaconfig.ContextConfiguration;

public class Application {

    public static void main(String[] args) {
        MemberDTO javaMember1 = new MemberDTO(1, "user1", "pass01", "홍길동");
        MemberDTO javaMember2 = new MemberDTO(1, "user1", "pass01", "홍길동");

        System.out.println(javaMember1 == javaMember2);

        ApplicationContext context =
                new AnnotationConfigApplicationContext(ContextConfiguration.class);

        // 객체를 직접 만들지 않음에도 ApplicationContext가 객체를 만들어서 가져와줌.
        MemberDTO member1 = (MemberDTO) context.getBean("member", MemberDTO.class);
        MemberDTO member2 = (MemberDTO) context.getBean("member", MemberDTO.class);

        System.out.println(member1 == member2); //스프링 컨텍스트에 빈으로 등록되는 객체는 기본적으로 싱글톤이다. 그래서 같은 객체를 가져와 쓴다
    }
}
