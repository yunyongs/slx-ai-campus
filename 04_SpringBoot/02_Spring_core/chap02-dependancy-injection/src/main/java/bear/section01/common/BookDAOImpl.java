package bear.section01.common;

import bear.section01.common.BookDAO;
import bear.section01.common.BookDTO;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository("bookDAO")
public class BookDAOImpl implements BookDAO {

    private Map<Integer, BookDTO> bookList;

    /* 설명. 데이터베이스 조회 결과가 있다고 가정하고 예제용 도서 데이터를 생성한다. */
    public BookDAOImpl() {
        bookList = new HashMap<>();
        bookList.put(1, new BookDTO(1, 123456, "자바의 정석", "남궁성", "도우출판", new Date()));
        bookList.put(2, new BookDTO(2, 654321, "칭찬은 고래도 춤추게 한다", "고래", "고래출판", new Date()));
    }

    @Override
    public List<BookDTO> selectBookList() {
        /* 설명. Map에 저장된 도서 값만 꺼내 새로운 List로 반환한다. */
        return new ArrayList<>(bookList.values());
    }

    @Override
    public BookDTO selectOneBook(int sequence) {
        return bookList.get(sequence);
    }
}
