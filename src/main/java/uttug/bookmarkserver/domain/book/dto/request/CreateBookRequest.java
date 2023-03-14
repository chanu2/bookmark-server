package uttug.bookmarkserver.domain.book.dto.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateBookRequest {

    @NotNull
    @Size(min = 1, max = 18)
    private String bookName;
    private String author;
    private Integer pageNumber;

}
