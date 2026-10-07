package lecture.section01.section02.extend;

// T extends Rabbit: T의 최대범위가 Rabbit이라는 뜻
public class WildCardFarm{

    // RabbitFarm의 제네릭 타입이 뭐든 매개변수로 받겠다.
    // Rabbit을 부모로 가진 클래스는 전부 매개변수로 올 수 있다.
    public void anyType(RabbitFarm<?> farm){
        farm.getAnimal().cry();
    }

    public void extendType(RabbitFarm<? extends Bunny> farm){
        farm.getAnimal().cry();
    }
    public void superType(RabbitFarm<? super Bunny> farm){
        farm.getAnimal().cry();
    }

}
