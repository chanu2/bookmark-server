package uttug.bookmarkserver.domain.asset.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import uttug.bookmarkserver.domain.asset.entity.MoodImage;

import java.util.List;
import java.util.Optional;

public interface MoodImageRepository extends JpaRepository<MoodImage,Long > {

    @Override
    List<MoodImage> findAll();

    Optional<MoodImage> findByMoodName(String moodName);


}
