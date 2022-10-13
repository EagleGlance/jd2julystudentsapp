package com.noirix.controller.springdata;

import com.noirix.controller.requests.UserCreateRequest;
import com.noirix.domain.Gender;
import com.noirix.domain.hibernate.HibernateRole;
import com.noirix.domain.hibernate.HibernateUser;
import com.noirix.repository.jdbctemplate.RoleRepositoryInterface;
import com.noirix.repository.springdata.RolesSpringDataRepository;
import com.noirix.repository.springdata.UserSpringDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rest/data/users")
public class UserController {

    private final UserSpringDataRepository repository;

    private final RolesSpringDataRepository rolesSpringDataRepository;

    private final RoleRepositoryInterface roleRepository;

    private final ConversionService converter;

    @GetMapping
    public ResponseEntity<Object> testEndpoint() {

        return new ResponseEntity<>(Collections.singletonMap("result",
                //repository.findAll(PageRequest.of(0, 10))), HttpStatus.OK);
                repository.findByIsDeletedOrderByIdDesc(false)), HttpStatus.OK);
    }

    @GetMapping("/test")
    public ResponseEntity<Object> testEndpointSearchQuery(@RequestParam("id") Long userId, @RequestParam("gender") String gender) {

        return new ResponseEntity<>(Collections.singletonMap("result",
                repository.findByIdAndGender(userId, Gender.valueOf(gender))), HttpStatus.OK);
    }


    @PostMapping
    @Transactional
    public ResponseEntity<Object> createUser(@RequestBody UserCreateRequest createRequest) {

        HibernateUser user = converter.convert(createRequest, HibernateUser.class);
        HibernateUser createdUser = repository.save(setRoles(user));

        //repository.createRoleRow(createdUser.getId(), roleRepository.findById(1L).getId());

        Map<String, Object> model = new HashMap<>();
        model.put("user", repository.findById(createdUser.getId()).get());

        return new ResponseEntity<>(model, HttpStatus.CREATED);
    }

    private HibernateUser setRoles(HibernateUser user) {
        Set<HibernateRole> roles = user.getRoles();

        Set<HibernateRole> updatedRoles = new HashSet<>();

        if (!CollectionUtils.isEmpty(roles)) {
            updatedRoles.addAll(roles);
        }
        updatedRoles.add(rolesSpringDataRepository.findById(1L).get());
        updatedRoles.add(rolesSpringDataRepository.findById(2L).get());

        user.setRoles(updatedRoles);

        return user;
    }
}
