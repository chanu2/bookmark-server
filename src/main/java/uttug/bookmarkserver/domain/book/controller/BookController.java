package uttug.bookmarkserver.domain.book.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uttug.bookmarkserver.domain.book.dto.request.CreateBookRequest;
import uttug.bookmarkserver.domain.book.dto.request.UpdateBookRequest;
import uttug.bookmarkserver.domain.book.dto.response.*;
import uttug.bookmarkserver.domain.book.service.BookService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/books")
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

    @DeleteMapping("/delete/{bookId}")
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

    // 나의 책 리스트 보여주기
    @GetMapping("/my")
    public List<BookDetailResponse> myBookList(){
        return bookService.myBookList();
    }

    @GetMapping("/summary/{bookId}")
    public BookDetailResponse bookSummary(@PathVariable Long bookId){
        return bookService.bookSummary(bookId);
    }

    @GetMapping("/club")
    public List<BookClubInfoDto> bookClubList(@RequestParam Integer page){
        return bookService.bookClubList(page);
    }


    @GetMapping("/club/summary/{bookId}")
    public BookClubSummaryResponse bookClubSummary( @PathVariable Long bookId){
        return bookService.bookClubSummary(bookId);
    }


    @PostMapping("/likes/{bookId}")
    public void saveLikes(@PathVariable Long bookId){
        bookService.saveLike(bookId);
    }


//    @GetMapping("/summary/{bookId}")
//    public BookSummaryResponse bookSummary( @PathVariable Long bookId){
//        return bookService.bookSummary(bookId);
//    }




}
