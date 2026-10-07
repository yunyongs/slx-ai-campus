package lecture.section01.extend;

public class RacingCar extends Car{

    /*
    * 레이싱카는 멈출 수가 없다
    * run() > "레이싱카가 질주합니다!!" 출력 바꾸기
    * soundHorn() >> "레이싱카는 경적을 울리지 않습니다"로 출력하기
    * stop() >> 상태 안 바꾸기
    * */

    public RacingCar() {
        super();
        System.out.println("레이싱카가 생성되었습니다.");
    }

    @Override
    public void run(){
        runningStatus = true;
        System.out.println("레이싱카가 질주합니다!!");
    }

    @Override
    public void soundHorn() {
        if(isRunning()) {
            System.out.println("레이싱카는 경적을 울리지 않습니다.");

        } else {
            System.out.println("레이싱카가 주행중이 아닙니다.");
        }
    }

    @Override
    public void stop() {
        System.out.println("레이싱카가 멈춥니다.");
    }


}
