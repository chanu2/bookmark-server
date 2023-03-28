package uttug.bookmarkserver.domain.book.service;


import uttug.bookmarkserver.domain.book.entity.Book;

public interface BookUtils {

    Book queryBook(Long bookId);

    void addCompleteReading();



}