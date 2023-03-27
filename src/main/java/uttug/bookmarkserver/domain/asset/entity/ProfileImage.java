package uttug.bookmarkserver.domain.asset.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProfileImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "profile_image_id")
    private Long id;
    private String imageUrl;

    public ProfileImage( String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
