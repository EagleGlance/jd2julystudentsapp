import com.noirix.domain.User;
import com.noirix.repository.user.UserRepositoryInterface;
import com.noirix.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class SpringTest {
    public static void main(String[] args) {
//        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("classpath:application-context.xml");
//
//        User user1 = classPathXmlApplicationContext.getBean("user1", User.class);
//        User user2 = classPathXmlApplicationContext.getBean("user2", User.class);
//
//        System.out.println(user1);
//        System.out.println(user2);

        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext("com.noirix");

//        UserRepositoryInterface userRepository = annotationConfigApplicationContext.getBean(
//                "userRepository", UserRepositoryInterface.class);
//
//        for (User user : userRepository.findAll()) {
//            System.out.println(user);
//        }

        UserService userService = annotationConfigApplicationContext.getBean(UserService.class);

        List<User> all = userService.findAll();

        for (User user : all) {
            System.out.println(user);
        }
    }
}
