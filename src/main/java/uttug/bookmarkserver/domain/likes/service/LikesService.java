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
    private final UserUtils userUtils;


//    @Transactional
//    public Boolean saveLike(Long bookId){
//
//        String findEmail = SecurityUtils.getCurrentUserEmail();
//
//        Optional<Likes> findLike = likesRepository.findByBookIdAndUserEmail(bookId, findEmail);
//
//        User user = userUtils.getUserByEmail(findEmail);
//        Book book = bookUtils.queryBook(bookId);
//
//        if(findLike.isEmpty()){
//
//            Likes like = Likes.builder()
//                    .book(book)
//                    .user(user)
//                    .build();
//
//            likesRepository.save(like);
//            book.addLikeNum();
//
//            return true;
//        }else {
//            book.subLikeNum();
//            likesRepository.deleteByBookIdAndUserEmail(bookId,findEmail);
//            return false;
//        }
//    }

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
