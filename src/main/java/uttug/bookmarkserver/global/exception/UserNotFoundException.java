package uttug.bookmarkserver.global.exception;


import uttug.bookmarkserver.global.error.exception.BookMarkException;
import uttug.bookmarkserver.global.error.exception.ErrorCode;

public class UserNotFoundException extends BookMarkException {

    public static final BookMarkException EXCEPTION = new UserNotFoundException();

    private UserNotFoundException() {
        super(ErrorCode.USER_NOT_FOUND);
    }
}