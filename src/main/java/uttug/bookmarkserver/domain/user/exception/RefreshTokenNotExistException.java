package uttug.bookmarkserver.domain.user.exception;


import uttug.bookmarkserver.global.error.exception.BookMarkException;
import uttug.bookmarkserver.global.error.exception.ErrorCode;

public class RefreshTokenNotExistException extends BookMarkException {

    public static final BookMarkException EXCEPTION = new RefreshTokenNotExistException();
    public RefreshTokenNotExistException() {
        super(ErrorCode.REFRESH_TOKEN_NOT_EXIST);
    }
}
