package lecture.section01.list.run;

import java.util.Stack;

public class Application4 {
    
    /*
    * Stack
    * - 후입 선출(LIFO)
    * 
    * */
    public static void main(String[] args) {
        Stack<Integer> intStack = new Stack<>();

        intStack.push(10);
        intStack.push(11);
        intStack.push(12);
        intStack.push(13);
        intStack.push(14);

        System.out.println("intStack = " + intStack);

        //pop :  해당 스택의 가장 마지막 요소를 반환 후 제거
        //peek : 해당 스택의 가장 마지막 요소를 반환
        System.out.println("intStack.peek() = " + intStack.peek());
        System.out.println("intStack = " + intStack);
        System.out.println("intStack.pop() = " + intStack.pop());
        System.out.println("intStack.pop() = " + intStack.pop());
        System.out.println("intStack.pop() = " + intStack.pop());
        System.out.println("intStack.pop() = " + intStack.pop());
        System.out.println("intStack.pop() = " + intStack.pop());
//        System.out.println("intStack.pop() = " + intStack.pop());// 더이상 빼낼 수 있는 게 없음

    }
    
    
}
