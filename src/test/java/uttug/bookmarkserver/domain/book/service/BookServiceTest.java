package uttug.bookmarkserver.domain.book.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import uttug.bookmarkserver.domain.book.entity.Book;
import uttug.bookmarkserver.domain.book.repository.BookRepository;
import uttug.bookmarkserver.domain.bookmark.dto.request.CreateBookMarkRequest;
import uttug.bookmarkserver.domain.bookmark.entity.BookMark;
import uttug.bookmarkserver.domain.bookmark.repository.BookMarkRepository;
import uttug.bookmarkserver.domain.bookmark.service.BookMarkService;
import uttug.bookmarkserver.domain.common.Color;
import uttug.bookmarkserver.domain.user.entity.User;

import javax.persistence.EntityManager;

import java.util.Collections;
import java.util.List;


@SpringBootTest
@Transactional
@Rollback(false)
class BookServiceTest {

    @Autowired
    EntityManager em;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    BookMarkRepository bookMarkRepository;

    @Autowired
    BookService bookService;

    @Autowired
    BookMarkService bookMarkService;


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

    @Test
    public void 양방향_연관관계_확인() {

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


        CreateBookMarkRequest createBookMarkRequest1 = new CreateBookMarkRequest("어린왕자1","2","3",1234,Color.RED);
        CreateBookMarkRequest createBookMarkRequest2 = new CreateBookMarkRequest("어린왕자2","2","3",1234,Color.RED);

        BookMark bookMark1 = BookMark.builder()
                .book(book1)
                .user(user)
                .bookMarkName(createBookMarkRequest1.getBookMarkName())
                .moodImageUrl(createBookMarkRequest1.getMoodImageUrl())
                .summary(createBookMarkRequest1.getSummary())
                .checkPageNum(createBookMarkRequest1.getCheckPageNum())
                .color(createBookMarkRequest1.getColor())
                .build();




        BookMark bookMark2 = BookMark.builder()
                .book(book1)
                .user(user)
                .bookMarkName(createBookMarkRequest2.getBookMarkName())
                .moodImageUrl(createBookMarkRequest2.getMoodImageUrl())
                .summary(createBookMarkRequest2.getSummary())
                .checkPageNum(createBookMarkRequest2.getCheckPageNum())
                .color(createBookMarkRequest2.getColor())
                .build();



        em.persist(bookMark1);
        em.persist(bookMark2);



        List<BookMark> bookMarks = book1.getBookMarks();

        for (BookMark mark : bookMarks) {
            System.out.println("mark.get = " + mark.getBookMarkName());
        }


        bookMark1.subBookmark(book1);
        bookMarkRepository.delete(bookMark1);

        em.flush();
        em.clear();

        Book bookGet = bookRepository.findById(book1.getId()).get();

        List<BookMark> bookMarkList = bookGet.getBookMarks();

        for (BookMark mark : bookMarkList) {
            System.out.println("mark.get = " + mark.getBookMarkName());
        }



    }



}