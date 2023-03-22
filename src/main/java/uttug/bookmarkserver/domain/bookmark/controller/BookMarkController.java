package uttug.bookmarkserver.domain.bookmark.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.*;
import uttug.bookmarkserver.domain.bookmark.dto.request.CreateBookMarkRequest;
import uttug.bookmarkserver.domain.bookmark.dto.request.UpdateBookMarkRequest;
import uttug.bookmarkserver.domain.bookmark.dto.response.BookMarkInfoDto;
import uttug.bookmarkserver.domain.bookmark.dto.response.BookMarkResponse;
import uttug.bookmarkserver.domain.bookmark.service.BookMarkService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/bookmark")
public class BookMarkController {

    private final BookMarkService bookMarkService;

    @PostMapping("/create/{bookId}")
    public BookMarkResponse createBook(@RequestBody CreateBookMarkRequest createBookMarkRequest ,@PathVariable Long bookId){
        return bookMarkService.createBookMark(bookId,createBookMarkRequest);
    }

    @PostMapping("/delete/{bookMarkId}")
    public void deleteBookMark(@PathVariable Long bookMarkId){
        bookMarkService.deleteBookMark(bookMarkId);
    }

    @PostMapping("/update/{bookMarkId}")
    public BookMarkResponse updateBook(@PathVariable Long bookMarkId, @RequestBody UpdateBookMarkRequest updateBookRequest){
        return bookMarkService.updateBookMark(bookMarkId,updateBookRequest);
    }

    @GetMapping("/list/{bookId}")
    public Slice<BookMarkInfoDto> bookMarkListInfo(@PathVariable Long bookId,@RequestParam Integer page){

        return bookMarkService.bookMarkList(bookId,page);
    }

    @GetMapping("/Info/{bookMarkId}")
    public BookMarkResponse bookMarkListInfo(@PathVariable Long bookMarkId){

        return bookMarkService.bookMarkDetail(bookMarkId);
    }




}
