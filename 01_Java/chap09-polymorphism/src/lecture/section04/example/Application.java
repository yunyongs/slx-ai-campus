package lecture.section04.example;

public class Application {

    /*
     * FireCar와 RacingCar는 앞으로 갈 수 있다. (go())
     * -- sout으로 출력("소방차가 앞으로 갑니다...")
     * FireCar와 RacingCar는 멈출 수 있다. (go())
     *
     * FireCar만 경적을 울릴 수 있다. (horn() )
     *
     * 상속과 구현을 이용해서 완성해 보세요.
     * */

    public static void main(String[] args) {
        FireCar fire = new FireCar();
        RacingCar racing = new RacingCar();
        fire.go();
        fire.stop();
        fire.horn();

        racing.go();
        racing.stop();
    }
}
