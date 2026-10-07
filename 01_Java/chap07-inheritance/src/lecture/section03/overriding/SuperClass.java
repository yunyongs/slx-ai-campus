package lecture.section03.overriding;

public class SuperClass {
    /*
     * 오버라이딩 성립요건
     * 1. 메소드의 이름이 동일해야 한다.
     * 2. 메소드의 리턴 타입이 동일해야 한다.
     * 3. 매개변수의 타입, 갯수, 순서가 동일해야 한다.
     * 4. 접근이 가능해야 오버라이딩이 가능하다. (접근제어자가 부모 메서드보다 같거나 더 넓어야함)
     * 5. final 키워드가 사용된 메서드는 오버라이딩이 불가하다.
     * */

    public void method(int num) {};
    private void privateMethod () {};
    public final void finalMethod () {};
    protected void protectedMethod () {};


}
