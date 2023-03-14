package uttug.bookmarkserver.infrastructor;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Component  // 스프링빈 등록
@RequiredArgsConstructor
public class InitDb {

    private final InitService initService;

    @PostConstruct  //bean이 여러 번 초기화되는 걸 방지할 수 있다.
    public void init() {
        initService.dbInit1();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor // 생성자 주입
    static class InitService {
        private final EntityManager em;
        public void dbInit1() {





        }


    }
}