package uttug.bookmarkserver.domain.book.entity;



import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import uttug.bookmarkserver.domain.book.exception.NotHostException;
import uttug.bookmarkserver.domain.book.service.dto.UpdateBookDto;
import uttug.bookmarkserver.domain.bookmark.entity.BookMark;
import uttug.bookmarkserver.domain.likes.entity.Likes;
import uttug.bookmarkserver.domain.user.entity.User;
import uttug.bookmarkserver.global.database.BaseEntity;


import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Slf4j
public class Book extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Long id;
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private List<BookMark> bookMarks = new ArrayList<>();

    @OneToMany(mappedBy = "book",cascade = CascadeType.ALL)
    private List<Likes> likesList = new ArrayList<>();

    private String bookName;

    private String author;

    private String publisher;

    private Integer pageNumber;

    private Integer likeNumber;

    private Integer elapsedDay;
    private boolean registrationStatus;
    private boolean completedStatus;



    //== 연관관계 메서드==//

    @Builder
    public Book(User user, String bookName, String author, String publisher,Integer pageNumber) {
        this.user = user;
        this.bookName = bookName;
        this.author = author;
        this.publisher = publisher;
        this.pageNumber = pageNumber;
        this.likeNumber = 0;
        this.elapsedDay = 1;
        this.registrationStatus = false;
        this.completedStatus = false;

    }

    public void validUserIsHost(String email) {
        if (!checkUserIsHost(email)) {
            throw NotHostException.EXCEPTION;
        }
    }


    public Boolean checkUserIsHost(String email) {
        return user.getEmail().equals(email);
    }

    public void updateBook(UpdateBookDto updateBookDto) {
        this.bookName = updateBookDto.getBookName();
        this.author = updateBookDto.getAuthor();
        this.pageNumber = updateBookDto.getPageNumber();
    }

    public void addLikeNum(){
        likeNumber+=1;
    }

    public void subLikeNum(){
        likeNumber-=1;
    }

    public void addElapsedDay(){ elapsedDay+=1; }

    public void postBook(){this.registrationStatus = true;
    }
    public void deletePostBook(){
        this.registrationStatus = false;
    }

    public void completeBook(){
        this.completedStatus = true; }



}
