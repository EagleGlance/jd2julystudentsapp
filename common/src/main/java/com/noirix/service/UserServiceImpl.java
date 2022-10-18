package com.noirix.service;

import com.noirix.domain.User;
import com.noirix.repository.hibernate.HibernateUserInterface;
import com.noirix.repository.user.UserRepositoryInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

//    @Autowired
//    @Qualifier("userRepository")

    //    @Inject
//    @Named("userRepository")
//JSR-330
    private final UserRepositoryInterface userRepository;

    private final HibernateUserInterface hibernateUserInterface;

//    public UserServiceImpl(@Qualifier("userRepository") UserRepositoryInterface userRepository) {
//        this.userRepository = userRepository;
//    }

    @Override
    public List<User> findAll() {

        return userRepository.findAll();
    }

    @Override
    public Map<String, Object> getUserStats() {
        return userRepository.getUserStats();
    }

    @Override
    public User create(User object) {
        return userRepository.create(object);
    }

    @Override
    public User findById(Long userId) {
        return userRepository.findById(userId);
    }

    @Override
    public List<User> search(int limit, int offset) {
        return userRepository.findAll(limit, offset);
    }

    //    @Autowired
//    //@Inject
//    public void setUserRepository(@Qualifier("userRepository") UserRepositoryInterface userRepository) {
//        this.userRepository = userRepository;
//    }
}
