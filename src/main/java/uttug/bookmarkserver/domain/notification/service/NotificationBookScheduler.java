package uttug.bookmarkserver.domain.notification.service;



import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import uttug.bookmarkserver.domain.book.service.BookService;
import uttug.bookmarkserver.domain.book.service.BookUtils;

@Service
@RequiredArgsConstructor
public class NotificationBookScheduler {

    private final BookUtils bookUtils;


    //매일 밤 12시 책 읽은 날 추가
    @Scheduled(cron = "0 0 0 * * *")
    public void resetTodayCalorie(){

        bookUtils.addCompleteReading();
    }
}
