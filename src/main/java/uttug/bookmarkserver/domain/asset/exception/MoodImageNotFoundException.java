package uttug.bookmarkserver.domain.asset.exception;


import uttug.bookmarkserver.global.error.exception.BookMarkException;
import uttug.bookmarkserver.global.error.exception.ErrorCode;

public class MoodImageNotFoundException extends BookMarkException {

    public static final BookMarkException EXCEPTION = new MoodImageNotFoundException();

    private MoodImageNotFoundException() {
        super(ErrorCode.MOOD_IMAGE_NOT_FOUND);
    }
}