package uttug.bookmarkserver.domain.book.service;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uttug.bookmarkserver.domain.book.dto.request.CreateBookRequest;
import uttug.bookmarkserver.domain.book.dto.request.UpdateBookRequest;
import uttug.bookmarkserver.domain.book.dto.response.*;
import uttug.bookmarkserver.domain.book.entity.Book;
import uttug.bookmarkserver.domain.book.exception.BookNotFoundException;
import uttug.bookmarkserver.domain.book.repository.BookQueryRepository;
import uttug.bookmarkserver.domain.book.repository.BookRepository;
import uttug.bookmarkserver.domain.bookmark.entity.BookMark;
import uttug.bookmarkserver.domain.likes.entity.Likes;
import uttug.bookmarkserver.domain.likes.repository.LikesRepository;
import uttug.bookmarkserver.domain.likes.service.LikesUtils;
import uttug.bookmarkserver.domain.user.entity.User;
import uttug.bookmarkserver.global.utill.security.SecurityUtils;
import uttug.bookmarkserver.global.utill.user.UserUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BookService implements BookUtils {

    private final BookRepository bookRepository;
    private final UserUtils userUtils;
    private final LikesUtils likesUtils;
    private final LikesRepository likesRepository;
    private final BookQueryRepository bookQueryRepository;


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

        List<Book> bookList = bookRepository.findMyBook(currentUserEmail);


        List<MyBookListDto> myBookListDtos = new ArrayList<>();


        for (Book book : bookList) {

            MyBookListDto myBookListDto = new MyBookListDto(book);

            List<Integer> pageNumList = new ArrayList<>();

            for (BookMark bookMark : book.getBookMarks()) {

                pageNumList.add(bookMark.getCheckPageNum());

            }

            Integer maxNowPage = Collections.max(pageNumList);
            myBookListDto.setNowPageNumber(maxNowPage);
            myBookListDtos.add(myBookListDto);

        }

        return myBookListDtos;
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



    // 북클럽리스트
    public List<BookClubInfoDto> bookClubList(Integer page){

        PageRequest pageRequest = PageRequest.of(page,3, Sort.Direction.DESC,"likeNumber");

        List<Book> bookList = bookRepository.findAllByRegistrationStatus(pageRequest, true).getContent();

        String email = SecurityUtils.getCurrentUserEmail();

        List<BookClubInfoDto> objects = new ArrayList<>();

        for (Book book : bookList) {

            Boolean likes = likesUtils.findLikes(book.getId(), email);
            BookClubInfoDto bookClubInfoDto = new BookClubInfoDto(book);

            if(likes){
                bookClubInfoDto.setLikeStatus(true);
            }

            objects.add(bookClubInfoDto);

        }

        return objects;

    }

    // 나의 책 요약 페이지 정보
    public BookSummaryResponse bookSummary(Long bookId){

        String currentUserEmail = SecurityUtils.getCurrentUserEmail();

        Book book = queryBook(bookId);

        book.validUserIsHost(currentUserEmail);

        return new BookSummaryResponse(book);
    }


    // 북마크 요약 페이지 정보
    public BookClubSummaryResponse bookClubSummary(Long bookId){

        String currentUserEmail = SecurityUtils.getCurrentUserEmail();

        Book book = queryBook(bookId);

        Boolean likes = likesUtils.findLikes(bookId, currentUserEmail);

        return new BookClubSummaryResponse(book,likes);
    }




    // 책 좋아요
    @Transactional
    public Boolean saveLike(Long bookId){

        String findEmail = SecurityUtils.getCurrentUserEmail();

        Optional<Likes> findLike = likesRepository.findByBookIdAndUserEmail(bookId, findEmail);

        User user = userUtils.getUserByEmail(findEmail);
        Book book = queryBook(bookId);

        if(findLike.isEmpty()){

            Likes like = Likes.builder()
                    .book(book)
                    .user(user)
                    .build();

            likesRepository.save(like);
            book.addLikeNum();

            return true;

        }else {
            book.subLikeNum();
            likesRepository.deleteByBookIdAndUserEmail(bookId,findEmail);
            return false;
        }
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


    public List<BookQueryDto> test(){
        String currentUserEmail = SecurityUtils.getCurrentUserEmail();
        return bookQueryRepository.findBook_optimization(currentUserEmail);
    }

}
