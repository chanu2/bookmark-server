//package uttug.bookmarkserver.infrastructor;
//
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Component;
//import uttug.bookmarkserver.domain.asset.entity.MoodImage;
//import uttug.bookmarkserver.domain.asset.entity.ProfileImage;
//
//
//import javax.annotation.PostConstruct;
//import javax.persistence.EntityManager;
//import javax.transaction.Transactional;
//
//@Component  // 스프링빈 등록
//@RequiredArgsConstructor
//public class InitDb {
//
//    private final InitService initService;
//
//    @PostConstruct  //bean이 여러 번 초기화되는 걸 방지할 수 있다.
//    public void init() {
//        initService.dbInit1();
//    }
//
//    @Component
//    @Transactional
//    @RequiredArgsConstructor // 생성자 주입
//    static class InitService {
//        private final EntityManager em;
//        public void dbInit1() {
//
//
//            ProfileImage profileImage1 = new ProfileImage("https://checkmark.s3.ap-northeast-2.amazonaws.com/mdsoo55828%40gmail.com%이미지1.jpg");
//            ProfileImage profileImage2 = new ProfileImage("https://checkmark.s3.ap-northeast-2.amazonaws.com/mdsoo55828%40gmail.com%이미지2.jpg");
//            ProfileImage profileImage3 = new ProfileImage("https://checkmark.s3.ap-northeast-2.amazonaws.com/mdsoo55828%40gmail.com%이미지3.jpg");
//
//            em.persist(profileImage1);
//            em.persist(profileImage2);
//            em.persist(profileImage3);
//
//            MoodImage moodImage1 = new MoodImage("좋음","https://checkmark.s3.ap-northeast-2.amazonaws.com/감정이미지1.jpg");
//            MoodImage moodImage2 = new MoodImage("나쁨","https://checkmark.s3.ap-northeast-2.amazonaws.com/감정이미지2.jpg");
//            MoodImage moodImage3 = new MoodImage("꿀꿀","https://checkmark.s3.ap-northeast-2.amazonaws.com/감정이미지3.jpg");
//            MoodImage moodImage4 = new MoodImage("불쾌","https://checkmark.s3.ap-northeast-2.amazonaws.com/감정이미지4.jpg");
//
//            em.persist(moodImage1);
//            em.persist(moodImage2);
//            em.persist(moodImage3);
//            em.persist(moodImage4);
//
//            em.flush();
//            em.clear();
//
//        }
//
//
//    }
//}