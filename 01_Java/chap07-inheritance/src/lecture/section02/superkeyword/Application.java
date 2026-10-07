package lecture.section02.superkeyword;

public class Application {

    public static void main(String[] args) {

        /*
        * super : 현재 객체의 부모 부분을 가리킨다
        * super() : 부모 클래스의 생성자 호출
        *
        * 자식 객체를 생성하면 부모 클래스 부분이 먼저 초기화되고, 자식 클래스가 초기화된다.
        * */

        System.out.println("===1. 생략된 super()===");

        new Computer(); //컴퓨터의 기본 생성자 호출

        System.out.println("===2. 매개 변수가 있는 super()===");

        Computer computer = new Computer(
                "S-01234", "삼성", "갤럭시폴드6", 2398000, new java.util.Date(),
                "퀄컴 스냅드래곤", 512, 12, "안드로이드"
        );

        System.out.println("===3. 부모 메서드 호출===");
        System.out.println(computer.toString());

    }
}
