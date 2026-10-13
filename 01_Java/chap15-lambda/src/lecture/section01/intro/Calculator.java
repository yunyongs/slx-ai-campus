package lecture.section01.intro;

//하나의 추상메서느가 있는 것을 함수적 인터페이스
@FunctionalInterface //컴파일 시점에 람다식으로 쓸 수 있는 인터페이스인지 확인해 주는 기능
public interface Calculator {

    // 추상 메서드
    // 람다식을 사용하기 위해서는 인터페이스 내부에 하나의 추상메소드만 있어야함.
    public int sumTwoNumber (int a, int b);

    //public void printNum (int a);
    /*
    * 반환형, 매개변수를 제네릭으로 변환 >> 매개변수의 갯수, 반환을 할지 안할지만 남는다.
    * */
}
