package lecture.section02.grammar;

public enum UserRole2 {

    //예외를 상수로 정의하자.



    // 상수 정의
    GUEST("게스트"),
    CONSUMER("구매자"),
    PRODUCER("판매자"),
    ADMIN("관리자");

    //필드
    // 이때 상수이기때문에 final로 정의
    private final String description;

    //생성자 - 필드 때문에
    UserRole2(String description) {
        this.description = description;
    }

    //getter
    public String getDescription() {
        return description;
    }
}
