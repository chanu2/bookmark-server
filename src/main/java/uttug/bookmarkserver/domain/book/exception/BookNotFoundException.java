package uttug.bookmarkserver.domain.book.exception;


import uttug.bookmarkserver.global.error.exception.BookMarkException;
import uttug.bookmarkserver.global.error.exception.ErrorCode;

public class BookNotFoundException extends BookMarkException {

    public static final BookMarkException EXCEPTION = new BookNotFoundException();

    private BookNotFoundException() {
        super(ErrorCode.BOOK_NOT_FOUND);
    }
}