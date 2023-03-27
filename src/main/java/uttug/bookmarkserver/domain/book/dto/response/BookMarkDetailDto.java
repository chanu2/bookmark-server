package uttug.bookmarkserver.domain.book.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BookMarkDetailDto {
    private Long bookId;
    private Integer checkPageNum;
    private String summary;

}
