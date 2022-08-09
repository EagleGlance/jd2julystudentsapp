import com.noirix.domain.User;
import com.noirix.repository.user.UserRepository;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserRepository userRepository = new UserRepository();

        List<User> all = userRepository.findAll();

        for (User user : all) {
            System.out.println(user);
        }

        //System.out.println(userRepository.findById(10L));

        userRepository.findOne(1L).ifPresent(System.out::println);

        User user = new User();
        user.setUserName("Prepared");
        user.setSurname("Statement");
        user.setBirth(new Timestamp(new Date().getTime()));
        user.setCreationDate(new Timestamp(new Date().getTime()));
        user.setModificationDate(new Timestamp(new Date().getTime()));
        user.setDeleted(false);
        user.setWeight(87D);
        System.out.println(user);

        User user1 = userRepository.create(user);
        System.out.println(user1);

    }
}
