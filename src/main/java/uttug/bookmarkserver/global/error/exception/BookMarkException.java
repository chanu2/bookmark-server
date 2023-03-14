package uttug.bookmarkserver.global.error.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class BookMarkException extends RuntimeException{
    ErrorCode errorCode;
}
