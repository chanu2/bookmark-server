package uttug.bookmarkserver.domain.bookmark.dto.request;

import lombok.Getter;
import uttug.bookmarkserver.domain.book.service.dto.UpdateBookDto;
import uttug.bookmarkserver.domain.bookmark.service.dto.UpdateBookMarkDto;
import uttug.bookmarkserver.domain.common.Color;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
public class UpdateBookMarkRequest {
    private String bookMarkName;
    private String moodImageUrl;
    private String summary;
    private Integer checkPageNum;
    @Enumerated(EnumType.STRING)
    private Color color;


    public UpdateBookMarkDto toUpdateBookMarkDto() {
        return UpdateBookMarkDto.builder()
                .bookMarkName(bookMarkName)
                .moodImageUrl(moodImageUrl)
                .summary(summary)
                .checkPageNum(checkPageNum)
                .color(color)
                .build();
    }
}
