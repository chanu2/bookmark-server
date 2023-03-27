package uttug.bookmarkserver.domain.bookmark.exception;

import uttug.bookmarkserver.global.error.exception.BookMarkException;
import uttug.bookmarkserver.global.error.exception.ErrorCode;

public class OutOfPageException extends BookMarkException {


    public static final BookMarkException EXCEPTION = new OutOfPageException();

    private OutOfPageException() {
        super(ErrorCode.OUT_OF_PAGE);
    }
}
