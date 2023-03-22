package uttug.bookmarkserver.domain.user.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import uttug.bookmarkserver.domain.book.entity.Book;
import uttug.bookmarkserver.domain.common.Gender;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(nullable = false, length =50, unique = true)
    private String email;
    private String profilePath;
    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String nickname;

//    @ElementCollection(fetch = FetchType.LAZY)
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            joinColumns = @JoinColumn(name = "user_id"))
    private List<String> roles = new ArrayList<>();


    @Builder
    public User(String email,String profilePath, Gender gender, String nickname,List<String> roles) {
        this.email = email;
        this.profilePath = profilePath;
        this.gender = gender;
        this.nickname = nickname;
        this.roles = roles;
    }


    public void changeUser(String nickname,String profilePath){
        this.nickname = nickname;
        this.profilePath = profilePath;
    }

}
