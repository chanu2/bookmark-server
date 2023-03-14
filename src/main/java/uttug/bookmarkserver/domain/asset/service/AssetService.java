package uttug.bookmarkserver.domain.asset.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uttug.bookmarkserver.domain.asset.dto.*;
import uttug.bookmarkserver.domain.asset.entity.MoodImage;
import uttug.bookmarkserver.domain.asset.entity.ProfileImage;
import uttug.bookmarkserver.domain.asset.exception.MoodImageNotFoundException;
import uttug.bookmarkserver.domain.asset.exception.ProfileImageNotFoundException;
import uttug.bookmarkserver.domain.asset.repository.MoodImageRepository;
import uttug.bookmarkserver.domain.asset.repository.ProfileImageRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AssetService implements AssetUtils {
    private final MoodImageRepository moodImageRepository;
    private final ProfileImageRepository profileImageRepository;


    // 감성 이모티콘 제공
    public List<AllMoodImageDto> allMoodImage(){

        List<MoodImage> moodImageList =
                moodImageRepository.findAll();
        return moodImageList.stream().map(m -> new AllMoodImageDto(m)).collect(Collectors.toList());
    }

    @Override
    public MoodImageDto moodImageByName(String moodName) {
        MoodImage moodImage =
                moodImageRepository
                        .findByMoodName(moodName)
                        .orElseThrow(() -> MoodImageNotFoundException.EXCEPTION);

        return new MoodImageDto(moodImage);
    }


    public ProfileImageDto randomProfileImageUrl() {
        ProfileImage profileImage =
                profileImageRepository
                        .findRandomProfileImage()
                        .orElseThrow(() -> ProfileImageNotFoundException.EXCEPTION);
        return new ProfileImageDto(profileImage);
    }


}
