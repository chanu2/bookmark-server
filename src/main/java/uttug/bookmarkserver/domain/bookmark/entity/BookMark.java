package uttug.bookmarkserver.domain.bookmark.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import uttug.bookmarkserver.domain.book.entity.Book;
import uttug.bookmarkserver.domain.book.service.dto.UpdateBookDto;
import uttug.bookmarkserver.domain.bookmark.exception.NotHostException;
import uttug.bookmarkserver.domain.bookmark.exception.OutOfPageException;
import uttug.bookmarkserver.domain.bookmark.service.dto.UpdateBookMarkDto;
import uttug.bookmarkserver.domain.common.Color;
import uttug.bookmarkserver.domain.common.Gender;
import uttug.bookmarkserver.domain.user.entity.User;
import uttug.bookmarkserver.global.database.BaseEntity;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BookMark extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_mark_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    private String bookMarkName;
    private String moodImageUrl;
    private String summary;
    private Integer checkPageNum;
    @Enumerated(EnumType.STRING)
    private Color color;

    @Builder
    public BookMark(Book book,User user, String bookMarkName, String moodImageUrl, String summary, Integer checkPageNum, Color color) {

        if(this.book!=null){
            this.book.getBookMarks().remove(this);
        }
        this.book=book;
        book.getBookMarks().add(this);
        this.user = user;
        this.bookMarkName = bookMarkName;
        this.moodImageUrl = moodImageUrl;
        this.summary = summary;
        this.checkPageNum = checkPageNum;
        this.color = color;

    }

    public void validUserIsHost(String email) {
        if (!checkUserIsHost(email)) {
            throw NotHostException.EXCEPTION;
        }
    }

    public Boolean checkUserIsHost(String email) {
        return user.getEmail().equals(email);
    }

    public void subBookmark(Book book){
        book.getBookMarks().remove(this);
    }

    public void checkOutOfPage(){
        if(this.book.getPageNumber()<checkPageNum){
            throw OutOfPageException.EXCEPTION;
        }
    }

    public void updateBook(UpdateBookMarkDto updateBookMarkDto) {
        this.bookMarkName = updateBookMarkDto.getBookMarkName();
        this.moodImageUrl = updateBookMarkDto.getMoodImageUrl();
        this.summary = updateBookMarkDto.getSummary();
        this.checkPageNum = updateBookMarkDto.getCheckPageNum();
        this.color = updateBookMarkDto.getColor();
    }
}
