package section01.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

// Aspect: 로깅, 성능 측정, 예외 처리같은 공통기능을 모아둔 클래스
// Advice: 실제 공통기능이 작성된 메서드
@Component
@Aspect

public class MemberAspect {

    // selectMember method 적용
    @Before("execution(* section01.aop.MemberServiceImpl.selectMember(..) )")
    public void advicePointCut_selectMember() {
        System.out.println("[Before] 메서드 실행 전...");
    }

    @Before("advicePointCut_selectMember()")
    public void beforeAdvice_selectMember () {
        System.out.println("[Before] 메서드 실행 전...");
    }



    // execution(반환형 패키지.클래스.메서드명(매개변수))
    // * : anything can be
    // (..): 매개변수의 개수와 타입은 무엇이든 가능
    @Pointcut("execution(* section01.aop.MemberServiceImpl.processMember(..) )")
    public void advicePointCut() {}

    @Before("advicePointCut()")
        public void beforeAdvice () {
        System.out.println("[Before] 메서드 실행 전...");
    }

    @AfterReturning(value = "advicePointCut()", returning = "result")
    public void afterReturningAdvice (Object result) {
        System.out.println("[AfterReturning] 반환 값:" + result);
    }

    @After("advicePointCut()")
    public void afterAdvice () {
        System.out.println("[AfterThrowing] 예외...");
    }

    @AfterThrowing(pointcut = "advicePointCut()", throwing = "exception")
    public void afterThrowingAdvice (Exception exception) {
        System.out.println("[After] 메서드 실행 후...");
    }

    @Around("advicePointCut()")
    private Object aroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable{
        long startTime = System.nanoTime();
        System.out.println("[Around-전] 메서드 실행 전");

        try {
            return joinPoint.proceed(); //proceed가 target 메서드를 실행한다.
        } finally {
            long executionTime = System.nanoTime() - startTime;
            System.out.println("[Around-후] 실행 시간:" + executionTime + "ns");
        }
    }

    @Before("within(section01.aop.MemberServiceImpl) && execution(* selectMembers(..))")
    public void withinPointCut() {
        System.out.println("[Within] MemberServiceImpl 적용");
    }

    /*
    * this
    * */
    @Before("this(section01.aop.MemberService) && execution(* checkMember(..))")
    public void thisPointCut() {
        System.out.println("[this] MemberService 적용");
    }

    /*
    * args: 해당 매개 변수를 가진 메소드에 적용
    * */
    @Before(value = "args(name, age) && execution(* registerMember(..))", argNames = "name,age")
    public void argsPointCut(String name, int age) {
        System.out.println("[args] MemberService 적용");
    }

    // 어노테이션 만들기
    @Before("@annotation(section01.aop.AopLog)")
    public void annotationPointCut() {
        System.out.println("[annotation] @AopLog 적용");
    }
}
