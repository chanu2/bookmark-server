package uttug.bookmarkserver.domain.book.repository;


import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uttug.bookmarkserver.domain.book.entity.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book,Long> {

    Book save(Book book);

    @Override
    Optional<Book> findById(Long id);


    @Query("select b from Book b"+
            " join fetch b.user u"+
            " where u.email = :email order by b.createdDate asc")
    List<Book> findHomeBookList(@Param("email") String userUid);


    Slice<Book> findBy(PageRequest pageRequest);
    Slice<Book> findAllByRegistrationStatus(PageRequest pageRequest,Boolean status);

    List<Book> findAllByCompletedStatus(Boolean status);


}
