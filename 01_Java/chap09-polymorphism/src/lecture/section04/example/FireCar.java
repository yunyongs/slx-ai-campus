package lecture.section04.example;

public class FireCar extends Car implements Soundable{

    @Override
    public void go() {
        System.out.println("소방차가 앞으로 갑니다...");
    }

    @Override
    public void stop() {
        System.out.println("소방차가 멈춥니다...");
    }

    @Override
    public void horn() {
        System.out.println("소방차가 경적을 울립니다. 빠아아아앙!");
    }
}
