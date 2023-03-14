package uttug.bookmarkserver.domain.likes.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import uttug.bookmarkserver.domain.book.entity.Book;
import uttug.bookmarkserver.domain.user.entity.User;
import uttug.bookmarkserver.global.database.BaseEntity;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Likes extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="likes_id")
    private Long id;
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user;


    @Builder
    public Likes(Book book, User user) {
        this.book = book;
        this.user = user;
    }
}
