package uttug.bookmarkserver.global;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import uttug.bookmarkserver.global.utill.security.SecurityUtils;

@RestController
@Slf4j
@ResponseBody
public class TestController1 {

    @GetMapping("/test")
    public ResponseEntity getHello(@RequestParam String hello){

        String email = SecurityUtils.getCurrentUserEmail();

        return ResponseEntity.ok().body(email);
    }
}
