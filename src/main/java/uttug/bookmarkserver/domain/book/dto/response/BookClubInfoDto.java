package uttug.bookmarkserver.domain.book.dto.response;

import lombok.Getter;

@Getter
public class BookClubInfoDto {

    private String bookName;
    private String author;
    private String publisher;
    private Integer pageNumber;
    private Integer likeNumber;
    private Integer elapsedDay;


    private String nickname;

    public BookClubInfoDto(String bookName, String author, String publisher, Integer pageNumber, Integer likeNumber, Integer elapsedDay, String nickname) {
        this.bookName = bookName;
        this.author = author;
        this.publisher = publisher;
        this.pageNumber = pageNumber;
        this.likeNumber = likeNumber;
        this.elapsedDay = elapsedDay;
        this.nickname = nickname;
    }
}
