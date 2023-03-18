package uttug.bookmarkserver.domain.bookmark.dto.response;

import lombok.Getter;
import uttug.bookmarkserver.domain.common.Color;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
public class BookMarkInfoDto {

    private String bookMarkName;
    private String moodImageUrl;
    private String summary;
    private Integer checkPageNum;
    @Enumerated(EnumType.STRING)
    private Color color;


    public BookMarkInfoDto(String bookMarkName, String moodImageUrl, String summary, Integer checkPageNum, Color color) {
        this.bookMarkName = bookMarkName;
        this.moodImageUrl = moodImageUrl;
        this.summary = summary;
        this.checkPageNum = checkPageNum;
        this.color = color;
    }
}
