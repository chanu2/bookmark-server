package uttug.bookmarkserver.global.utill.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uttug.bookmarkserver.domain.user.entity.User;
import uttug.bookmarkserver.domain.user.repository.UserRepository;
import uttug.bookmarkserver.global.exception.UserNotFoundException;
import uttug.bookmarkserver.global.utill.security.SecurityUtils;

@RequiredArgsConstructor
@Service
public class UserUtilsImpl implements UserUtils {
    private final UserRepository userRepository;
    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> UserNotFoundException.EXCEPTION);
    }
    public User getUserFromSecurityContext() {
        String currentUserEmail = SecurityUtils.getCurrentUserEmail();
        User user = getUserByEmail(currentUserEmail);
        return user;
    }

}