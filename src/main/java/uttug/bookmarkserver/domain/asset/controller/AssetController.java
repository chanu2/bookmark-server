package uttug.bookmarkserver.domain.asset.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import uttug.bookmarkserver.domain.asset.dto.AllMoodImageDto;
import uttug.bookmarkserver.domain.asset.dto.ProfileImageDto;
import uttug.bookmarkserver.domain.asset.service.AssetService;

import java.util.List;


@RequiredArgsConstructor
@RequestMapping("/api/v1/asset")
@RestController
public class AssetController {

    private final AssetService assetService;

    @GetMapping("/profiles/random")
    public ProfileImageDto getRandomProfileImageUrl() {
        return assetService.randomProfileImageUrl();
    }

    @GetMapping("/mood/image")
    public List<AllMoodImageDto> getRandomFoodImage(){
        return assetService.allMoodImage();
    }

}