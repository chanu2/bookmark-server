package uttug.bookmarkserver.domain.asset.dto;


import lombok.Getter;
import uttug.bookmarkserver.domain.asset.entity.ProfileImage;

@Getter
public class ProfileImageDto {

    private String url;

    public ProfileImageDto(ProfileImage profileImage) {
        this.url = profileImage.getImageUrl();

    }


}