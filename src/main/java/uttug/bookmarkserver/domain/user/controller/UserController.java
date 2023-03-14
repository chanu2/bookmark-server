package uttug.bookmarkserver.domain.user.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uttug.bookmarkserver.domain.user.dto.request.LoginDto;
import uttug.bookmarkserver.domain.user.service.UserService;
import uttug.bookmarkserver.global.googleVerifier.TokenVerifier;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.GeneralSecurityException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@Slf4j
public class UserController {
    private final UserService userService;
    private final TokenVerifier tokenVerifier;

    // 회원가입 요청

    @PostMapping("/signIn")
    public void signIn(@RequestParam String idTokenString,HttpServletResponse response) throws GeneralSecurityException, IOException {

        String email = tokenVerifier.tokenVerify(idTokenString);
        userService.signIn(email, response);
    }

    // 로그인
    @PostMapping("/signUp")
    public void signUp(@RequestParam String idTokenString, @RequestBody LoginDto loginDto, HttpServletResponse response) throws GeneralSecurityException, IOException {

        String email = tokenVerifier.tokenVerify(idTokenString);
        log.info("email={}",email);

        userService.signUp(email, loginDto, response);

    }

    @PostMapping("/signUp2")
    public void signUp2(@RequestParam String email, @RequestBody LoginDto loginDto, HttpServletResponse response) {
        userService.signUp2(email, loginDto, response);
    }

    @PostMapping("/signIn2")
    public void signIn2(@RequestParam String email,HttpServletResponse response) {
        userService.signIn2(email, response);
    }


}
