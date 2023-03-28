package uttug.bookmarkserver.domain.user.dto.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import uttug.bookmarkserver.domain.user.entity.User;

@Getter
@AllArgsConstructor
public class ConnectUserResponse {
    private boolean logInStatus;

    public ConnectUserResponse() {
        this.logInStatus = true;
    }
}
