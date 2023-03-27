package uttug.bookmarkserver.domain.book.dto.response;

import lombok.Getter;
import lombok.Setter;
import uttug.bookmarkserver.domain.book.entity.Book;
import uttug.bookmarkserver.domain.bookmark.dto.response.BookMarkSummaryDto;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class BookClubSummaryResponse {
    private String bookName;
    private Integer likeNumber;
    private Integer elapsedDay;
    private boolean registrationStatus;
    private boolean like;
    private List<BookMarkDetailDto> bookMarkDetailDtos;


    public BookClubSummaryResponse(Book book, boolean like) {
        this.bookName = book.getBookName();
        this.likeNumber = book.getLikeNumber();
        this.elapsedDay = book.getElapsedDay();
        this.registrationStatus = book.isRegistrationStatus();
        this.like = like;

    }
}
