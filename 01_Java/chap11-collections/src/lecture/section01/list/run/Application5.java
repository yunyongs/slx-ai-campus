package lecture.section01.list.run;

import java.util.LinkedList;
import java.util.Queue;

public class Application5 {
    public static void main(String[] args) {
        Queue<String> que = new LinkedList<>();
        que.offer("first");
        que.offer("second");
        que.offer("third");
        que.offer("fourth");
        que.offer("fifth");
        System.out.println("que = " + que);
        
        /*
        * peek() : 큐의 가장 앞에 있는 요소를 반환한다. 
        * poll() : 큐의 가장 앞에 있는 요소를 반환하고 제거한다. 
        * */

        System.out.println("que.peek() = " + que.peek());
        System.out.println("que.peek() = " + que.peek());
        System.out.println("que.poll() = " + que.poll());
        System.out.println("que.poll() = " + que.poll());

    }
}
