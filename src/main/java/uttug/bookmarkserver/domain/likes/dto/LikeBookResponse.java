package uttug.bookmarkserver.domain.likes.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
public class LikeBookResponse {
    private boolean likeStatus;
    public LikeBookResponse(boolean likeStatus) {
        this.likeStatus = likeStatus;
    }
}
