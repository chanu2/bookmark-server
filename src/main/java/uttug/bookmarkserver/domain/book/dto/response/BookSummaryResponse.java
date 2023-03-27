package uttug.bookmarkserver.domain.book.dto.response;

import lombok.Getter;
import uttug.bookmarkserver.domain.book.entity.Book;
import uttug.bookmarkserver.domain.bookmark.dto.response.BookMarkSummaryDto;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class BookSummaryResponse {
    private String bookName;
    private Integer likeNumber;
    private Integer elapsedDay;
    private boolean registrationStatus;
    private List<BookMarkSummaryDto> bookMarkSummaryList;


    public BookSummaryResponse(Book book) {
        this.bookName = book.getBookName();
        this.likeNumber = book.getLikeNumber();
        this.elapsedDay = book.getElapsedDay();
        this.registrationStatus = book.isRegistrationStatus();
        this.bookMarkSummaryList = book.getBookMarks().stream().map(bm -> new BookMarkSummaryDto(bm)).collect(Collectors.toList());

    }
}
