package uttug.bookmarkserver.domain.bookmark.dto.response;

import lombok.Getter;
import uttug.bookmarkserver.domain.book.entity.Book;
import uttug.bookmarkserver.domain.bookmark.entity.BookMark;
import uttug.bookmarkserver.domain.common.Color;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
public class BookMarkSummaryDto {
    private String summary;

    public BookMarkSummaryDto(BookMark bookMark) {
        this.summary = bookMark.getSummary();
    }
}
