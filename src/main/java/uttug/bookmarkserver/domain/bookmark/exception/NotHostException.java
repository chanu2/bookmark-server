package uttug.bookmarkserver.domain.bookmark.exception;

import uttug.bookmarkserver.global.error.exception.BookMarkException;
import uttug.bookmarkserver.global.error.exception.ErrorCode;

public class NotHostException extends BookMarkException {


    public static final BookMarkException EXCEPTION = new NotHostException();

    private NotHostException() {
        super(ErrorCode.BOOK_MARK_NOT_HOST);
    }
}
