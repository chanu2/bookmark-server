package uttug.bookmarkserver.domain.bookmark.dto.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import uttug.bookmarkserver.domain.common.Color;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateBookMarkRequest {
    private String bookMarkName;
    private String moodImageUrl;
    private String summary;
    private Integer checkPageNum;
    @Enumerated(EnumType.STRING)
    private Color color;

}
