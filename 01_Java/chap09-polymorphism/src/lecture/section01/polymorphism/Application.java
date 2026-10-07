package lecture.section01.polymorphism;

public class Application {

    public static void main(String[] args) {
        Animal animal = new Animal();
        animal.cry();
        Tiger tiger = new Tiger();
        tiger.cry();
        tiger.bite();

        Rabbit rabbit = new Rabbit();
        rabbit.cry();

        System.out.println("========================");
        //동적 바인딩: 컴파일 당시에는 해당 타입의 메소드를 가리키다가,
        // 런타임 당시 실제 객체가 가진 오버라이딩된 메소드로 바인딩이 바뀌어 동작하는 것
        Animal a1 = new Tiger(); //다형성, 자기 자신 타입 및 부모의 타입 가능
        a1.cry();
       // a1.bite(); //컴파일이 안 됨 -> animal을 가리키고 있으니까

        Animal a2 = new Rabbit(); //
        a2.cry();
//        Object obj1 = new Tiger();

        //부모의 타입이 자식 타입으로 저장될 순 없음
//        Tiger t1 = new Animal();

        System.out.println("==================형변환==============");
        ((Tiger) a1).bite();
        ((Rabbit) a2).jump();

//        타입형변환을 잘못하는 경우 컴파일시에는 문제가 되지 않는데, 런타임시 Exception오류가 발생한다.
//        ((Rabbit) a1).jump();
        System.out.println("a1이 Tiger타입인지 확인: "+(a1 instanceof Tiger));

        if (a1 instanceof Tiger){
            ((Tiger) a1).bite();
        }

    }

}
