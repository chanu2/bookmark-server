package uttug.bookmarkserver.global.exception;


import uttug.bookmarkserver.global.error.exception.ErrorCode;
import uttug.bookmarkserver.global.error.exception.BookMarkException;

public class UserExistedException extends BookMarkException {

    public static final BookMarkException EXCEPTION = new UserExistedException();

    private UserExistedException() {
        super(ErrorCode.USER_EXISTED);
    }
}