package uttug.bookmarkserver.domain.likes.repository;


import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import uttug.bookmarkserver.domain.book.entity.Book;
import uttug.bookmarkserver.domain.likes.entity.Likes;
import uttug.bookmarkserver.domain.user.entity.User;

import java.util.List;
import java.util.Optional;

public interface LikesRepository extends JpaRepository<Likes,Long> {

    Likes save(Likes likes);

    @Override
    Optional<Likes> findById(Long id);

    Optional<Likes> findByBookIdAndUserEmail(Long bookId, String email);

    void deleteByBookIdAndUserEmail(Long bookId, String email);


}
