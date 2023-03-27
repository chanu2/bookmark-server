package uttug.bookmarkserver.domain.likes.service;


import uttug.bookmarkserver.domain.book.entity.Book;

public interface LikesUtils {

    Boolean findLikes(Long bookId,String email);


}