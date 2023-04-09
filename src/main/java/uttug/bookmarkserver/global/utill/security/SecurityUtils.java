package uttug.bookmarkserver.global.utill.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import uttug.bookmarkserver.global.exception.UserNotFoundException;

public class SecurityUtils {

    public static String getCurrentUserEmail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            throw UserNotFoundException.EXCEPTION;
        }
        return authentication.getName();
    }
}
