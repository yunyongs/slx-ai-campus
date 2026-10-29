package section01.xmlconfig;

import common.MemberDTO;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Application {

    // Application Context를 만들 것!
    // XML 파일로 설정정보를 담아서 만들어 보기
    public static void main(String[] args) {

        // ApplicationContext란 사실 Bean을 관리해 주는 것
        ApplicationContext context =
                new GenericXmlApplicationContext("xmlconfig/spring-context.xml");

        // 객체를 직접 만들지 않음에도 ApplicationContext가 객체를 만들어서 가져와줌. (new로 객체생성하지 않음)
        MemberDTO member1 = (MemberDTO) context.getBean("member");
        System.out.println("member1 = " + member1);

        // 클래스의 메타정보를 이용해 가져오는 방법
        MemberDTO member2 = context.getBean(MemberDTO.class);
        System.out.println("member2 = " + member2);


        MemberDTO member3 = context.getBean("member", MemberDTO.class);
        System.out.println("member3 = " + member3);





    }
}
