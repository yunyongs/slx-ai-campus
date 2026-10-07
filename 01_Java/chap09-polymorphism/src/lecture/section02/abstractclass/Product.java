package lecture.section02.abstractclass;

public abstract class Product {
    //추상 클래스
    //abstract로 선언해 주어야 함

    //추상클래스는 필드를 가질 수 있음
    private int nonStaticField;
    private static int staticField;

    public Product() {}

    public void nonStaticMethod() {
        System.out.println("Product의 nonStaticMethod 호출함...");
    }

    public static void StaticMethod() {
        System.out.println("Product의 StaticMethod 호출함...");
    }

    //추상메서드를 가질 수 있다.
    public abstract void abstMethod(); //{}부분이 없음. 구현부가 없음



}
