package uttug.bookmarkserver.domain.asset.exception;


import uttug.bookmarkserver.global.error.exception.BookMarkException;
import uttug.bookmarkserver.global.error.exception.ErrorCode;

public class ProfileImageNotFoundException extends BookMarkException {

    public static final BookMarkException EXCEPTION = new ProfileImageNotFoundException();

    private ProfileImageNotFoundException() {
        super(ErrorCode.PROFILE_IMAGE_NOT_FOUND);
    }
}