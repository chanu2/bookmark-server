package uttug.bookmarkserver.domain.asset.dto;


import lombok.Getter;
import uttug.bookmarkserver.domain.asset.entity.MoodImage;

@Getter
public class AllMoodImageDto {
    private String moodName;
    private String moodImageUrl;

    public AllMoodImageDto(MoodImage moodImage) {
        this.moodName = moodImage.getMoodName();
        this.moodImageUrl = moodImage.getMoodImageUrl();
    }
}
