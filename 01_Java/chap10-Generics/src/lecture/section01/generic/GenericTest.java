package lecture.section01.generic;

//
// 연산자 내부에 작성하는 영문자는 대문자 (관례)

public class GenericTest <T>{
    private T value;

    public GenericTest(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
