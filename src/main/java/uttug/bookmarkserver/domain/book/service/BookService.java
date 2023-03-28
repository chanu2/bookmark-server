package uttug.bookmarkserver.domain.book.service;


import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uttug.bookmarkserver.domain.book.dto.request.CreateBookRequest;
import uttug.bookmarkserver.domain.book.dto.request.HomeBookDto;
import uttug.bookmarkserver.domain.book.dto.request.UpdateBookRequest;
import uttug.bookmarkserver.domain.book.dto.response.*;
import uttug.bookmarkserver.domain.book.entity.Book;
import uttug.bookmarkserver.domain.book.exception.BookNotFoundException;
import uttug.bookmarkserver.domain.book.repository.BookQueryRepository;
import uttug.bookmarkserver.domain.book.repository.BookRepository;
import uttug.bookmarkserver.domain.likes.dto.LikeBookResponse;
import uttug.bookmarkserver.domain.likes.entity.Likes;
import uttug.bookmarkserver.domain.likes.repository.LikesRepository;
import uttug.bookmarkserver.domain.likes.service.LikesUtils;
import uttug.bookmarkserver.domain.user.entity.User;
import uttug.bookmarkserver.global.utill.security.SecurityUtils;
import uttug.bookmarkserver.global.utill.user.UserUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.lang.Math.min;

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




    // 내가 쓴 책 홈화면 리스트
    public List<List<HomeBookDto>> getMyHomeBookList(){

        String currentUserEmail = SecurityUtils.getCurrentUserEmail();

        List<Book> homeBookList = bookRepository.findHomeBookList(currentUserEmail);

        List<HomeBookDto> bookList = homeBookList.stream().map(b -> new HomeBookDto(b)).collect(Collectors.toList());

        List<List<HomeBookDto>> partition = Lists.partition(bookList, 4);

        return partition;

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

    public List<BookDetailResponse> myBookList(){
        String currentUserEmail = SecurityUtils.getCurrentUserEmail();
        return bookQueryRepository.findMyBookList(currentUserEmail);
    }


    public BookDetailResponse bookSummary(Long bookId){

        String currentUserEmail = SecurityUtils.getCurrentUserEmail();

        Book book = queryBook(bookId);

        book.validUserIsHost(currentUserEmail);

        return bookQueryRepository.findSummaryBook(book);
    }


    public BookClubSummaryResponse bookClubSummary(Long bookId){

        String currentUserEmail = SecurityUtils.getCurrentUserEmail();

        Book book = queryBook(bookId);

        Boolean like = likesUtils.findLikes(bookId, currentUserEmail);

        return bookQueryRepository.findSummaryBookClub(book,like);

    }


    // 북클럽리스트
    public List<BookClubInfoDto> bookClubList(Integer page){

        PageRequest pageRequest = PageRequest.of(page,20, Sort.Direction.DESC,"likeNumber");

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



    @Transactional
    public LikeBookResponse saveLike(Long bookId){

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

            return new LikeBookResponse(true);

        }else {
            book.subLikeNum();
            likesRepository.deleteByBookIdAndUserEmail(bookId,findEmail);
            return new LikeBookResponse(false);

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

    @Override
    public void addCompleteReading() {
        List<Book> booKCompletedStatus = bookRepository.findAllByCompletedStatus(false);

        booKCompletedStatus.forEach(Book::addElapsedDay);

    }


}
