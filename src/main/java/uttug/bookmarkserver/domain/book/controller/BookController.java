package uttug.bookmarkserver.domain.book.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.*;
import uttug.bookmarkserver.domain.book.dto.request.CreateBookRequest;
import uttug.bookmarkserver.domain.book.dto.request.UpdateBookRequest;
import uttug.bookmarkserver.domain.book.dto.response.BookClubInfoDto;
import uttug.bookmarkserver.domain.book.dto.response.BookResponse;
import uttug.bookmarkserver.domain.book.dto.response.MyBookListDto;
import uttug.bookmarkserver.domain.book.service.BookService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/book")
public class BookController {
    private final BookService bookService;

    @PostMapping("/create")
    public BookResponse createBook(@RequestBody CreateBookRequest createBookRequest){

        return bookService.createBook(createBookRequest);
    }

    @PostMapping("/update/{bookId}")
    public BookResponse updateBook(@PathVariable Long bookId, @RequestBody UpdateBookRequest updateBookRequest){

        return bookService.updateBook(bookId,updateBookRequest);
    }

    @PostMapping("/delete/{bookId}")
    public void deleteBook(@PathVariable Long bookId){
        bookService.deleteBook(bookId);
    }

    @PostMapping("/resister/{bookId}")
    public void postBook(@PathVariable Long bookId){
        bookService.postBook(bookId);
    }

    @PostMapping("/resister/cancel/{bookId}")
    public void deletePostBook(@PathVariable Long bookId){
        bookService.deletePostBook(bookId);
    }

    @GetMapping("/my")
    public List<MyBookListDto> myBookList(){
        return bookService.getMyBookList();
    }

    @GetMapping("/club")
    public Slice<BookClubInfoDto> bookClubList(@RequestParam Integer page){
        return bookService.bookClubList(page);
    }



}
