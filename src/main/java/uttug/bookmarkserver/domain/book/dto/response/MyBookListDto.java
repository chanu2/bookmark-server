package uttug.bookmarkserver.domain.book.dto.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uttug.bookmarkserver.domain.asset.entity.MoodImage;
import uttug.bookmarkserver.domain.book.entity.Book;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MyBookListDto {
    private String bookName;
    private String author;
    private Integer likeNumber;
    private Integer elapsedDay;
    private Integer nowPageNumber;

    public MyBookListDto(Book book) {
        this.bookName = book.getBookName();
        this.author = book.getAuthor();
        this.likeNumber = book.getLikeNumber();
        this.elapsedDay = book.getElapsedDay();
    }
}
