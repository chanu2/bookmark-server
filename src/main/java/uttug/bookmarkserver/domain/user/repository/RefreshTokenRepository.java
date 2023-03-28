package uttug.bookmarkserver.domain.user.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import uttug.bookmarkserver.domain.user.entity.RefreshToken;

import javax.transaction.Transactional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, String> {
    boolean existsByRefreshToken(String token);
    void deleteByRefreshToken(String token);
}
