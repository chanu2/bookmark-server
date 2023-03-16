package uttug.bookmarkserver.domain.book.service;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uttug.bookmarkserver.domain.asset.service.AssetUtils;
import uttug.bookmarkserver.domain.book.dto.request.CreateBookRequest;
import uttug.bookmarkserver.domain.book.dto.request.UpdateBookRequest;
import uttug.bookmarkserver.domain.book.dto.response.BookClubInfoDto;
import uttug.bookmarkserver.domain.book.dto.response.BookResponse;
import uttug.bookmarkserver.domain.book.dto.response.MyBookListDto;
import uttug.bookmarkserver.domain.book.entity.Book;
import uttug.bookmarkserver.domain.book.exception.BookNotFoundException;
import uttug.bookmarkserver.domain.book.exception.NotHostException;
import uttug.bookmarkserver.domain.book.repository.BookRepository;
import uttug.bookmarkserver.domain.user.entity.User;
import uttug.bookmarkserver.global.utill.security.SecurityUtils;
import uttug.bookmarkserver.global.utill.user.UserUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BookService implements BookUtils {

    private final BookRepository bookRepository;
    private final UserUtils userUtils;


    // 책 생성
    @Transactional
    public BookResponse createBook(CreateBookRequest createBookRequest){

        User user = userUtils.getUserFromSecurityContext();

        Book book = makeBook(createBookRequest, user);

        bookRepository.save(book);

        return new BookResponse(book);
    }

    //책 삭제
    @Transactional
    public void deleteBook(Long bookId){

        String currentUserEmail = SecurityUtils.getCurrentUserEmail();

        Book book = queryBook(bookId);

        book.validUserIsHost(currentUserEmail);

        bookRepository.delete(book);
    }


    // 책 내용 수정
    @Transactional
    public BookResponse updateBook(Long bookId, UpdateBookRequest updateBookRequest ){

        String currentUserEmail = SecurityUtils.getCurrentUserEmail();
        Book book = queryBook(bookId);
        book.validUserIsHost(currentUserEmail);

        book.updateBook(updateBookRequest.toUpdateBookDto());

        return new BookResponse(book);
    }



    // 내가 쓴 책 리스트
    public List<MyBookListDto> getMyBookList(){

        String currentUserEmail = SecurityUtils.getCurrentUserEmail();

        List<Book> bookList = bookRepository.findBooksByUserEmailOrderByCreatedDateDesc(currentUserEmail);

        return bookList.stream().map(b -> new MyBookListDto(b)).collect(Collectors.toList());
    }


    // 내가 쓴 책 홈화면 리스트
    public List<Book> getMyHomeBookList(){

        String currentUserEmail = SecurityUtils.getCurrentUserEmail();
        return bookRepository.findBooksByUserEmailOrderByCreatedDateAsc(currentUserEmail);

    }


    // 자랑하기
    @Transactional
    public void postBook(Long bookId){

        String currentUserEmail = SecurityUtils.getCurrentUserEmail();

        Book book = queryBook(bookId);

        book.validUserIsHost(currentUserEmail);

        book.postBook();
    }

    // 자랑하기 취소
    @Transactional
    public void deletePostBook(Long bookId){

        String currentUserEmail = SecurityUtils.getCurrentUserEmail();

        Book book = queryBook(bookId);

        book.validUserIsHost(currentUserEmail);

        book.deletePostBook();
    }


    // 북클럽 리스트
    public Slice<BookClubInfoDto> bookClubList(Integer page){

        PageRequest pageRequest = PageRequest.of(page,2, Sort.Direction.DESC,"likeNumber");

        Slice<Book> bookList = bookRepository.findBy(pageRequest);

        return bookList.map(b -> new BookClubInfoDto(
                b.getBookName(),
                b.getAuthor(),
                b.getPublisher(),
                b.getPageNumber(),
                b.getLikeNumber(),
                b.getElapsedDay(),
                b.getUser().getNickname()));

    }



    private Book makeBook(CreateBookRequest createBookRequest,User host){

        Book book = Book.builder()
                .user(host)
                .bookName(createBookRequest.getBookName())
                .author(createBookRequest.getAuthor())
                .publisher(createBookRequest.getPublisher())
                .pageNumber(createBookRequest.getPageNumber())
                .build();

        return book;
    }

    @Override
    public Book queryBook(Long bookId) {
        return bookRepository
                .findById(bookId)
                .orElseThrow(() -> BookNotFoundException.EXCEPTION);
    }


}
