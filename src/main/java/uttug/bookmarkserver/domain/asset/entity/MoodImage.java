package uttug.bookmarkserver.domain.asset.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MoodImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mood_image_id")
    private Long id;
    private String moodName;

    private String moodImageUrl;

    public MoodImage(String moodName, String moodImageUrl) {
        this.moodName = moodName;
        this.moodImageUrl = moodImageUrl;
    }
}
