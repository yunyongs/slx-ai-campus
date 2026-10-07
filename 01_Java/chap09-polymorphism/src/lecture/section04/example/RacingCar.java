package lecture.section04.example;

public class RacingCar extends Car{

    @Override
    public void go() {
        System.out.println("레이싱카가 앞으로 갑니다...");
    }

    @Override
    public void stop() {
        System.out.println("레이싱카가 멈춥니다.");
    }
}
