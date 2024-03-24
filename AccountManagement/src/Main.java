import service.UserServiceImpl;
import user.Gender;
import user.User;

public class Main {
    public static void main(String[] args) {
        User user = new User("eminozkan","parola","Emin","Ozkan","11111111110", Gender.MALE);
        UserServiceImpl userService = new UserServiceImpl();
        userService.createAccount(user);


    }
}