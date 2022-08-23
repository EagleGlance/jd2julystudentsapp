import com.noirix.domain.User;
import com.noirix.repository.user.UserRepositoryInterface;
import com.noirix.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

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

        userService.findById(898989898L);

        List<User> all = userService.findAll();
        Map<String, Object> userStats = userService.getUserStats();

        for (User user : all) {
            System.out.println(user);
        }


        for (Map.Entry<String, Object> stringObjectEntry : userStats.entrySet()) {
            System.out.println(stringObjectEntry.getKey() + " : " + stringObjectEntry.getValue());
        }

        User user = new User();
        user.setUserName("JDBC");
        user.setSurname("Template");
        user.setBirth(new Timestamp(new Date().getTime()));
        user.setCreationDate(new Timestamp(new Date().getTime()));
        user.setModificationDate(new Timestamp(new Date().getTime()));
        user.setIsDeleted(false);
        user.setWeight(87D);
        System.out.println(user);

        User user1 = userService.create(user);
        System.out.println(user1);
    }
}
