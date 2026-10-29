package section01.aop;

/*
* 사용자 정의 어노테이션 (@interface)
* - 필요한 기능을 구분하기 위해 직접 만드는 어노테이션
* */

import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/*
* @Target: 어노테이션을 붙일 수 있는 위치 정하기
* */
@Target(ElementType.METHOD)

/*
* @Retention: 어노테이션 정보를 언제까지 유지할지
* */
@Retention(RetentionPolicy.RUNTIME)

public @interface AopLog {

}
