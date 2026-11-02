# 수업 내용

2026년 10월 30일 Spring Boot
- Spring Boot 설정 하이라키 (`@SpringBootApplication` 구조)
- Spring MVC Request 처리 과정, 요청 매핑 (`@RequestMapping`, `@GetMapping`, 클래스 레벨 매핑, `@PathVariable`)
- Java · Spring · Spring Boot와 AI 엔지니어링 · 데이터 엔지니어링의 관계


## 1. Spring Boot 설정 하이라키

![1793321024271](image/261030_SpringBoot/1793321024271.png)

**이미지 설명**
- `@SpringBootApplication` 하나가 내부적으로 **어떤 어노테이션들로 이루어져 있는지** 보여 주는 계층도
- 그림 읽는 법
  - 초록색 `@` 아이콘 = 어노테이션 하나
  - `└→` 화살표 = 왼쪽 어노테이션의 정의 (`@interface`) 위에 **붙어 있는** 어노테이션 (부모 → 자식)
  - 같은 세로선에서 뻗어 나온 화살표들 = 같은 부모 아래의 형제
- 맨 위 `@SpringBootApplication`에서 **세 갈래**로 나뉨
  1. `@ComponentScan`: 더 내려가지 않고 끝
  2. `@SpringBootConfiguration` → `@Configuration` → `@Component`: 세 단계로 이어짐 → 결국 메인 클래스도 `@Component`, 즉 **Bean으로 등록됨**
  3. `@EnableAutoConfiguration` → `@Import`, `@AutoConfigurationPackage` 두 갈래로 나뉘고, `@AutoConfigurationPackage` 아래에 다시 `@Import`
- 그림에 `@Import`가 **두 번** 나오지만 가져오는 클래스가 다름 (그림에서는 괄호 안 클래스가 생략됨)
  - 위쪽 `@Import(AutoConfigurationImportSelector.class)`: 자동 설정 클래스 목록을 가져옴
  - 아래쪽 `@Import(AutoConfigurationPackages.Registrar.class)`: 메인 클래스의 패키지를 등록

### 1.1. 어노테이션 계층 구조

```
@SpringBootApplication
├── @ComponentScan
├── @SpringBootConfiguration
│   └── @Configuration
│       └── @Component
└── @EnableAutoConfiguration
    ├── @Import(AutoConfigurationImportSelector.class)
    └── @AutoConfigurationPackage
        └── @Import(AutoConfigurationPackages.Registrar.class)
```

- **메타 어노테이션 (Meta-annotation)**: 어노테이션 위에 붙은 어노테이션
- **합성 어노테이션 (Composed Annotation)**: 여러 어노테이션을 하나로 묶은 것
  - 메인 클래스에 `@SpringBootApplication` 하나만 붙이면, 그 아래 계층의 어노테이션이 모두 함께 적용됨

### 1.2. `@SpringBootApplication` 내부 (간략화)

```java
// Spring Boot 소스 (@Target, @Retention 등은 생략)
@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan(excludeFilters = { ... })
public @interface SpringBootApplication {
}

@Configuration
public @interface SpringBootConfiguration { }

@Component
public @interface Configuration { }

@AutoConfigurationPackage
@Import(AutoConfigurationImportSelector.class)
public @interface EnableAutoConfiguration { }

@Import(AutoConfigurationPackages.Registrar.class)
public @interface AutoConfigurationPackage { }
```

- 어제 만든 `@AopLog`처럼 `@interface`로 정의된 어노테이션이고, 그 위에 다른 어노테이션이 붙어 있는 구조

### 1.3. 각 어노테이션의 역할

| 어노테이션 | 역할 |
| --- | --- |
| `@SpringBootApplication` | 아래 3개 (`@ComponentScan`, `@SpringBootConfiguration`, `@EnableAutoConfiguration`)를 묶은 합성 어노테이션, 메인 클래스에 붙임 |
| `@ComponentScan` | 이 클래스가 있는 **패키지와 그 하위 패키지**를 스캔해서 `@Component`가 붙은 클래스를 Bean으로 등록 |
| `@SpringBootConfiguration` | 스프링 부트의 설정 클래스라는 표시, 사실상 `@Configuration`과 같음 (테스트 `@SpringBootTest`가 설정 클래스를 자동으로 찾을 때 사용) |
| `@Configuration` | `@Bean` 메서드로 Bean을 등록하는 설정 클래스, `@Bean` 메서드를 여러 번 호출해도 싱글톤 유지 (CGLIB 프록시) |
| `@Component` | 컴포넌트 스캔 대상이라는 표시 → `@Configuration` 클래스 자신도 Bean으로 등록됨 |
| `@EnableAutoConfiguration` | 자동 설정 (Auto-configuration) 켜기: 추가한 의존성 (라이브러리)을 보고 필요한 Bean을 자동으로 등록 |
| `@Import(AutoConfigurationImportSelector.class)` | 자동 설정 클래스 목록을 읽어 와서, 조건에 맞는 것만 등록 |
| `@AutoConfigurationPackage` | 메인 클래스의 패키지를 "자동 설정 패키지"로 기억 |
| `@Import(AutoConfigurationPackages.Registrar.class)` | 그 패키지 이름을 등록 → JPA `@Entity`, Spring Data Repository를 찾기 시작하는 위치로 사용 |

- `@Import`: 다른 설정 클래스 (또는 Bean 등록기)를 가져와서 함께 적용
- `@Service`, `@Repository`, `@Controller`, `@Configuration`도 내부에 `@Component`가 붙어 있어서 컴포넌트 스캔 대상이 됨

**자동 설정 (Auto-configuration) 동작**
- 자동 설정 클래스 목록 파일: 라이브러리 jar 안의 `META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports` (Spring Boot 2.7 이전에는 `META-INF/spring.factories`)
- 목록에 있다고 다 등록되는 게 아니라 **조건부 (`@Conditional...`)** 로 등록
  - `@ConditionalOnClass`: 해당 클래스 (라이브러리)가 있을 때만
  - `@ConditionalOnMissingBean`: 개발자가 같은 타입의 Bean을 직접 등록하지 않았을 때만 → **내가 등록한 Bean이 우선**
- 예: `spring-boot-starter-webmvc` 추가 → `DispatcherServlet` (스프링의 Front Controller), 내장 Tomcat (Embedded Tomcat)이 자동 등록

### 1.4. 두 갈래로 정리

| 갈래 | 등록하는 Bean |
| --- | --- |
| `@ComponentScan` | **내가 만든** 클래스 (`@Controller`, `@Service`, `@Repository`, `@Component`) |
| `@EnableAutoConfiguration` | **라이브러리가 제공하는** 클래스 (`DispatcherServlet`, 내장 Tomcat, `DataSource` 등) |
| `@SpringBootConfiguration` | 메인 클래스 자신도 설정 클래스 → 메인 클래스 안에 `@Bean` 메서드 작성 가능 |

### 1.5. 주의: 메인 클래스 위치
- `@ComponentScan`은 메인 클래스의 패키지부터 **아래로만** 스캔 → 메인 클래스는 **최상위 (root) 패키지**에 둠

```
demo
├── Application.java              ← @SpringBootApplication (여기부터 스캔)
├── controller
│   └── MenuController.java       ← 스캔 O
└── service
    └── MenuService.java          ← 스캔 O
other
└── OtherService.java             ← 스캔 X (demo 패키지 밖)
```


## 한 줄 정리
- **`@SpringBootApplication`** = `@ComponentScan` + `@SpringBootConfiguration` + `@EnableAutoConfiguration`
- **`@ComponentScan`**: 내가 만든 Bean을 찾아서 등록
- **`@SpringBootConfiguration`**: 메인 클래스도 설정 클래스 (`@Configuration` → `@Component`)
- **`@EnableAutoConfiguration`**: 라이브러리 Bean을 조건에 맞게 자동 등록


![1793321308819](image/261030_SpringBoot/1793321308819.png)

**이미지 설명**
- 스프링 부트 **스타터 목록** 표 (왼쪽: 스타터 이름, 오른쪽: 설명)
- 스타터 = 기능 하나에 필요한 라이브러리를 **서로 맞는 버전으로 한 번에** 가져오는 의존성 묶음
- 필기
  - ✓ 표시: 이 과정에서 쓸 스타터 → `webmvc` (웹), `data-jpa` (→ Database), `security` (→ 인증/인가)
  - `aop` 앞 화살표: 어제 배운 AOP를 스프링 부트에서 쓸 때의 스타터
  - `thymeleaf` 빨간 박스 → **HTML 템플릿 엔진**: 서버에서 HTML에 데이터를 채워 넣어 화면을 만드는 도구 (오늘 `mappingResult.html`)
- Spring Initializr에서 `Spring Web`을 고르면 `spring-boot-starter-webmvc`가 추가됨
- 오늘 chap01-request-mapping의 build.gradle에 들어간 스타터: `spring-boot-starter-webmvc` + `spring-boot-starter-thymeleaf`

-thymeleaf는 간단하게만 (우리는 실제로는 리엑트에서 사용할 것임)

Jar / War

![1793324130610](image/261030_SpringBoot/1793324130610.png)

![1793324153507](image/261030_SpringBoot/1793324153507.png)

**이미지 설명** (위 두 장은 같은 그림, 두 번째 장에 필기 추가)
- **계층형 아키텍처 (Layered Architecture, 레이어드 아키텍처)**: 애플리케이션을 역할별 계층으로 나눈 구조
- 왼쪽 → 오른쪽으로 요청이 흐름: User Application (Browser, Java Application) → Controller Layer (`ProductController`) → Service Layer (`ProductService`) → Repository Layer (`ProductRepository`) → Database (Product Table)
- 그림 읽는 법: 상자 = 메서드, 화살표 = 왼쪽 메서드가 오른쪽 메서드를 **호출**
  - 예: Browser → `deleteProduct()` (Controller) → `deleteProduct()` (Service) → `deleteById()` (Repository) → Product Table
- 계층마다 메서드 이름이 다름
  - Controller: 요청 이름 (`createProduct`, `getAllProducts`)
  - Service: 업무 이름 (`saveProduct`, `findAllProducts`)
  - Repository: DB 작업 이름 (`save`, `findById`, `findAll`, `deleteById`), 수정 (`updateProduct`)도 Repository에서는 `save()`로 처리 (JPA 방식, 나중에 배움)
- 클라이언트는 브라우저만이 아님: **Java Application** 같은 다른 프로그램도 같은 Controller에 요청을 보낼 수 있음
- 빨간 필기: Controller = 요청을 받는, Service = 비즈니스 로직, Repository = 데이터베이스
- 아래 MVC 그림의 파란 필기 (핸들러 (컨트롤러) → Service → Repository)가 바로 이 구조

Controller: 요청을 받는
Service: 비즈니스 로직
Database: 데이터


## MVC Request 처리과정
![1793324390968](image/261030_SpringBoot/1793324390968.png)
![1793324426766](image/261030_SpringBoot/1793324426766.png)
![1793324471507](image/261030_SpringBoot/1793324471507.png)

**이미지 설명** (세 장은 같은 그림, 수업 중에 필기가 차례로 추가됨)
- **Spring MVC Request 처리 과정**: 클라이언트의 HTTP 요청이 HTML 응답이 될 때까지의 흐름
- 가운데 **DispatcherServlet**이 모든 요청을 먼저 받고, ①~⑧ 순서로 다른 객체에게 일을 시킴 (Front Controller)
  1. 핸들러 조회: 핸들러 매핑에게 "이 요청을 처리할 메서드가 누구야?"
  2. 핸들러 어댑터 조회: 그 메서드를 실행할 수 있는 어댑터 찾기
  3. handle(handler): 어댑터에게 실행을 맡김
  4. handler 호출: 어댑터가 컨트롤러 메서드를 실제로 호출
  5. ModelAndView 반환: 데이터 (Model) + 화면 이름 (View 이름)을 돌려줌
  6. viewResolver 호출: 화면 이름으로 실제 화면 파일 찾기
  7. View 반환
  8. render(model) 호출: 화면 파일에 Model 데이터를 채워 HTML을 만들고 응답
- 파란 필기 (첫 번째 장부터)
  - 핸들러 (컨트롤러) → 점선 오른쪽 **Service → Repository**: 컨트롤러는 요청만 받고, 실제 일은 Service와 Repository에 맡김 (위 계층형 아키텍처)
  - Controller → `"member/list"`: 컨트롤러가 돌려주는 것은 **화면 이름 (문자열)**
  - `.jsp`, **forward / redirect**: 그 이름으로 화면 파일 (JSP)을 찾아 forward 하거나 redirect 함 (10/28 서블릿)
  - viewResolver 동그라미 → **"1. 어떤 화면을 보여줄지 결정"**
  - View 상자 → 왼쪽 화살표: 완성된 HTML이 클라이언트로 감
- 빨간 필기 (두 번째, 세 번째 장)
  - 핸들러 매핑 → **Request Mapping**: 핸들러 매핑은 `@RequestMapping`에 적힌 **HTTP 메서드 (get, post, put, delete) + URL**로 처리할 메서드를 찾음
  - 클라이언트 옆 상자 **body, HTTP 정보, 값**: HTTP 요청에 담겨 오는 것들
  - → **HandlerMethod**: 요청을 처리하는 컨트롤러의 메서드 (핸들러 메서드), 요청에 담긴 값은 이 메서드의 매개변수로 들어옴

### 코드로 따라가기: chap01-request-mapping

**0. 요청 전 (앱이 시작할 때) 준비되는 것**
- `Chap01RequestMappingApplication.main()` → `SpringApplication.run()`
- `@EnableAutoConfiguration` (자동 설정)
  - `spring-boot-starter-webmvc` → `DispatcherServlet`, 핸들러 매핑, 핸들러 어댑터, 내장 Tomcat (포트는 `application.yaml`의 `server.port: 8081`)
  - `spring-boot-starter-thymeleaf` → `ThymeleafViewResolver` (앞에 `classpath:/templates/`, 뒤에 `.html`을 붙임)
- `@ComponentScan` → `org.example.requestmapping` 하위의 컨트롤러 2개를 Bean으로 등록
  - `chap01.MethodMappingTestController` (`@Controller`)
  - `chap02.ClassMappingTestController` (`@Controller`)
- 핸들러 매핑이 두 컨트롤러의 매핑 어노테이션을 읽어서 아래 **매핑 표**를 미리 만들어 둠

**핸들러 매핑 표 (① 핸들러 조회 때 보는 표)**

| HTTP 메서드 | URL | 핸들러 메서드 | 어노테이션 |
| --- | --- | --- | --- |
| 모두 (GET, POST, PUT, DELETE …) | `/menu/regist` | `MethodMappingTestController.registMenu()` | `@RequestMapping("/menu/regist")` |
| GET | `/menu/modify` | `MethodMappingTestController.modifyMenu()` | `@GetMapping("menu/modify")` |
| GET | `/order/regist` | `ClassMappingTestController.registOrder()` | 클래스 `@RequestMapping("/order/*")` + `@GetMapping("/regist")` |
| POST | `/order/modify`, `/order/delete` | `ClassMappingTestController.modifyAndDelete()` | 클래스 + `@RequestMapping(value = {"modify", "delete"}, method = {RequestMethod.POST})` |
| GET | `/order/detail/{orderNo}` | `ClassMappingTestController.selectOrderDetail()` | 클래스 + `@GetMapping("/detail/{orderNo}")` |
| 모두 | `/order/*` (위 URL에 안 걸린 나머지) | `ClassMappingTestController.anyRequest()` | 클래스 `/order/*` + `@RequestMapping("/*")` |

- `@RequestMapping`에 HTTP 메서드를 지정하지 않으면 **모든 메서드**가 들어옴
- **요청 전용 어노테이션** (`@GetMapping`, `@PostMapping`, `@PutMapping`, `@DeleteMapping`, `@PatchMapping`): 그 HTTP 메서드만 받도록 제한 (빨간 필기 get, post, put, delete)
  - `@GetMapping("/menu/modify")` = `@RequestMapping(value = "/menu/modify", method = RequestMethod.GET)`
- `@GetMapping("menu/modify")`처럼 앞의 `/`를 빼도 스프링이 붙여 줌 → `/menu/modify`

**클래스 레벨 매핑 (chap02): 클래스 URL + 메서드 URL**
- 클래스에 붙인 `@RequestMapping("/order/*")` = 이 클래스의 모든 핸들러 메서드 URL의 **앞부분 (공통 경로)**
- 합치는 규칙: 클래스 URL이 `/*`로 끝나면 `*`를 떼고 메서드 URL을 이어 붙임

| 클래스 | 메서드 | 결과 |
| --- | --- | --- |
| `/order/*` | `/regist` | `/order/regist` |
| `/order/*` | `{"modify", "delete"}` | `/order/modify`, `/order/delete` (URL 2개 → 핸들러 메서드 1개) |
| `/order/*` | `/detail/{orderNo}` | `/order/detail/{orderNo}` |
| `/order/*` | `/*` | `/order/*` ✅ (지금 `anyRequest()`) |
| `/order/*` | `/order/*` | `/order/order/*` ❌ (처음 코드) |

- **`anyRequest()` 고친 과정**
  - 처음: `@RequestMapping("/order/*")` → 클래스 경로와 겹쳐서 `/order/order/*`가 됨 → `/order/abc`는 매핑이 없어서 404
  - 수정: `@RequestMapping("/*")` → `/order/*` → 다른 매핑에 안 걸린 `/order/...` 요청을 받음 (주석 "그외 모든 리퀘스트 요청 처리"와 일치)
  - 둘 다 스프링 7.0.10의 `PathPattern.combine()`으로 직접 확인한 결과
- 여러 매핑이 동시에 맞으면 **더 구체적인 매핑이 우선**: `/order/regist`는 `/order/*`보다 구체적이라 `registOrder()`가 받음
- `*`는 경로 **한 칸**만 받음: `/order/abc` O, `/order/detail/abc` X

**`@PathVariable`: URL 안의 값 → 핸들러 메서드 매개변수**
- `@GetMapping("/detail/{orderNo}")`: `{orderNo}` 자리에 오는 값을 변수로 받음 (조회, 특히 단건 조회)
- `GET /order/detail/3` → ③ 핸들러 어댑터가 URL에서 `3`을 꺼내 `int`로 바꿔서 `orderNo` 매개변수에 넣음 → 빨간 필기 **"값 → HandlerMethod"**
- `@PathVariable int orderNo`처럼 이름을 생략하면 매개변수 이름 (`orderNo`)으로 찾음, 매개변수 이름이 다르면 `@PathVariable("orderNo") int a`처럼 적어야 함
  - 매개변수 이름을 읽을 수 있는 이유: 스프링 부트 Gradle 플러그인이 컴파일 옵션 `-parameters`를 켜 줌
- **주의: 숫자가 아닌 값** (`/order/detail/abc`)
  - ① 핸들러 조회는 **URL 글자만** 봄 → `{orderNo}`는 `abc`에도 맞음 → `selectOrderDetail()` 선택
  - ③에서 `"abc"` → `int` 변환 실패 → **400 Bad Request**
  - 숫자만 받으려면 경로 변수에 정규식을 붙임: `{변수명:정규식}` → `@GetMapping("/detail/{orderNo:\\d+}")` (`\d+` = 숫자 1개 이상, 자바 문자열에서는 `\\`)
  - 그러면 `abc`는 이 매핑에 안 맞아서 다른 매핑 (예: `/detail/*`)으로 넘어감

**흐름도**

```mermaid
flowchart TD
    A["브라우저<br/>static/index.html 버튼 클릭"] -->|"HTTP 요청 (HTTP 메서드 + URL)"| B["DispatcherServlet<br/>(자동 설정)"]
    B -->|"① 핸들러 조회"| C["핸들러 매핑<br/>RequestMappingHandlerMapping<br/>매핑 표에서 HTTP 메서드 + URL로 찾기"]
    C -.->|"못 찾음"| X["에러 응답<br/>URL 없음 → 404<br/>메서드 불일치 → 405 (POST /menu/modify)"]
    B -->|"② 어댑터 조회<br/>③ handle(handler)"| D["핸들러 어댑터<br/>RequestMappingHandlerAdapter<br/>매개변수 준비: Model, PathVariable"]
    D -->|"④ handler 호출"| E["chap01/MethodMappingTestController.java<br/>registMenu(), modifyMenu()<br/>chap02/ClassMappingTestController.java<br/>registOrder(), modifyAndDelete(), selectOrderDetail()"]
    E -.->|"Model에 message + return mappingResult"| D
    D -.->|"⑤ ModelAndView 반환"| B
    B -->|"⑥ viewResolver 호출"| F["ThymeleafViewResolver<br/>templates/ + mappingResult + .html"]
    F -.->|"⑦ View 반환"| B
    B -->|"⑧ render(model)"| G["templates/mappingResult.html<br/>th:text 로 message 출력"]
    G -->|"HTML 응답"| A
```

**단계별 설명**

| 그림 | 스프링이 하는 일 | 예 1: GET `/menu/regist` | 예 2: GET `/order/detail/3` |
| --- | --- | --- | --- |
| HTTP 요청 | 브라우저 → 내장 Tomcat (8081) → `DispatcherServlet` | `index.html`의 [GET 메뉴 등록 요청] 버튼 | 주소창에 `localhost:8081/order/detail/3` 입력 (index.html에는 버튼 없음) |
| ① 핸들러 조회 | `RequestMappingHandlerMapping`이 매핑 표에서 (HTTP 메서드 + URL)로 찾음 | `registMenu()` | `/order/detail/{orderNo}` → `selectOrderDetail()` |
| ② 핸들러 어댑터 조회 | `RequestMappingHandlerAdapter` (`@RequestMapping` 계열 메서드를 실행하는 어댑터) | 같음 | 같음 |
| ③ handle(handler) | 어댑터가 핸들러 메서드의 매개변수를 준비 | `Model` 객체 | `Model` 객체 + URL의 `3` → `int orderNo` |
| ④ handler 호출 | 어댑터가 핸들러 메서드 호출 | `model.addAttribute("message", "신규 등록된 핸들러 메소드 호출함!")` | `model.addAttribute("message", orderNo + "번 주문 상세 조회합니다.")` |
| ⑤ ModelAndView 반환 | Model + View 이름 (반환한 `String`을 어댑터가 ModelAndView로 바꿔 줌) | `return "mappingResult";` | `return "mappingResult";` |
| ⑥ viewResolver 호출 | `ThymeleafViewResolver`: `classpath:/templates/` + 뷰 이름 + `.html` | `templates/mappingResult.html` | 같음 (모든 핸들러 메서드가 같은 뷰를 씀) |
| ⑦ View 반환 | Thymeleaf View 객체 | 같음 | 같음 |
| ⑧ render(model) | `<h3 th:text="${ message }">`에 Model의 `message`를 채움 | 신규 등록된 핸들러 메소드 호출함! | 3번 주문 상세 조회합니다. |
| HTML 응답 | 완성된 HTML → 브라우저 | 위 문장이 `<h3>`로 표시 | 위 문장이 `<h3>`로 표시 |

**index.html 버튼별 결과**

| 버튼 | 요청 | ① 핸들러 조회 결과 | 화면 |
| --- | --- | --- | --- |
| GET 메뉴 등록 요청 | GET `/menu/regist` | `registMenu()` | 신규 등록된 핸들러 메소드 호출함! |
| POST 메뉴 등록 요청 | POST `/menu/regist` | `registMenu()` (메서드 지정이 없어서 POST도 받음) | 신규 등록된 핸들러 메소드 호출함! |
| GET 메뉴 수정 요청 | GET `/menu/modify` | `modifyMenu()` | GET 방식의 핸들러메소드 호출함 |
| POST 메뉴 수정 요청 | POST `/menu/modify` | URL은 있지만 GET 전용 → 핸들러 없음 | **405 Method Not Allowed** 에러 페이지 (② 이후 실행 안 됨) |
| GET 메뉴 등록 요청 (Chap02) | GET `/order/regist` | `registOrder()` | /order/regist 요청 결과 |
| POST 주문 수정 요청 | POST `/order/modify` | `modifyAndDelete()` | /order/modify와 /order/delete 요청 결과 |
| POST 주문 삭제 요청 | POST `/order/delete` | `modifyAndDelete()` | /order/modify와 /order/delete 요청 결과 |

- `static/` vs `templates/`
  - `static/index.html`: DispatcherServlet은 거치지만 내 컨트롤러 대신 **정적 리소스 핸들러**가 파일을 그대로 돌려줌 (⑥~⑧ 없음)
  - `templates/mappingResult.html`: 컨트롤러가 뷰 이름을 돌려줘야 Thymeleaf가 데이터를 채워서 응답, URL로 직접 열 수 없음 (`/mappingResult.html` → 404)

### 10/28 직접 만든 FrontController와 비교

| 그림 | 10/28 `FrontControllerServlet` (직접 만듦) | 오늘 Spring MVC (스프링이 해 줌) |
| --- | --- | --- |
| DispatcherServlet | `@WebServlet(value = "/")` `FrontControllerServlet` | `DispatcherServlet` (자동 설정) |
| ① 핸들러 매핑 | `dispatchGet()`의 `switch (requestPath)` | `@RequestMapping`, `@GetMapping` → 매핑 표 |
| HTTP 메서드 구분 | `doGet()` → `dispatchGet()`, `doPost()` → `dispatchPost()`로 나눔 | `@GetMapping`, `@PostMapping` 또는 `method = RequestMethod.POST` |
| 공통 경로 | 없음 (`case`마다 `/todo/...` 전체를 씀) | 클래스에 `@RequestMapping("/order/*")` |
| URL 안의 값 | 직접 잘라야 함 (`getRequestPath()`의 `substring()`처럼) | `@PathVariable` |
| 핸들러 (컨트롤러) | `new TodoController()`로 직접 생성 | `@Controller` → Bean으로 등록, 스프링이 호출 |
| 데이터 담기 | `req.setAttribute("todoList", todoList)` | `model.addAttribute("message", ...)` |
| 뷰 이름 반환 | `return "todo/list";` | `return "mappingResult";` |
| ⑥ viewResolver | `render()`: `VIEW_PREFIX + viewName + VIEW_SUFFIX` → `/WEB-INF/views/todo/list.jsp` | `ThymeleafViewResolver`: `classpath:/templates/` + 뷰 이름 + `.html` |
| ⑧ render | `dispatcher.forward(req, resp)` → JSP | Thymeleaf가 HTML 생성 |
| redirect | `viewName.startsWith("redirect:")` → `resp.sendRedirect()` | 뷰 이름 앞에 `redirect:`를 붙이면 똑같이 redirect |
| 매핑 없음 | `default: return null;` → `sendError(404)` | URL 없음 → 404, 메서드 불일치 → 405 (자동) |

→ 10/28에 손으로 만든 `switch` (핸들러 매핑)와 `render()` (viewResolver)를 **스프링이 대신 해 주는 것**이 Spring MVC


## CSR / SSR
클라이언트 사이드 렌더링 / 서버 사이드 렌더링
- 렌더링은 데이터를 바탕으로 사용자가 볼 화면을 만드는 과정이다. 

-- 우리는 실제 프로젝트는 CSR로 할 것임
다만 Spring boot에서는 MVC모델을 써야하기 때문에 SSR을 사용할 것임

## 프로젝트 계획을 잘 해야 한다. 
- 리엑트는 바이브 코딩으로 할 것임 : 디자인 시스템을 잘 찾아야 함, 지도 관련 앱을 만들어 보자. / 아니면 금융 관련 - 데이터 분석 - 연관성-상관관계 분석./ 경제상황 - 매크로 분석 / 매크로 상황에서의 종목 성향 분석: 경제상황에 따른 비중 조절을 어떻게 할 것인가?

## Java · Spring · Spring Boot와 AI 엔지니어링 · 데이터 엔지니어링의 관계

### 1. Java · Spring · Spring Boot ↔ AI 엔지니어링

**핵심**
- AI 모델 연구·학습은 주로 **Python**
- 그 모델을 실제 서비스로 만드는 **백엔드**는 Java / Spring Boot가 많이 맡음 → AI 제품의 대부분은 백엔드 (요청 받기 → 입력 검증 → 데이터 조회 → 모델 호출 → 결과 저장 → 배포)

**오늘 배운 Spring MVC 흐름 = AI 채팅 요청 흐름**

```mermaid
flowchart TD
    A["React 채팅 화면<br/>(프론트엔드)"] -->|"POST /chat"| B["DispatcherServlet → ChatController<br/>@PostMapping /chat<br/>(오늘 배운 ①~④)"]
    B --> C["ChatService<br/>ChatClient Bean을 주입받음 (DI)"]
    C -->|"1. 조회"| D[("Repository (JPA)<br/>대화 기록, 사용자 정보<br/>MySQL")]
    C -->|"2. 검색"| E[("VectorStore<br/>RAG: 관련 문서 검색")]
    C -->|"3. 조립"| F["프롬프트 만들기<br/>질문 + 대화 기록 + 문서"]
    C -->|"4. 호출"| G["LLM API (Claude 등)<br/>Spring AI ChatClient로 호출"]
    G -.->|"모델 응답"| C
    C -.->|"JSON / 스트리밍 응답"| A
```

**Java · Spring · Spring Boot가 AI 엔지니어링에서 하는 일**

| 기술 | AI 엔지니어링에서의 역할 |
| --- | --- |
| **Java** | 기업 백엔드 표준 언어, 타입이 엄격해서 큰 시스템에 안정적 / Java 21 **가상 스레드** (virtual thread): LLM 응답을 기다리는 요청 수천 개를 적은 자원으로 처리 (`spring.threads.virtual.enabled: true`) |
| **Spring** | **DI**: LLM 클라이언트 (`ChatClient`)를 Bean으로 주입 / **AOP**: LLM 호출 로깅, 비용·시간 측정 같은 공통 기능 분리 (어제 배운 횡단 관심사) |
| **Spring Boot** | **스타터 + 자동 설정**으로 LLM 연동: `spring-ai-starter-model-anthropic`을 추가하면 `ChatClient` 관련 Bean이 자동 등록 (오늘 배운 `@EnableAutoConfiguration`과 같은 원리) |
| **Spring AI** | 스프링 공식 AI 프로젝트: `ChatClient` (LLM 호출), `VectorStore` (RAG), `@Tool` (내 메서드를 LLM tool로 공개), MCP 서버/클라이언트 스타터, **Advisor** (LLM 호출 앞뒤에 기능을 끼움 = AOP의 Advice와 같은 생각) |
| **LangChain4j** | Python LangChain의 Java 버전 |

**Python과의 역할 분담**

| 영역 | 주로 쓰는 것 |
| --- | --- |
| 모델 연구·학습, 데이터 과학 | Python (PyTorch, pandas) |
| LangChain / LangGraph | 주로 Python (Java는 LangChain4j, Spring AI) |
| 서비스 백엔드 (인증, 결제, DB, 대규모 트래픽) | **Java / Spring Boot** (+ Spring AI) |
| 흔한 실무 구성 | **Spring Boot 백엔드 + Python AI 서비스**, 또는 Spring AI로 Java 안에서 바로 처리 |

**가장 직접적인 연결: 에이전트는 API를 호출한다**
- 에이전트의 **tool** = 이름 + 설명 + 매개변수가 있는 함수 → 대부분 오늘 만든 것 같은 **엔드포인트** (`@GetMapping`)
  - Spring AI에서는 메서드에 `@Tool`을 붙이면 LLM이 그 메서드를 tool로 호출할 수 있음
- 예: "3번 주문 뭐야?" → 에이전트가 `GET /order/detail/3` 호출
- 그래서 **입력 검증**이 더 중요: LLM이 `abc` 같은 잘못된 값을 보낼 수 있음 → 백엔드는 믿지 말고 검증 (`{orderNo:\\d+}`)

### 2. Java · Spring · Spring Boot ↔ 데이터 엔지니어링

**핵심**
- 백엔드 (Spring Boot)가 데이터를 **만들고**, 데이터 엔지니어링이 분석할 수 있게 **옮기고 다듬고**, AI·분석이 **사용**
- 빅데이터 도구 대부분이 **JVM (자바 가상 머신) 위에서 동작** → Java를 알면 데이터 인프라를 이해하기 쉬움

```mermaid
flowchart TD
    A["Spring Boot 앱 (주문, 사용자)<br/>요청 1건 = 행 1개"] -->|"JPA / JDBC로 저장"| B[("MySQL<br/>OLTP: 트랜잭션")]
    B -->|"추출 (Extract)<br/>배치: Spring Batch, Airflow<br/>실시간: Debezium → Kafka (Spring Kafka)"| C[("데이터 레이크 / 웨어하우스<br/>S3, BigQuery, Snowflake")]
    C -->|"변환 (Transform)<br/>SQL, Spark, dbt<br/>정제 · 조인 · 집계"| D["사용처<br/>대시보드 · ML 학습<br/>RAG 인덱스 (임베딩) · AI 에이전트"]
```

**JVM 위에서 동작하는 데이터 도구**

| 도구 | 언어 | 하는 일 |
| --- | --- | --- |
| Apache Kafka | Java, Scala | 실시간 이벤트 (주문, 클릭 등) 전달 |
| Apache Spark | Scala (JVM) | 대용량 데이터 분산 처리 |
| Apache Hadoop | Java | 대용량 분산 저장·처리 (빅데이터의 시작) |
| Apache Flink | Java | 실시간 스트림 처리 |
| Elasticsearch | Java | 검색, 로그 분석 |
| Debezium | Java | DB 변경 내용 (INSERT, UPDATE)을 Kafka로 보냄 (CDC) |

**Spring이 데이터 엔지니어링에서 하는 일**

| 기술 | 역할 |
| --- | --- |
| **Spring Batch** (`spring-boot-starter-batch`, 오늘 스타터 표) | ETL 작업: 읽기 → 가공 → 쓰기를 묶음 (chunk) 단위로, 실패하면 이어서 재시작 |
| **Spring for Apache Kafka** (`spring-kafka`) | Spring Boot 앱이 Kafka로 이벤트를 보내고 받음 |
| **JDBC** (03_jdbc) | Spark, Kafka Connect가 MySQL을 읽을 때도 JDBC 드라이버를 씀 → 배운 지식이 그대로 쓰임 |

**백엔드 vs 데이터 엔지니어링**

| 구분 | 백엔드 (Spring Boot, 지금 배우는 것) | 데이터 엔지니어링 |
| --- | --- | --- |
| 작업 종류 | **OLTP**: 주문 1건씩 (`INSERT`, `UPDATE` 한 행) | **OLAP**: 수백만 행을 집계 |
| 데이터 모델 | 정규화 1NF~3NF (10/21 ERD) | 빠른 분석을 위해 일부러 비정규화 (스타 스키마: 팩트 + 디멘션 테이블) |
| 시점 | 실시간 요청 → 응답 | 정해진 시간의 파이프라인 (매시간, 매일) 또는 스트림 |
| 주요 도구 | Java, Spring Boot, JPA | **SQL**, Spark, Kafka, Airflow, Python (pandas, PySpark) |

**지금까지 배운 것과 겹치는 부분**
- **SQL** (GROUP BY, JOIN, 서브쿼리): 데이터 엔지니어링 1순위 기술 → `02_Database`에서 배운 것이 그대로 쓰임
- **18:10 daily-wrapup = 작은 파이프라인**: 정해진 시간 (launchd) → 추출 (오늘 코드) → 변환 (요약) → 적재 (Notion, GitHub)
- **Docker / 클라우드 / CI/CD**: 파이프라인도 클라우드에서 컨테이너로, 정해진 시간에 실행
- 실무에서는 Java만이 아니라 **SQL + Python**도 많이 씀 → Java + SQL + Python 조합

**AI 엔지니어링과의 연결: RAG 파이프라인 = 데이터 파이프라인**
- 문서 수집 → 청크로 나누기 → 임베딩 → 벡터 DB 저장 → 최신 상태 유지 (Spring Batch + Spring AI `VectorStore`로도 만들 수 있음)
- 데이터 품질이 AI 답변 품질을 결정함

### 3. 한 장으로 정리

```mermaid
flowchart LR
    A["백엔드<br/>데이터를 만들고 제공<br/>Java · Spring Boot · MySQL"] -->|"데이터"| B["데이터 엔지니어링<br/>데이터를 옮기고 다듬음<br/>SQL · Spring Batch · Kafka · Spark"]
    B -->|"정리된 데이터"| C["AI 엔지니어링<br/>데이터와 모델을 사용<br/>Spring AI · RAG · LangChain · 에이전트"]
    C -.->|"에이전트가 API 호출"| A
```

| 계층 | Ant Lab (주식 모의투자 프로젝트)에서 |
| --- | --- |
| 백엔드 | Spring Boot 모의투자 API (주문, 잔고, 종목 조회) |
| 데이터 엔지니어링 | 매일 주가 수집 (Spring Batch) → 지표, 상관관계 계산 (매크로 분석) |
| AI 엔지니어링 | 에이전트가 API와 분석 결과를 사용해 경제 상황에 따른 비중 조절 제안 |


![1793343202182](image/261030_SpringBoot/1793343202182.png)

