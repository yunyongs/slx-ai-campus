![1793583344424](image/261102_SpringBoot_2/1793583344424.png)

HandlerExceptionResolver
DispatcherServlet

- **HandlerExceptionResolver**는 Spring MVC의 요청 처리 과정에서 발생한 예외를 해결하는 인터페이스이다.
- DispatcherServlet은 예외가 발생하면 등록된 예외 처리기에 처리를 위임한다.
- 처리기는 오류 화면을 선택하거나 HTTP 상태와 응답을 구성한다.

구현체	역할
ExceptionHandlerExceptionResolver	@ExceptionHandler 메서드를 찾아 실행
ResponseStatusExceptionResolver	예외 클래스의 @ResponseStatus나 ResponseStatusException을 HTTP 상태로 처리
DefaultHandlerExceptionResolver	표준 Spring MVC 예외를 해당 HTTP 상태로 변환
SimpleMappingExceptionResolver	예외 클래스 이름과 오류 뷰 이름을 연결



## Servlet Filter
![1793588638812](image/261102_SpringBoot_2/1793588638812.png)

- 공통적인 로직들
- 보안 로직들
![1793588805247](image/261102_SpringBoot_2/1793588805247.png)


## Spring Intercept
![1793599233694](image/261102_SpringBoot_2/1793599233694.png)


## 복습 과제: 기존 spring-mvc-assignment에 Filter·Interceptor 붙이기

- 프로젝트: `04_SpringBoot/spring-mvc-assignment` (포트 8081)
- **Controller와 HTML은 새로 만들지 않는다.** 이미 구현한 문제 1~5의 요청(`/mapping`, `/parameters`, `/reservations`, `/session`, `/views`)에 Filter와 Interceptor를 끼워 넣는다.
- 새로 만드는 것은 Filter·Interceptor·설정·예외·Advice 클래스뿐이다. `problem06`(Filter), `problem07`(Interceptor) 패키지에 둔다.
- `support` 패키지, 제공 HTML, 설정 파일은 수정하지 않는다.
- 실행 전에 **결과를 먼저 예측해서 표에 적고**, 실행 후 콘솔 로그와 비교한다.

### 문제 6. 기존 요청에 Filter 여러 개 붙이기

목표: Filter 3개를 순서대로 등록하고, 문제 2·3의 기존 요청이 Filter를 어떤 순서로 지나가는지 확인한다.

| 만들 파일 (`problem06/`) | 내용 |
|---|---|
| `LogFilter.java` | 모든 요청의 HTTP 메서드와 URI 출력 |
| `BadWordFilter.java` | 문제 2-3 검색어 검사 |
| `TimerFilter.java` | 문제 2·3 요청의 수행 시간 측정 |
| `FilterConfig.java` | `@Configuration`, Filter 3개를 `FilterRegistrationBean`으로 등록 |

**6-1. Filter 3개** (모두 `jakarta.servlet.Filter` 구현)

- `LogFilter`: `chain.doFilter()` 전에 `[Log 전] GET /parameters/form`처럼 메서드와 URI를, 후에 `[Log 후] 응답 상태 {상태코드}`를 출력한다. (`HttpServletResponse.getStatus()`)
- `BadWordFilter`: 파라미터 `keyword`가 `광고`이면 `[BadWord] 차단`을 출력하고 400(`SC_BAD_REQUEST`)으로 `sendError()`한 뒤 `chain.doFilter()`를 호출하지 않는다. 아니면 `[BadWord 통과] keyword={값}` → `chain.doFilter()`
- `TimerFilter`: `[Timer 전]` → `chain.doFilter()` → `[Timer 후] {걸린 시간}ms`

**6-2. 등록** (`FilterConfig`: `@Bean` 메서드 3개, 각각 `setFilter()`, `addUrlPatterns()`, `setOrder()`, `setName()`)

| Filter | URL 패턴 | order |
|---|---|---|
| `LogFilter` | `/*` | 1 |
| `BadWordFilter` | `/parameters/search` | 2 |
| `TimerFilter` | `/parameters/*`, `/reservations/*` | 3 |

**6-3. 예측 → 실행** (모두 홈 화면의 기존 링크·폼으로 요청)

| 요청 | 예측한 콘솔 출력 순서 | 실제 |
|---|---|---|
| 홈 `GET /` | | |
| 문제 2 `GET /parameters/form` | | |
| 문제 2 검색 `keyword=자바` | | |
| 문제 2 검색 `keyword=광고` | | |
| 문제 3 예약 미리보기 → 예약 완료 버튼 | | |

생각할 질문

1. "후" 로그는 "전" 로그와 반대 순서로 찍힌다. 왜 그런가? (`chain.doFilter()`도 결국 메서드 호출이다.)
2. `keyword=광고`일 때 `[Timer 전]`이 찍히는가? `[Log 후]`는 찍히는가? 각각 이유는?
3. 문제 3의 예약 완료 버튼 한 번에 `[Log 전]`은 몇 번 찍히는가? 어제 배운 redirect(302)로 설명하라.
4. `/favicon.ico` 같은 요청이 로그에 보인다면, 누가 보낸 요청인가? `LogFilter`의 패턴이 `/*`인 것과 어떤 관계가 있는가?
5. (도전) `LogFilter`와 `TimerFilter`의 order를 서로 바꾸면 `[Timer 후]`의 시간에는 무엇이 더 포함되는가?

### 문제 7. Interceptor에서 던진 예외는 @ControllerAdvice가 처리할까?

목표: 문제 4의 세션 로그인(`nickname`)을 이용해 "로그인한 사람만 예약(문제 3)"을 Interceptor로 만들고, Interceptor와 Filter에서 던진 예외가 각각 어디서 처리되는지 위 그림(Filter는 DispatcherServlet 밖, HandlerExceptionResolver는 안)으로 설명한다.

| 만들 파일 (`problem07/`) | 내용 |
|---|---|
| `LoginRequiredException.java` | `RuntimeException`을 상속한 사용자 정의 예외 |
| `LoginCheckInterceptor.java` | `@Component`, `HandlerInterceptor` 구현 |
| `InterceptorConfig.java` | `@Configuration`, `WebMvcConfigurer` 구현 |
| `AssignmentExceptionAdvice.java` | `@ControllerAdvice` (제공 코드 `ViewLabelAdvice`와 별개로 새로 만든다) |
| `LoginCheckFilter.java` | 7-4에서 사용할 Filter |

**7-1. Interceptor**

- `LoginRequiredException(String message)`: 생성자에서 `super(message)`
- `LoginCheckInterceptor.preHandle()`
  - 세션의 `nickname`을 확인한다. 세션이 없을 때 새로 만들지 않도록 `request.getSession(false)`를 쓰고 `null`도 처리한다.
  - `nickname`이 없으면 `[Interceptor] 로그인 필요`를 출력하고 `new LoginRequiredException("로그인 후 예약할 수 있습니다.")`를 던진다.
  - 있으면 `[Interceptor] {nickname} 통과`를 출력하고 `true`를 반환한다.
- `postHandle()`, `afterCompletion()`에도 각각 한 줄씩 출력을 넣는다.
- `InterceptorConfig`: `/reservations/**`에만 적용한다.

**7-2. Advice**

- `AssignmentExceptionAdvice`: `@ExceptionHandler(LoginRequiredException.class)` 메서드에서 `[Advice] LoginRequiredException 처리`를 출력하고, Model에 `message=예외 메시지`를 넣고, 기존 `result` 뷰를 반환한다.
- `result` 화면의 제목 번호나 Controller 이름이 비어 보여도 정상이다.

**7-3. 예측 → 실행**

| 순서 | 요청 | 예측: 화면 / 콘솔에 찍히는 줄 | 실제 |
|---|---|---|---|
| 1 | 로그인하지 않고 문제 3 예약 미리보기 | | |
| 2 | 문제 4에서 김학생으로 로그인 | | |
| 3 | 다시 문제 3 예약 미리보기 → 예약 완료 | | |
| 4 | 문제 4에서 로그아웃 후 `GET /reservations/complete` | | |

생각할 질문

1. 1번 요청에서 `ReservationController.preview()`는 실행되는가? 이 Interceptor의 `afterCompletion()`은 찍히는가?
2. 예외가 Advice까지 갔다면, 위 그림에서 어떤 경로로 HandlerExceptionResolver에 도착했는가?
3. `getSession()`과 `getSession(false)`의 차이는? 로그인 확인에 `getSession()`을 쓰면 어떤 일이 생기는가?

**7-4. 같은 예외를 Filter에서 던지면?**

- `LoginCheckFilter`: 세션에 `nickname`이 없으면 `[Filter] 로그인 필요`를 출력하고 `LoginRequiredException`을 던진다. `FilterRegistrationBean`으로 문제 1의 `/mapping/rooms/*`에만 등록한다. (문제 6의 `FilterConfig`에 `@Bean`을 하나 더 추가해도 된다.)
- 로그아웃 상태에서 홈의 "7번 스터디룸 조회" 링크를 누른다.

생각할 질문

1. 화면은 7-3의 1번처럼 `result`인가, 다른 화면인가? HTTP 상태 코드는? (개발자 도구 Network 탭)
2. 7-3과 결과가 다르다면, 위 그림의 Filter와 HandlerExceptionResolver 위치로 이유를 설명하라.
3. `LoginRequiredException`이 `Exception`(checked)을 상속했다면 `doFilter()`에서 던질 수 있었을까? `doFilter()`의 `throws` 목록을 보고 답하라.

**7-5. (도전)**

1. `ReservationController` 안에 `@ExceptionHandler(LoginRequiredException.class)`를 하나 더 만들면, `preHandle()`에서 던진 예외는 Controller 쪽과 Advice 쪽 중 어디서 처리되는가? (힌트: `preHandle()` 시점에 DispatcherServlet은 이미 어떤 Handler로 갈지 알고 있는가?) 확인 후 추가한 메서드는 지운다.
2. 예외 대신 `response.sendRedirect("/session/home")` 후 `return false`로 바꾸면 사용자 경험이 어떻게 달라지는가? 실제 서비스의 로그인 체크에는 어느 쪽이 더 맞는가?
3. 예외를 `postHandle()`에서 던지면 Advice가 처리하는가? 이때 Controller는 이미 실행됐는가?
