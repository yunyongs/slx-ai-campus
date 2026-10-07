package lecture.section01.object.run;

import lecture.section01.object.book.Book;

public class Application2 {

    public static void main(String[] args) {

        /*
        * equals
        * - 객체가 정의한 기준으로 값이 같은지 비교한다. (동등성)
        *
        * ==
        * - 두 변수가 같은 객체를 참조하는지 비교한다 (동일성)
        * */
        Book book1 = new Book(1, "홍길동전", "허균", 50000);
        Book book2 = new Book(1, "홍길동전", "허균", 50000);

        Book book3 = book1;
        System.out.println("book1 == book2:" + (book1==book2));
        System.out.println("book1 == book3:" + (book1==book3));


    }
}
