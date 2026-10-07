package lecture.section01.list.run;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Application1 {
    public static void main(String[] args) {

        /*
        * ArrayList
        * - 배열의 단점을 보완
        * - 크기변경, 요소의 추가삭제, 정렬기능을 구현해 놓았다.
        * */

        ArrayList arrayList = new ArrayList();
        List arrList = new ArrayList();

        arrList.add("apple");
        arrList.add(123); //Wrapping Class 사용한 것임 Integer, Double로
        arrList.add(45.53);
        arrList.add(LocalDateTime.now());

        System.out.println("arrList = " + arrList); //toStrijng이 오버라이딩 되어있다.

        System.out.println("arrList.size() = " + arrList.size());
        System.out.println("arrList.get(0) = " + arrList.get(0)); //인덱스를 매소드로 호출

        arrList.add(1, "banana");
        System.out.println("arrList = " + arrList);
        arrList.remove(1);

        //정렬


    }
}
