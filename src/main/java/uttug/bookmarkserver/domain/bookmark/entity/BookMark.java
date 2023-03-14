package uttug.bookmarkserver.domain.bookmark.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import uttug.bookmarkserver.domain.book.entity.Book;
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
    private String bookMarkName;
    private String moodImageUrl;
    private String summary;
    private Integer checkPageNum;
    @Enumerated(EnumType.STRING)
    private Color color;


    //== 연관 관계 메서드 ==//
    public void addBook(Book book){
        book.getBookMarks().add(this);
    }


    // enum

    @Builder
    public BookMark(Book book, String bookMarkName, String moodImageUrl, String summary, Integer checkPageNum, Color color) {
        this.book = book;
        this.bookMarkName = bookMarkName;
        this.moodImageUrl = moodImageUrl;
        this.summary = summary;
        this.checkPageNum = checkPageNum;
        this.color = color;
    }
}
