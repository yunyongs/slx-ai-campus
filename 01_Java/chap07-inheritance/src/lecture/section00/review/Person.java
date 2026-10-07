package lecture.section00.review;

//클래스
// 클래스는 객체를 만들기 위한 설계도 (참조자료형)
// 필드, 생성자, 메서드
// 클래스의 이름은 파일명과 같아야 한다.

//접근제어자: 클래스나 클래스의 멤버(필드, 메소드)에 접근할 수 있는 범위를 설정
// -public: 모든 패키지에서 접근이 가능
// -protected: 같은 패키지 또는 상속 관계의 클래스에서 접근이 가능
// -default: 같은 패키지 안에서만 접근 가능
// private: 현재 클래스 안에서만 접근 가능


public class Person {

    //필드
    private String name;
    private int age;

    //생성자 - (필드와 메서드 사이 위치 권고)
    public Person(String name, int age) {

        //this: 현재 만들어진 객체(인스턴스) 자기 자신을 가리킨다.
        // super (상속시 개념)
        this.name = name;
        this.age = age;
    }

    //기본 생성자
    public Person() {
    }

    //메서드: 객체가 할 수 있는 행동을 코드로 작성한 것
    // void (반환형이 없는 경우 void 작성)
    public void introduce() {
        System.out.println(
                "안녕하세요. 저는 " + name + "이고 나이는 "+ age +"입니다."
        );
    }

    // Getter; 데이터를 읽는 용도
    public String getName() {
        return name;
    }

    // Setter: 데이터를 수정하는 용도
    public void setName(String name) {

        //전처리 가능
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
