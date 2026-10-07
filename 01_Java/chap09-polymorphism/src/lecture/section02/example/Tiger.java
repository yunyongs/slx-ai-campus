package lecture.section02.example;

import lecture.section01.polymorphism.Animal;

public class Tiger extends Animal {
    @Override
    public void eat() {
        System.out.println("Tiger eat grass");
    }

    @Override
    public void run() {
        System.out.println("Tiger run");
    }

    @Override
    public void cry() {
        System.out.println("Tiger cry");
    }

    public void bite() {
        System.out.println("Tiger bite something");
    }
}
