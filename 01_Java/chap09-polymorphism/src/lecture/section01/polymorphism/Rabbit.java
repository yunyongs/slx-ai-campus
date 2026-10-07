package lecture.section01.polymorphism;

public class Rabbit extends Animal{
    @Override
    public void eat() {
        System.out.println("Rabbit eat grass");
    }

    @Override
    public void run() {
        System.out.println("Rabbit run");
    }

    @Override
    public void cry() {
        System.out.println("Rabbit cry");
    }

    public void  jump() {
        System.out.println("Rabbit jumps");
    }
}
