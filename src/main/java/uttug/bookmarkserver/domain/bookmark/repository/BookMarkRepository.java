package uttug.bookmarkserver.domain.bookmark.repository;


import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import uttug.bookmarkserver.domain.book.entity.Book;
import uttug.bookmarkserver.domain.bookmark.entity.BookMark;

import java.util.List;
import java.util.Optional;

public interface BookMarkRepository extends JpaRepository<BookMark,Long> {

    BookMark save(BookMark bookMark);

    @Override
    Optional<BookMark> findById(Long id);
    Slice<BookMark> findAllByBookId(Long id,PageRequest pageRequest);

    List<BookMark> findAllByBook(Book book);

}
