package bear.section01.common;

import java.util.List;

public interface BookDAO {

    /* 설명. 저장된 전체 도서를 조회한다. */
    List<BookDTO> selectBookList();

    /* 설명. 도서 번호와 일치하는 도서 한 권을 조회한다. */
    BookDTO selectOneBook(int sequence);
}
