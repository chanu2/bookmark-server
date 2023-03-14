package uttug.bookmarkserver.domain.book.service.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UpdateBookDto {

    private String bookName;
    private String author;
    private String publisher;
    private Integer pageNumber;
}
