package lecture.section01.section02.extend;

// T extends Rabbit: T의 최대범위가 Rabbit이라는 뜻
public class RabbitFarm <T extends Rabbit>{

    public T animal;

    public RabbitFarm() {
    }

    public RabbitFarm(T animal) {
        this.animal = animal;
    }

    public T getAnimal() {
        return animal;
    }

    public void setAnimal(T animal) {
        this.animal = animal;
    }
}
