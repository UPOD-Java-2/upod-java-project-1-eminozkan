package service.user;

import user.User;

public interface UserService {

    void createAccount(User user);

    User login(String username, String password);
}
