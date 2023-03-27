package uttug.bookmarkserver.domain.book.dto.response;


import lombok.Getter;
import uttug.bookmarkserver.domain.book.entity.Book;

@Getter
public class BookResponse {

    private Long id;
    private String bookName;

    public BookResponse(Book book) {
        this.id = book.getId();
        this.bookName = book.getBookName();

    }
}
