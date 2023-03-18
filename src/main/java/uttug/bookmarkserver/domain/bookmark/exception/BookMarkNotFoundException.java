package uttug.bookmarkserver.domain.bookmark.exception;


import uttug.bookmarkserver.global.error.exception.BookMarkException;
import uttug.bookmarkserver.global.error.exception.ErrorCode;

public class BookMarkNotFoundException extends BookMarkException {

    public static final BookMarkException EXCEPTION = new BookMarkNotFoundException();

    private BookMarkNotFoundException() {
        super(ErrorCode.BOOK_NOT_FOUND);
    }
}