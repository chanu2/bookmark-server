package uttug.bookmarkserver.domain.book.exception;

import uttug.bookmarkserver.global.error.exception.BookMarkException;
import uttug.bookmarkserver.global.error.exception.ErrorCode;
import uttug.bookmarkserver.global.exception.UserExistedException;

public class NotHostException extends BookMarkException {


    public static final BookMarkException EXCEPTION = new NotHostException();

    private NotHostException() {
        super(ErrorCode.BOOK_NOT_HOST);
    }
}
