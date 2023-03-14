package uttug.bookmarkserver.domain.book.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uttug.bookmarkserver.domain.book.service.BookService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/food")
public class BookController {
    private final BookService bookService;

}
