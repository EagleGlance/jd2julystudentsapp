package com.noirix.controller.springdata;

import com.noirix.controller.requests.UserCreateRequest;
import com.noirix.domain.Gender;
import com.noirix.domain.User;
import com.noirix.domain.hibernate.HibernateUser;
import com.noirix.repository.jdbctemplate.RoleRepositoryInterface;
import com.noirix.repository.springdata.UserSpringDataRepository;
import com.noirix.util.UUIDGenerator;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rest/data/users")
public class UserController {

    private final UserSpringDataRepository repository;

    private final RoleRepositoryInterface roleRepository;

    @GetMapping
    public ResponseEntity<Object> testEndpoint() {

        return new ResponseEntity<>(Collections.singletonMap("result",
                repository.findAll(PageRequest.of(0, 10))), HttpStatus.OK);
    }

    @GetMapping("/test")
    public ResponseEntity<Object> testEndpointSearchQuery(@RequestParam("id") Long userId, @RequestParam("gender") String gender) {

        return new ResponseEntity<>(Collections.singletonMap("result",
                repository.findByIdAndGender(userId, Gender.valueOf(gender))), HttpStatus.OK);
    }


    @PostMapping
    @Transactional
    public ResponseEntity<Object> createUser(@RequestBody UserCreateRequest createRequest) {

        HibernateUser user = new HibernateUser();
        user.setUserName(createRequest.getUserName());
        user.setSurname(createRequest.getSurname());
        user.setBirth(new Timestamp(new Date().getTime()));
        user.setCreationDate(new Timestamp(new Date().getTime()));
        user.setModificationDate(new Timestamp(new Date().getTime()));
        user.setIsDeleted(false);
        user.setWeight(createRequest.getWeight());

        user.setUserLogin(RandomStringUtils.randomAlphabetic(10));
        user.setUserPassword(RandomStringUtils.randomAlphabetic(10));

        HibernateUser createdUser = repository.save(user);

        repository.createRoleRow(createdUser.getId(), roleRepository.findById(1L).getId());

        Map<String, Object> model = new HashMap<>();
        model.put("user", createdUser);

        return new ResponseEntity<>(model, HttpStatus.CREATED);
    }
}
