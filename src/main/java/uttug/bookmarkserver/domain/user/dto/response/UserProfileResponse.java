package uttug.bookmarkserver.domain.user.dto.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import uttug.bookmarkserver.domain.user.entity.User;

@Getter
@AllArgsConstructor
public class UserProfileResponse {

    private Long id;
    private String nickname;
    private String profilePath;
    private String email;

    public UserProfileResponse(User user) {
        this.id = user.getId();
        this.nickname = user.getNickname();
        this.profilePath = user.getProfilePath();
        this.email = user.getEmail();
    }
}
