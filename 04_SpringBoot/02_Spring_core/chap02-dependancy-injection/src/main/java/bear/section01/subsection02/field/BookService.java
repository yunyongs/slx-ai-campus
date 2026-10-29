package bear.section01.subsection02.field;

import bear.section01.common.BookDAO;
import bear.section01.common.BookDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("bookServiceSetter")
public class BookService {

    /*
    * final 키워드 사용 못함
    * */
    private BookDAO bookDAO;

    @Autowired
    public void setBookDAO(BookDAO bookDAO) {
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
