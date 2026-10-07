package lecture.section03.wrapper;

public class Application1 {
    /*
    * Wrapping Class
    *
    * */
    public static void main(String[] args) {
        int primitive = 20;
        Integer wrapper = primitive; //auto boxing
        int result = wrapper; //auto unboxing



    /*
    * 사용례
    * 문자열을 기본 타입으로 변경할 때
    * parse() : 해당 타입을 (문자열을) 인자로 받아서 원하는 타입으로 변환
    *
    * */
        int age = Integer.parseInt("20");
        Double.parseDouble("167.6");
        boolean active = Boolean.parseBoolean("true");
        //질문 parse 에서 더블형 숫자가 문자형태로 저장되어 있을 때 인티져로 변경 가능?



    }
}
