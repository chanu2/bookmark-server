package uttug.bookmarkserver.domain.book.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import uttug.bookmarkserver.domain.book.dto.response.MyBookListDto;
import uttug.bookmarkserver.domain.book.entity.Book;
import uttug.bookmarkserver.domain.bookmark.dto.request.CreateBookMarkRequest;
import uttug.bookmarkserver.domain.bookmark.entity.BookMark;
import uttug.bookmarkserver.domain.common.Color;
import uttug.bookmarkserver.domain.common.Gender;
import uttug.bookmarkserver.domain.user.entity.User;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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

    @Test
    public void 책에서_북마크_페이지_가져오기() {

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


        CreateBookMarkRequest createBookMarkRequest1 = new CreateBookMarkRequest("책갈피1","www.asdwd","3",1233, Color.RED);
        CreateBookMarkRequest createBookMarkRequest2 = new CreateBookMarkRequest("책갈피2","www.asdwd","3",126,Color.RED);
        CreateBookMarkRequest createBookMarkRequest3 = new CreateBookMarkRequest("책갈피3","www.asdwd","3",100,Color.RED);
        CreateBookMarkRequest createBookMarkRequest4 = new CreateBookMarkRequest("책갈피4","www.asdwd","3",133,Color.RED);
        CreateBookMarkRequest createBookMarkRequest5 = new CreateBookMarkRequest("책갈피5","www.asdwd","3",42,Color.RED);


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

        BookMark bookMark3 = BookMark.builder()
                .book(book2)
                .user(user)
                .bookMarkName(createBookMarkRequest3.getBookMarkName())
                .moodImageUrl(createBookMarkRequest3.getMoodImageUrl())
                .summary(createBookMarkRequest3.getSummary())
                .checkPageNum(createBookMarkRequest3.getCheckPageNum())
                .color(createBookMarkRequest3.getColor())
                .build();


        BookMark bookMark4 = BookMark.builder()
                .book(book1)
                .user(user)
                .bookMarkName(createBookMarkRequest4.getBookMarkName())
                .moodImageUrl(createBookMarkRequest4.getMoodImageUrl())
                .summary(createBookMarkRequest4.getSummary())
                .checkPageNum(createBookMarkRequest4.getCheckPageNum())
                .color(createBookMarkRequest4.getColor())
                .build();


        BookMark bookMark5 = BookMark.builder()
                .book(book1)
                .user(user)
                .bookMarkName(createBookMarkRequest5.getBookMarkName())
                .moodImageUrl(createBookMarkRequest5.getMoodImageUrl())
                .summary(createBookMarkRequest5.getSummary())
                .checkPageNum(createBookMarkRequest5.getCheckPageNum())
                .color(createBookMarkRequest5.getColor())
                .build();




        em.persist(bookMark1);
        em.persist(bookMark2);
        em.persist(bookMark3);
        em.persist(bookMark4);
        em.persist(bookMark5);


        em.flush();
        em.clear();

        List<Book> bookList = bookRepository.findMyBook("mdsoo55828@gmail.com");
        System.out.println("----------------------------------");
        System.out.println("bookList.size() = " + bookList.size());
        System.out.println("bookList.stream().collect(Collectors.toList()) = " + bookList.stream().collect(Collectors.toList()));

        List<MyBookListDto> myBookListDtos = new ArrayList<>();

        for (Book book : bookList) {

            MyBookListDto myBookListDto = new MyBookListDto(book);

            List<Integer> pageNumList = new ArrayList<>();

            for (BookMark bookMarkList : book.getBookMarks()) {

                pageNumList.add(bookMarkList.getCheckPageNum());

            }

            Integer maxNowPage = Collections.max(pageNumList);
            myBookListDto.setNowPageNumber(maxNowPage);
            myBookListDtos.add(myBookListDto);

        }

        for (MyBookListDto myBookListDto : myBookListDtos) {
            System.out.println("myBookListDto.getBookName() = " + myBookListDto.getBookName());
            System.out.println("myBookListDto.getNowPageNumber() = " + myBookListDto.getNowPageNumber());
            System.out.println("myBookListDto.getNowPageNumber() = " + myBookListDto.getNowPageNumber());
        }
        


    }


}