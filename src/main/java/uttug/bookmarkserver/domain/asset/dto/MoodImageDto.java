package uttug.bookmarkserver.domain.asset.dto;


import lombok.Getter;
import uttug.bookmarkserver.domain.asset.entity.MoodImage;

@Getter
public class MoodImageDto {
    private String moodName;
    private String moodImageUrl;

    public MoodImageDto(MoodImage moodImage) {
        this.moodName = moodImage.getMoodName();
        this.moodImageUrl = moodImage.getMoodImageUrl();
    }
}
