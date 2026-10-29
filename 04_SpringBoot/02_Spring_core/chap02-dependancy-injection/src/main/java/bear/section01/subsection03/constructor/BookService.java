package bear.section01.subsection03.constructor;

import bear.section01.common.BookDAO;
import bear.section01.common.BookDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*
* 생성자 주입을 권장하는 이유
* 1. 필수 의존성을 빠뜨린 채 객체를 생성할 수 없다.
* 2. final필드를 사용해 의존관계를 불변으로 유지할 수 있다.
* */

@Service("bookServiceConstructor")
public class BookService {

    // BookDAO 객체가 다른 객체 또는 새로 만들어진
    private final BookDAO  bookDAO;

    // 생성자 주입
    // BookService 빈을 생성할 때, BookDAO타입의 빈을 생성자 인자로 전달한다.
    // constructor의 장점은 객체를 만들 때 바로 (객체를) 할당 >> 불변객체(Final선언 가능)
    // 아키텍처를 잡을 때 Final을 같이 선언하여 Service, Controller,
//    @Autowired //SpringBoot 4.3 부터 생성자가 한개이면 autowired를 생략해도 자동으로 의존성을 주입한다.
    public BookService(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    // 도서목록 조회
    public List<BookDTO> selectAllBooks() {

        return bookDAO.selectBookList();
    }

    // sequence로 도서 조회
    public BookDTO searchBookBySequence(int sequence) {

        return bookDAO.selectOneBook(sequence);
    }

}
