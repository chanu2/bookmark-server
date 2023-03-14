package uttug.bookmarkserver.domain.notification.service;



import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import uttug.bookmarkserver.domain.book.service.BookService;

@Service
@RequiredArgsConstructor
public class NotificationCharacterScheduler {

    private final BookService bookService;


    //매일 칼로리 초기화
//    @Scheduled(cron = "0 0 0 * * *")
//    public void resetTodayCalorie(){
//        runEatCharacterService.resetTodayCalorie();
//    }
}
