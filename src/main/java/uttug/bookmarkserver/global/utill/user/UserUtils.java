package uttug.bookmarkserver.global.utill.user;


import uttug.bookmarkserver.domain.user.entity.User;

public interface UserUtils {

    User getUserByEmail(String email);

    User getUserFromSecurityContext();
}