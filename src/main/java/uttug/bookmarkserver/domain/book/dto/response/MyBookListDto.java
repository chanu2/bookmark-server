package uttug.bookmarkserver.domain.book.dto.response;


import lombok.Getter;
import uttug.bookmarkserver.domain.asset.entity.MoodImage;
import uttug.bookmarkserver.domain.book.entity.Book;

@Getter
public class MyBookListDto {
    private String bookName;
    private String author;
    private Integer likeNumber;
    private Integer elapsedDay;

    public MyBookListDto(Book book) {
        this.bookName = book.getBookName();
        this.author = book.getAuthor();
        this.likeNumber = book.getLikeNumber();
        this.elapsedDay = book.getElapsedDay();
    }
}
