package lecture.section00.review;

public class Application {

    // static: 정적, JVM에 클래스로드가 일어날 때 static영역에 같이 등록된다.
    // 즉 static 키워드가 있을 때는 해당 메소드가 속해있는 객체를 생성하지 않고도 호출할 수 있다.
    // 즉 static 메서드는 Application.newMethod 형태로 호출 가능
    // 그러나 일반 메서드는 Application newClass = new Application();
    // newClass.newMethod로 호출해야 함
    public static void main(String[] args) {

        Person person = new Person("yuny", 46);
        person.introduce();

        Person person2 = new Person();
        person2.introduce();

        //하나의 클래스 파일에서 같은 이름의 메소드, 생성자를 매개변수만 다르게
        // >> 이것을 '오버로딩'이라고 한다.

        //name은 private이기 때문에 바로 접근 불가. 메서드를 통해 접근 가능 - 캡슐화
        //System.out.println(person.name);
        System.out.println(person.getName());


    }
}
