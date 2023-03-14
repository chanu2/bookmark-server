package uttug.bookmarkserver.domain.user.service;



import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uttug.bookmarkserver.domain.user.dto.request.LoginDto;
import uttug.bookmarkserver.domain.user.entity.RefreshToken;
import uttug.bookmarkserver.domain.user.entity.User;
import uttug.bookmarkserver.domain.user.repository.RefreshTokenRepository;
import uttug.bookmarkserver.domain.user.repository.UserRepository;
import uttug.bookmarkserver.global.exception.UserExistedException;
import uttug.bookmarkserver.global.exception.UserNotFoundException;
import uttug.bookmarkserver.global.security.JwtUtil;

import javax.servlet.http.HttpServletResponse;
import java.util.Collections;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class UserService {

    private final AssetUtils assetUtils;

    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;




    public void signIn (String email, HttpServletResponse response) {

        User user = findMemberEmail(email);
        createToken(email, response);
        log.info(user.getEmail() + " (id : " + user.getId() + ") login");
    }

    @Transactional
    public void signUp (String email, LoginDto loginDto, HttpServletResponse response) {

        if(!userRepository.findByEmail(email).isEmpty()) {
           throw UserExistedException.EXCEPTION;
        }

        User user = userRepository.save(
                User.builder()
                        .email(email)
                        .nickname(loginDto.getNickname())
                        .gender(loginDto.getGender())
                        .roles(Collections.singletonList("ROLE_USER"))
                        .build());


        // 어세스, 리프레시 토큰 발급 및 헤더 설정
        log.info("email={}",email);
        createToken(email, response);
        log.info(user.getEmail() + " (id : " + user.getId() + ") login");

    }

    @Transactional
    public boolean signUp2 (String email,LoginDto loginDto, HttpServletResponse response) {

        if(!userRepository.findByEmail(email).isEmpty()) {
            throw UserExistedException.EXCEPTION;
        }

        User user = userRepository.save(
                User.builder()
                        .email(email)
                        .nickname(loginDto.getNickname())
                        .gender(loginDto.getGender())
                        .roles(Collections.singletonList("ROLE_USER"))
                        .build());

        // 어세스, 리프레시 토큰 발급 및 헤더 설정
        log.info("email={}",email);
        createToken(email, response);
        log.info(user.getEmail() + " (id : " + user.getId() + ") login");

        return true;

    }

    public void signIn2 (String email, HttpServletResponse response) {

        User user = findMemberEmail(email);
        createToken(email, response);
        log.info(user.getEmail() + " (id : " + user.getId() + ") login");
    }


    public void createToken(String email, HttpServletResponse response) {

        log.info("email={}",email);

        String accessToken = jwtUtil.createAccessToken(email, Collections.singletonList("ROLE_USER"));
        String refreshToken = jwtUtil.createRefreshToken(email, Collections.singletonList("ROLE_USER"));

        jwtUtil.setHeaderAccessToken(response, accessToken);
        jwtUtil.setHeaderRefreshToken(response, refreshToken);

        log.info("email={}",email);
        log.info("---------------------------------------------------------------------");

        refreshTokenRepository.save(new RefreshToken(refreshToken));

        log.info("---------------------------------------------------------------------");
    }

    public User findMemberEmail(String email) {
        return userRepository
                .findByEmail(email)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);
    }



    // TODO: 2023-03-04 캐릭터로 변경
//    public List<User> getTotalRank(){
//        PageRequest pageRequest = PageRequest.of(0,10, Sort.by(Sort.Direction.DESC,"accumulatedCalorie"));
//
//        return userRepository.findBy(pageRequest).getContent();
//    }


}
