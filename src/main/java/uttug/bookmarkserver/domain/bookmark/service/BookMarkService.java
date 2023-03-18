package uttug.bookmarkserver.domain.bookmark.service;

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
import uttug.bookmarkserver.domain.book.entity.Book;
import uttug.bookmarkserver.domain.book.exception.BookNotFoundException;
import uttug.bookmarkserver.domain.book.service.BookUtils;
import uttug.bookmarkserver.domain.bookmark.dto.request.CreateBookMarkRequest;
import uttug.bookmarkserver.domain.bookmark.dto.request.UpdateBookMarkRequest;
import uttug.bookmarkserver.domain.bookmark.dto.response.BookMarkInfoDto;
import uttug.bookmarkserver.domain.bookmark.entity.BookMark;
import uttug.bookmarkserver.domain.bookmark.repository.BookMarkRepository;
import uttug.bookmarkserver.domain.bookmark.service.dto.UpdateBookMarkDto;
import uttug.bookmarkserver.domain.user.entity.User;
import uttug.bookmarkserver.global.database.BaseEntity;
import uttug.bookmarkserver.global.utill.security.SecurityUtils;
import uttug.bookmarkserver.global.utill.user.UserUtils;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BookMarkService extends BaseEntity {

    private final BookMarkRepository bookMarkRepository;
    private final UserUtils userUtils;
    private final AssetUtils assetUtils;

    private final BookUtils bookUtils;


    //북마크 생성
    @Transactional
    public BookMark createBookMark(Long bookId,CreateBookMarkRequest createBookMarkRequest){

        User user = userUtils.getUserFromSecurityContext();

        Book book = bookUtils.queryBook(bookId);

        book.validUserIsHost(user.getEmail());

        BookMark bookMark = makeBookMark(createBookMarkRequest, book, user);

        bookMarkRepository.save(bookMark);

        bookMark.addBook(book);

        return bookMark;
    }

    // 북 마크 삭제
    @Transactional
    public void deleteBookMark(Long bookMarkId){

        String currentUserEmail = SecurityUtils.getCurrentUserEmail();

        BookMark bookMark = queryBookMark(bookMarkId);

        bookMark.validUserIsHost(currentUserEmail);

        bookMarkRepository.delete(bookMark);

    }

    // 북마크 업데이트
    @Transactional
    public void updateBook(Long bookMarkId, UpdateBookMarkRequest updateBookMarkRequest){

        String currentUserEmail = SecurityUtils.getCurrentUserEmail();
        BookMark bookMark = queryBookMark(bookMarkId);
        bookMark.validUserIsHost(currentUserEmail);

        bookMark.updateBook(updateBookMarkRequest.toUpdateBookMarkDto());

    }

    // 북마크 리스트 가져오기 (날짜순서)

    public Slice<BookMarkInfoDto> bookMarkList(Long bookId, Integer page){

        PageRequest pageRequest = PageRequest.of(page,20, Sort.Direction.DESC,"createdDate");

        Slice<BookMark> bookMarkList = bookMarkRepository.findById(bookId,pageRequest);

        return bookMarkList.map(bm -> new BookMarkInfoDto(
                bm.getBookMarkName(),
                bm.getMoodImageUrl(),
                bm.getSummary(),
                bm.getCheckPageNum(),
                bm.getColor()));

    }


    private BookMark makeBookMark(CreateBookMarkRequest createBookMarkRequest,Book book ,User user){

        BookMark bookMark = BookMark.builder()
                .book(book)
                .user(user)
                .bookMarkName(createBookMarkRequest.getBookMarkName())
                .moodImageUrl(createBookMarkRequest.getMoodImageUrl())
                .summary(createBookMarkRequest.getSummary())
                .checkPageNum(createBookMarkRequest.getCheckPageNum())
                .color(createBookMarkRequest.getColor())
                .build();

        return bookMark;
    }

    public BookMark queryBookMark(Long bookMarkId) {
        return bookMarkRepository
                .findById(bookMarkId)
                .orElseThrow(() -> BookNotFoundException.EXCEPTION);
    }




}
