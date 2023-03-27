package uttug.bookmarkserver.domain.bookmark.service.dto;

import lombok.Builder;
import lombok.Getter;
import uttug.bookmarkserver.domain.common.Color;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Builder
public class UpdateBookMarkDto {

    private String bookMarkName;
    private String moodImageUrl;
    private String summary;
    private Integer checkPageNum;
    @Enumerated(EnumType.STRING)
    private Color color;
}
