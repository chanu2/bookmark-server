package uttug.bookmarkserver.domain.user.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
@Getter
@NoArgsConstructor
public class ChangeUserRequest {
    private String nickname;
    private String filePath;
}