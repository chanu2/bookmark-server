package uttug.bookmarkserver.domain.book.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uttug.bookmarkserver.domain.book.entity.Book;

@Getter
@Setter
@NoArgsConstructor
public class BookClubInfoDto {

    private Long bookId;
    private String bookName;
    private String author;
    private String publisher;
    private Integer pageNumber;
    private Integer likeNumber;
    private String nickname;
    private boolean likeStatus;

    public BookClubInfoDto(Book book) {
        this.bookId= book.getId();
        this.bookName = book.getBookName();
        this.author = book.getAuthor();
        this.publisher = book.getPublisher();
        this.pageNumber = book.getPageNumber();
        this.likeNumber = book.getLikeNumber();
        this.nickname = book.getUser().getNickname();
        this.likeStatus = false;
    }
}
