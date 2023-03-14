package uttug.bookmarkserver.global.security.auth;


import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import uttug.bookmarkserver.domain.user.entity.User;
import uttug.bookmarkserver.domain.user.repository.UserRepository;
import uttug.bookmarkserver.global.exception.UserNotFoundException;

@Service
@RequiredArgsConstructor
public class AuthUserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public AuthUser loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);

        return new AuthUser(
                user.getId(),
                user.getEmail(),
                user.getRoles()
        );
    }
}