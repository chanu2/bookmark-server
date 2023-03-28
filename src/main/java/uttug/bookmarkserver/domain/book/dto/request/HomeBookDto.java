package uttug.bookmarkserver.domain.book.dto.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import uttug.bookmarkserver.domain.book.entity.Book;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter

public class HomeBookDto {
    private String bookName;
    private Integer pageNumber;

    public HomeBookDto(Book book) {
        this.bookName = book.getBookName();
        this.pageNumber = book.getPageNumber();
    }
}
