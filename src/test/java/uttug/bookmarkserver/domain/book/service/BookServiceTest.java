package uttug.bookmarkserver.domain.book.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import uttug.bookmarkserver.domain.book.dto.request.CreateBookRequest;
import uttug.bookmarkserver.domain.book.entity.Book;
import uttug.bookmarkserver.domain.book.repository.BookRepository;
import uttug.bookmarkserver.domain.common.Gender;
import uttug.bookmarkserver.domain.user.entity.User;

import javax.persistence.EntityManager;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Transactional
@Rollback(false)
class BookServiceTest {

    @Autowired
    EntityManager em;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    BookService bookService;

    @Test
    public void 책_생성 () {

        CreateBookRequest createBookRequest = new CreateBookRequest("asdwd","돈키호테",120);





    }

    @Test
    public void 내가쓴_책_리스트_가져오기() {

        User user = User.builder()
                .email("mdsoo55828@gmail.com")
                .profilePath("https://asdwdsda")
                .gender(Gender.MAN)
                .nickname("chanu2")
                .roles(Collections.singletonList("ROLE_USER"))
                .build();
        
        em.persist(user);

        Book book1 = Book.builder()
                .user(user)
                .bookName("돈키호테")
                .author("생땍베르찌")
                .publisher("유명 출판사")
                .pageNumber(200)
                .build();
        
        
        Book book2 = Book.builder()
                .user(user)
                .bookName("돈키호테2")
                .author("생땍베르찌2")
                .publisher("유명 출판사2")
                .pageNumber(300)
                .build();
        

        em.persist(book1);
        em.persist(book2);
        em.flush();
        em.clear();


        List<Book> bookList = bookRepository.findBooksByUserEmailOrderByCreatedDateDesc("mdsoo55828@gmail.com");
        for (Book book : bookList) {
            System.out.println("book.getBookName() = " + book.getBookName());
            System.out.println("book.getAuthor() = " + book.getAuthor());
        }


    }



}