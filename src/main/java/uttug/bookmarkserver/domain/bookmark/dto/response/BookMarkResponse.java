package uttug.bookmarkserver.domain.bookmark.dto.response;


import lombok.Getter;
import uttug.bookmarkserver.domain.book.entity.Book;
import uttug.bookmarkserver.domain.bookmark.entity.BookMark;
import uttug.bookmarkserver.domain.common.Color;
import uttug.bookmarkserver.domain.user.entity.User;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import static javax.persistence.FetchType.LAZY;

@Getter
public class BookMarkResponse {

    private Long bookId;
    private Long bookMarkId;
    private String bookMarkName;
    private String moodImageUrl;
    private String summary;
    private Integer checkPageNum;
    @Enumerated(EnumType.STRING)
    private Color color;
    private boolean completedStatus;

    public BookMarkResponse(BookMark bookMark) {
        this.bookId =bookMark.getBook().getId();
        this.bookMarkId = bookMark.getId();
        this.bookMarkName = bookMark.getBookMarkName();
        this.moodImageUrl =bookMark.getMoodImageUrl();
        this.summary = bookMark.getSummary();
        this.checkPageNum = bookMark.getCheckPageNum();
        this.color = bookMark.getColor();
        this.completedStatus = bookMark.getBook().isCompletedStatus();

    }
}
