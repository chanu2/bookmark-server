package uttug.bookmarkserver.domain.user.dto.request;


import lombok.Getter;
import uttug.bookmarkserver.domain.common.Gender;

@Getter
public class LoginDto {

    private String nickname;

    private Gender gender;


}
