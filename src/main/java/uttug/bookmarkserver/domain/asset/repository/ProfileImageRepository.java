package uttug.bookmarkserver.domain.asset.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uttug.bookmarkserver.domain.asset.entity.ProfileImage;

import java.util.Optional;

public interface ProfileImageRepository extends JpaRepository<ProfileImage,Long > {


    // 나중 기본 프로필 확장성을 위해서 램덤으로 골라주는 코드
    @Query(value = "SELECT * FROM profile_image order by RAND() limit 1", nativeQuery = true)
    Optional<ProfileImage> findRandomProfileImage();
    
}
