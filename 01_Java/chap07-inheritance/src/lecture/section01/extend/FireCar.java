package lecture.section01.extend;

public class FireCar extends Car{

    //기본 생성자
    public FireCar() {

        /*
        * super()는 자식 생성자의 가장 상단에 있어야 한다.
        *
        * */
        super();
        System.out.println("FireCar의 기본생성자가 호출되었습니다");
    }

    //메소드
    public void sprayWater() {
        System.out.println("불난 곳을 발견했습니다. 물을 뿌립니다.==----->");
    }

    //부모의 메소드를 자식에서 다시 작성 >> 오버라이딩
    // private 으로 되어 있으면 자식에서 가져올 수 없으니 protected로 변경하여 자식에서 사용가능하게
    // @Override에는 부모 클래스의 메서드를 자식에서 재작성했다는 의미임
    @Override
    public void soundHorn() {
        if(isRunning()) {
            System.out.println("빠아아아아앙~~~!! 화재 출동"); //코드를 수정함(부모와 달리) :: 오버라이딩이라고 함
        } else {
            System.out.println("주행중이 아닐 경우에는 경적을 울릴 수 없습니다.");
        }
    }


}
