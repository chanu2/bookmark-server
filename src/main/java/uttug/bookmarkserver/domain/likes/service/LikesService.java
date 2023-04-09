package uttug.bookmarkserver.domain.likes.service;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import uttug.bookmarkserver.domain.likes.entity.Likes;
import uttug.bookmarkserver.domain.likes.repository.LikesRepository;
import uttug.bookmarkserver.domain.user.entity.User;
import uttug.bookmarkserver.global.utill.security.SecurityUtils;
import uttug.bookmarkserver.global.utill.user.UserUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LikesService implements LikesUtils {

    private final LikesRepository likesRepository;

    @Override
    public Boolean findLikes(Long bookId, String email) {

        Optional<Likes> findLike = likesRepository.findByBookIdAndUserEmail(bookId, email);

        if(findLike.isEmpty()){
            return false;
        }else {
            return true;
        }

    }


}
