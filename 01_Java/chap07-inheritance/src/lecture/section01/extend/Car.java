package lecture.section01.extend;

public class Car {

    // 필드 영역(변수)
    protected boolean runningStatus;

    // 메서드 영역
    //출력문으로 경적 울리기
    public void soundHorn() {
        if(isRunning()) {
            System.out.println("빵!빵!");

        } else {
            System.out.println("주행중이 아닐 경우에는 경적을 울릴 수 없습니다.");
        }
    }

    //현재 주행상태를 확인할 수 있는 메서드
    // private 으로 되어 있으면 자식에서 가져올 수 없으니 protected로 변경하여 자식에서 사용가능하게
    protected boolean isRunning() {
        return runningStatus;
    }

    //멈추는 기능
    public void stop() {
        runningStatus = false;
        System.out.println("자동차가 멈춥니다.");
    }
    // 달리는 기능
    public void run(){
        runningStatus = true;
        System.out.println("자동차가 달립니다.");
    }

    public Car() {
        System.out.println("기본 생성자");
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
