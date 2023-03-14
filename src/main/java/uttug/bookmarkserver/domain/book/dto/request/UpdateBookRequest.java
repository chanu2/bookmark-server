package uttug.bookmarkserver.domain.book.dto.request;

import lombok.Getter;
import uttug.bookmarkserver.domain.book.entity.Book;
import uttug.bookmarkserver.domain.book.service.dto.UpdateBookDto;

@Getter
public class UpdateBookRequest {
    private String bookName;
    private String author;
    private String publisher;
    private Integer pageNumber;


    public UpdateBookDto toUpdateBookDto() {
        return UpdateBookDto.builder()
                .bookName(bookName)
                .author(author)
                .publisher(publisher)
                .pageNumber(pageNumber)
                .build();
    }
}
