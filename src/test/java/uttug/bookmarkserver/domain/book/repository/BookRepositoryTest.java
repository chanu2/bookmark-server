package uttug.bookmarkserver.domain.book.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import uttug.bookmarkserver.domain.book.entity.Book;
import uttug.bookmarkserver.domain.common.Gender;
import uttug.bookmarkserver.domain.user.entity.User;

import javax.persistence.EntityManager;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Transactional
@Rollback(false)
class BookRepositoryTest {

    @Autowired
    EntityManager em;

    @Autowired
    BookRepository bookRepository;

    @Test
    public void 내가쓴_책_리스트_가져오기2() {

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

        Book book3 = Book.builder()
                .user(user)
                .bookName("돈키호테3")
                .author("생땍베르찌3")
                .publisher("유명 출판사3")
                .pageNumber(300)
                .build();


        Book book4 = Book.builder()
                .user(user)
                .bookName("돈키호테4")
                .author("생땍베르찌4")
                .publisher("유명 출판사4")
                .pageNumber(300)
                .build();

        Book book5 = Book.builder()
                .user(user)
                .bookName("돈키호테5")
                .author("생땍베르찌5")
                .publisher("유명 출판사5")
                .pageNumber(300)
                .build();

        Book book6 = Book.builder()
                .user(user)
                .bookName("돈키호테6")
                .author("생땍베르찌6")
                .publisher("유명 출판사6")
                .pageNumber(300)
                .build();



        em.persist(book1);
        em.persist(book2);
        em.persist(book3);
        em.persist(book4);
        em.persist(book5);
        em.persist(book6);

        em.flush();
        em.clear();

        PageRequest pageRequest = PageRequest.of(0,2, Sort.Direction.DESC,"createdDate");




//        List<Book> bookList = bookRepository.findBooksByUserEmailOrderByCreatedDateDesc("mdsoo55828@gmail.com");
//        for (Book book : bookList) {
//            System.out.println("book.getBookName() = " + book.getBookName());
//            System.out.println("book.getAuthor() = " + book.getAuthor());
//        }



    }


}