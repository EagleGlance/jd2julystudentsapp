import com.noirix.domain.User;
import com.noirix.repository.user.UserRepository;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserRepository userRepository = new UserRepository();

        List<User> all = userRepository.findAll();

        for (User user : all) {
            System.out.println(user);
        }
    }
}
