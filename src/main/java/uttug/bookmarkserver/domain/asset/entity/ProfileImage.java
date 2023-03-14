package uttug.bookmarkserver.domain.asset.entity;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
public class ProfileImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "profile_image_id")
    private Long id;
    private String imageUrl;

}
