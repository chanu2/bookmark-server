package uttug.bookmarkserver.domain.book.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import uttug.bookmarkserver.domain.book.dto.response.BookClubSummaryResponse;
import uttug.bookmarkserver.domain.book.dto.response.BookMarkDetailDto;
import uttug.bookmarkserver.domain.book.dto.response.BookDetailResponse;
import uttug.bookmarkserver.domain.book.entity.Book;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;


@Repository
@RequiredArgsConstructor
@Slf4j
public class BookQueryRepository{

    private final EntityManager em;


    public List<BookDetailResponse> findMyBookList(String email) {

        List<BookDetailResponse> result = findBooks(email);

        List<Long> bookIds = changeBookId(result);

        Map<Long, List<BookMarkDetailDto>> bookMap = findBookMarkMap(bookIds);

        result.forEach(r -> r.setBookMarkDetailDtos(bookMap.get(r.getBookId())));

        return result;

    }

    public BookDetailResponse findSummaryBook(Book book) {

        BookDetailResponse bookDetail = new BookDetailResponse(
                book.getId(),
                book.getUser().getNickname(),
                book.getAuthor(),
                book.getLikeNumber(),
                book.getElapsedDay());

        List<BookMarkDetailDto> result = findBookSummaryMap(book.getId());

        bookDetail.setBookMarkDetailDtos(result);

        return bookDetail;
    }

    public BookClubSummaryResponse findSummaryBookClub(Book book,boolean like) {

        BookClubSummaryResponse summaryResponse = new BookClubSummaryResponse(book, like);

        List<BookMarkDetailDto> result = findBookSummaryMap(book.getId());

        summaryResponse.setBookMarkDetailDtos(result);

        return summaryResponse;
    }



    private Map<Long, List<BookMarkDetailDto>> findBookMarkMap(List<Long> bookIds) {
        List<BookMarkDetailDto> books = em.createQuery("select new uttug.bookmarkserver.domain.book.dto.response.BookMarkDetailDto(bm.book.id,bm.checkPageNum,bm.summary)" +
                " from BookMark bm" +
                " where bm.book.id in :bookIds order by bm.checkPageNum desc", BookMarkDetailDto.class).setParameter("bookIds", bookIds).getResultList();


        Map<Long, List<BookMarkDetailDto>> bookMap = books.stream().collect(Collectors.groupingBy(bookMarkDetailDto -> (bookMarkDetailDto.getBookId())));

        return bookMap;
    }

    private List<BookMarkDetailDto> findBookSummaryMap(Long bookId) {
        List<BookMarkDetailDto> books = em.createQuery("select new uttug.bookmarkserver.domain.book.dto.response.BookMarkDetailDto(bm.book.id,bm.checkPageNum,bm.summary)" +
                " from BookMark bm" +
                " where bm.book.id in :bookId order by bm.checkPageNum asc", BookMarkDetailDto.class).setParameter("bookId", bookId).getResultList();
        return books;
    }

    private static List<Long> changeBookId(List<BookDetailResponse> result) {
        List<Long> bookIds = result.stream().map(b -> b.getBookId()).collect(Collectors.toList());
        return bookIds;
    }

    private List<BookDetailResponse> findBooks(String email) {
        return em.createQuery("select new uttug.bookmarkserver.domain.book.dto.response.BookDetailResponse(b.id,b.user.nickname,b.author,b.likeNumber,b.elapsedDay)" +
                " from Book b" +
                " join b.user u" +
                " where u.email = :email", BookDetailResponse.class).setParameter("email",email).getResultList();
    }



}
