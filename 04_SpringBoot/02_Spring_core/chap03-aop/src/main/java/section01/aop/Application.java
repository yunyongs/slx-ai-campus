package section01.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {

    public static void main(String[] args) {
        ApplicationContext context =
                new AnnotationConfigApplicationContext(ContextConfiguration.class);

        MemberService memberService = context.getBean(MemberService.class);

        System.out.println("=========================TARGET 실행=========================");
//        System.out.println(memberService.processMember(false));
        memberService.selectMember();
//        memberService.checkMember();

        //args 예제
//        memberService.registerMember("bear", 20);

        //Annotation
        //memberService.updateMember();
    }
}
