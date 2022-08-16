import com.noirix.domain.User;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringTest {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("classpath:application-context.xml");

        User user1 = classPathXmlApplicationContext.getBean("user1", User.class);
        User user2 = classPathXmlApplicationContext.getBean("user2", User.class);

        System.out.println(user1);
        System.out.println(user2);
    }
}
