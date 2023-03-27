package uttug.bookmarkserver.domain.book.dto.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
public class BookDetailResponse {
    private Long bookId;
    private String nickname;
    private String author;
    private Integer likeNumber;
    private Integer elapsedDay;
    private List<BookMarkDetailDto> bookMarkDetailDtos;

    public BookDetailResponse(Long bookId, String nickname, String author, Integer likeNumber, Integer elapsedDay) {
        this.bookId = bookId;
        this.nickname = nickname;
        this.author = author;
        this.likeNumber = likeNumber;
        this.elapsedDay = elapsedDay;
    }
}
