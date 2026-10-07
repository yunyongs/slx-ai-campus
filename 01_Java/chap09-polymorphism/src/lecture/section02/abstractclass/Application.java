package lecture.section02.abstractclass;

public class Application {

    /*
    * 추상클래스
    * - 상속받은 클래스로만 클래스를 만들고 싶을 때
    * - 추상메소드를 0개 이상 포함하는 클래스
    * - 추상 클래스를 상속받은 클래스를 만들고, 추상메서드를 구현(완성-오버라이딩)해야지만
    *   사용이 가능하다.
    *
    * 추상메서드
    * - 메서드의 선언부만 있고 구현부가 없는 메소드 {} //중괄호 영역이 없음
    *
    * */

    public static void main(String[] args) {

        /*
        Product product = new Product() {        }
        //추상클래스 그 자체만으로는 인스턴스 생성이 불가함
        */
        Product smartPhone = new Smartphone();

    }
}
