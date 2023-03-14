package uttug.bookmarkserver.domain.user.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import uttug.bookmarkserver.global.database.BaseEntity;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RefreshToken extends BaseEntity {
    @Id
    @Column(nullable = false, length = 191)
    private String refreshToken;

    @Builder
    public RefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
