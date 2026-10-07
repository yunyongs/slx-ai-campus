package lecture.section03.interfaceimplements;

public interface InterProduct {
    /*
    * 인터페이스
    * - 상수필드와 추상메서드를 가진다. (단, static 메서드와 default 메서드는 구현부를 가질 수 있다)
    * */

    //상수: 대문자와 _로 단어를 구분하게끔 표기한다.
    // 선언과 동시에 초기화해야 함.
    public static final int MAX_NUM = 100;


    // interface는 생성자를 가질 수 없음.

//    // 일반 메서드를 가질 수 없음
//    public void  basicMethod();{}


    public abstract void nonStaticMethod ();

    void abstMethod(); // 보통은 퍼블릭, 스태틱을 생략하고 이런 형태로 작성 >> 왜냐하면 인터페이스는 public abstract의 의미를 가지고 있기 때문에


    public static void  staticMethod () {
        System.out.println("Interface는 static 메소드를 가질 수 있다.");
    }

    public default void defaultMethod () {
        System.out.println("Interface는 default 메소드를 가질 수 있다.");
    }

}
